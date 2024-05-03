package com.alinesno.infra.base.starter.pipeline.jenkins;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.offbytwo.jenkins.model.Build;
import com.offbytwo.jenkins.model.BuildWithDetails;
import com.offbytwo.jenkins.model.Job;
import com.offbytwo.jenkins.model.JobWithDetails;

import cn.hutool.core.lang.Dict;
import cn.hutool.extra.template.Template;
import cn.hutool.extra.template.TemplateConfig;
import cn.hutool.extra.template.TemplateConfig.ResourceMode;
import cn.hutool.extra.template.TemplateEngine;
import cn.hutool.extra.template.TemplateUtil;

/**
 * Jenkins工具
 * 
 * @author luoxiaodong
 * @since 1.0.0
 */
@Component
public class JenkinsTool extends BaseJenkinsClient {

	private static final Logger log = LoggerFactory.getLogger(JenkinsTool.class);

	public String getJobXml(Map<String, Object> map) {

		Dict dict = Dict.create();
		dict.putAll(map);

		TemplateEngine engine = TemplateUtil.createEngine(new TemplateConfig("vm", ResourceMode.CLASSPATH));
		Template template = engine.getTemplate("jenkins/maven.job.vm");
		String jobXml = template.render(dict);
		return jobXml;
	}

	public String getNpmJobXml(Map<String, Object> map) {

		Dict dict = Dict.create();
		dict.putAll(map);

		TemplateEngine engine = TemplateUtil.createEngine(new TemplateConfig("vm", ResourceMode.CLASSPATH));
		Template template = engine.getTemplate("jenkins/npm.job.vm");
		String jobXml = template.render(dict);
		return jobXml;
	}

	/**
	 * 获取某次构建的日志信息
	 * 
	 * @param jobName
	 * @return
	 * @throws IOException
	 */
	public String getJobLog(String jobName) throws IOException {

		JobWithDetails job = client.instance().getJob(jobName);
		JobWithDetails details = job.details();
		Build buildByNumber = details.getLastBuild(); // .getBuildByNumber(buildNumber);
		BuildWithDetails details2 = buildByNumber.details();
		
		String outputText = details2.getConsoleOutputText();
		
		return outputText;
	}

	/**
	 * 获取某次构建的开始时间和持续时间
	 * 
	 * @param jobName
	 * @return
	 * @throws IOException
	 */
	public Map<String, Long> getStartTImeAndEndTime(String jobName) throws IOException {

		Map<String, Job> jobs = client.instance().getJobs();
		JobWithDetails job = jobs.get(jobName).details();
		Build buildByNumber = job.getLastBuild();
		long startTime = buildByNumber.details().getTimestamp();
		long duration = buildByNumber.details().getDuration();

		Map<String, Long> data = new HashMap<String, Long>();
		data.put("startTime", startTime);
		data.put("duration", duration);
		return data;
	}

	/**
	 * 获取最后一次构建是否成功
	 * 
	 * @param jobName
	 * @param number
	 * @return
	 * @throws IOException
	 */
	public boolean isSuccess(String jobName, int number) throws IOException {
		Map<String, Job> jobs = client.instance().getJobs();
		
		JobWithDetails job = jobs.get(jobName).details();
		int LastSuccessfulNumber = job.getLastSuccessfulBuild().getNumber();
		int LastUnsuccessfulNumber = job.getLastUnsuccessfulBuild().getNumber();

		boolean flag = false;
		if (LastSuccessfulNumber == number) {
			flag = true;
		}
		if (LastUnsuccessfulNumber == number) {
			flag = false;
		}
		return flag;
	}

	/**
	 * 判断job是否执行完
	 * 
	 * @param number
	 * @param jobName
	 * @return
	 */
	public boolean isFinished(int number, String jobName) {
	
		if(StringUtils.isBlank(jobName)) {
			return false ; 
		}
		
		boolean isBuilding = false;
		try {
			JobWithDetails jobLast = client.instance().getJob(jobName) ; 
			if(jobLast == null) {
				return true ; 
			}
			Build build = jobLast.getLastBuild() ; 
		
			number = build.getNumber() ; 
			log.debug("build = {}" , build);
			
			Map<String, Job> jobs = client.instance().getJobs();
			JobWithDetails job = jobs.get(jobName).details();
			Build buildByNumber = job.getBuildByNumber(number);
			if (null != buildByNumber) {
				BuildWithDetails details = buildByNumber.details();
				if (null != details) {
					isBuilding = details.isBuilding();
				} else {
					isBuilding = true;
				}
			} else {
				isBuilding = true;
			}

			return !isBuilding;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return false;
	}

	/**
	 * 通过获取构建的最终的结果来判断最终的结果 返回结果：SUCCESS，FAILURE
	 * 
	 * @param jobName
	 * @param number
	 * @return
	 * @throws IOException
	 */
	public String isSuccessTag(String jobName, int number) throws IOException {
		Map<String, Job> jobs = client.instance().getJobs();
		JobWithDetails job = jobs.get(jobName).details();
		Build buildByNumber = job.getBuildByNumber(number);
		BuildWithDetails details = buildByNumber.details();
		return details.getResult().toString();
	}

}
