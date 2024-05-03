package com.alinesno.infra.base.starter.pipeline.git.gitlab.service;

import lombok.extern.slf4j.Slf4j;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.Project;
import org.gitlab4j.api.models.Runner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alinesno.infra.base.starter.pipeline.git.gitlab.BaseGitlab;
import com.alinesno.infra.base.starter.pipeline.git.gitlab.common.Gitlab4jClient;

/**
 * 处理跑任务的问题
 * 
 * @author luoxiaodong
 * @since 1.0.0
 */
@Slf4j
@Service
public class RunnerService extends BaseGitlab {

	private Gitlab4jClient gitlab4jclient;
	
	public void registryRunnerByProjectName(String projectName) {
		
		try {
			Project project = gitlab4jclient.getGitLabApi().getProjectApi().getProject(projectName) ; 
			project = gitlab4jclient.getGitLabApi().getProjectApi().getProject(project.getId());
		
			String runnersToken = project.getRunnersToken();
			this.createRunner(runnersToken);
			
		} catch (GitLabApiException e) {
			e.printStackTrace();
		}
        
	}

	/**
	 * 创建一个任务
	 * @param runnersToken
	 */
	public void createRunner(String runnersToken) {

		try {
			Runner runner = gitlab4jclient
						.getGitLabApi()
						.getRunnersApi()
						.registerRunner(runnersToken,"Junit registered runner", true, null , true , true, null) ;
	
			log.debug("runner = {}" , runner);
			
		} catch (GitLabApiException e) {
			log.error("异常信息:{}" , e.getMessage());
		}
	}
}
