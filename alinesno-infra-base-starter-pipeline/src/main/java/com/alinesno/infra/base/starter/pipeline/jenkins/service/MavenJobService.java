package com.alinesno.infra.base.starter.pipeline.jenkins.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.lang.exception.RpcServiceRuntimeException;

import com.alinesno.infra.base.starter.pipeline.jenkins.JenkinsTool;
import com.alinesno.infra.base.starter.pipeline.jenkins.bean.MavenJobBean;
import com.alinesno.infra.base.starter.pipeline.jenkins.bean.NpmJobBean;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alinesno.infra.base.starter.pipeline.git.gitlab.common.Gitlab4jProperties;
import com.alinesno.infra.base.starter.pipeline.jenkins.BaseJenkinsClient;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;

/**
 * 构建maven工程并执行
 * 
 * @author luoxiaodong
 * @since 1.0.0
 */
@Component
public class MavenJobService extends BaseJenkinsClient {

	private static final Logger log = LoggerFactory.getLogger(MavenJobService.class);

	private Gitlab4jProperties gitlabProperties;

	@Autowired
	protected JenkinsTool jenkinsTool ;
	
	/**
	 * 工程详情
	 * @param jobName
	 * @return 
	 */
	public String detailJobXml(String jobName) {
		try {
			return client.instance().getJobXml(jobName);
		} catch (IOException e) {
			log.error("删除工程{}异常:{}" , jobName , e);
			throw new RpcServiceRuntimeException(e) ; 
		}
	}

	/**
	 * 删除工程
	 * @param jobName
	 */
	public void deleteJob(String jobName) {
		try {
			client.instance().deleteJob(jobName);
		} catch (IOException e) {
			log.error("删除工程{}异常:{}" , jobName , e);
			throw new RpcServiceRuntimeException(e) ; 
		}
	}

	/**
	 * 创建maven构建结构工程
	 * 
	 * @param bean
	 * @return 
	 */
	public MavenJobBean createMavenJob(MavenJobBean bean) {
		
		String name = bean.getName();

		String jobName = name + "-" + IdWorker.get32UUID();
		bean.setJobName(jobName);

		String gitPath = bean.getGitPath();
		String artifactid = bean.getArtifactid() ;
		
		String user = gitlabProperties.getUser();
		String token = gitlabProperties.getPrivateToken();

		String gitClonePath = generatorClonePath(gitPath, user, token);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("GIT_PATH", gitClonePath);

		if(StringUtils.isNoneBlank(artifactid)) {
			map.put("ARTIFACTID", artifactid + "-boot/");
		}else {
			map.put("ARTIFACTID", "") ; 
		}
		
		map.put("MVN_COMMOND", "compile jib:build");
		String jobXml = jenkinsTool.getJobXml(map);

		log.debug("jobXml = {}", jobXml);

		try {
			client.instance().createJob(jobName, jobXml, true);
			client.instance().getJob(jobName).build() ;
			
		} catch (IOException e) {
			log.error("构建工程{}异常:{}" , jobName , e);
			throw new RpcServiceRuntimeException(e) ; 
		}
		
		return bean ; 

	}
	
	/**
	 * 创建maven构建结构工程
	 * 
	 * @param bean
	 * @return 
	 */
	public NpmJobBean createNpmJob(NpmJobBean bean) {
		
		bean.setBranches("master");
		String name = bean.getName();

		String jobName = name + "-" + IdWorker.get32UUID();
		bean.setJobName(jobName);

		String gitPath = bean.getGitPath();
		String artifactid = bean.getArtifactid() ;
		
		String user = gitlabProperties.getUser();
		String token = gitlabProperties.getPrivateToken();

		String gitClonePath = generatorClonePath(gitPath, user, token);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("GIT_PATH", gitClonePath);

		if(StringUtils.isNoneBlank(artifactid)) {
			map.put("ARTIFACTID", artifactid + "-ui/");
		}else {
			map.put("ARTIFACTID", "") ; 
		}
		
		map.put("MVN_COMMOND", "compile jib:build");
		String jobXml = jenkinsTool.getNpmJobXml(map);

		log.debug("jobXml = {}", jobXml);

		try {
			client.instance().createJob(jobName, jobXml, true);
			client.instance().getJob(jobName).build() ; 
			
		} catch (Exception e) {
			log.error("构建工程{}异常:{}" , jobName , e);
			throw new RpcServiceRuntimeException(e) ; 
		}
		
		return bean ; 

	}

	/**
	 * 生成克隆路径
	 * 
	 * @param gitPath
	 * @param user
	 * @param token
	 * @return
	 */
	private static String generatorClonePath(String gitPath, String user, String token) {
	
		int startIndex = gitPath.indexOf("://") ;
		StringBuilder  sb = new StringBuilder(gitPath);
		sb.insert(startIndex + 3 , user + ":" + token + "@") ; 
		
		return sb.toString() ;
	}

	/**
	 * 任务执行是否完成
	 * @param jenkinsJobName
	 * @return 
	 */
	public boolean isFinish(String jenkinsJobName) {
		boolean b = jenkinsTool.isFinished(0 , jenkinsJobName) ; 
		return b ; 
	}

}
