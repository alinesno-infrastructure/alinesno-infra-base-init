package com.alinesno.infra.base.starter.pipeline.git.gitlab.service;

import java.util.Optional;

import com.alinesno.infra.base.starter.pipeline.git.gitlab.BaseGitlab;
import com.alinesno.infra.base.starter.pipeline.git.gitlab.bean.PipelineBean;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.Pipeline;
import org.gitlab4j.api.models.RepositoryFile;
import org.gitlab4j.api.models.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 持续集成管道
 * @author luoxiaodong
 * @since 1.0.0
 */
@Component
public class PipelineService extends BaseGitlab {

	private static final Logger log = LoggerFactory.getLogger(PipelineService.class);
	
	/**
	 * 创建持续集成
	 * @param bean
	 */
	public void createPipeline(PipelineBean bean) {
		createPiplineYaml(bean) ; 
	}

	/**
	 * 触发构建
	 * @param bean
	 */
	public void triggerPipeline(PipelineBean bean) {
	
		String projectName = bean.getProjectName() ;
		
		String TRIGGER_DESCRIPTION = "Test pipeline trigger - DELETE AFTER TEST";
		
		String triggerDescription = TRIGGER_DESCRIPTION + " - test triggerPipeline() - " + getRandomInt() ; 
		
        try {
        	GitLabApi pi = gitlab4jclient.getGitLabApi() ; 
        	
			Trigger createdTrigger = pi.getPipelineApi().createPipelineTrigger(projectName, triggerDescription);
			Pipeline pipeline = pi.getPipelineApi().triggerPipeline(projectName, createdTrigger, "master",  null);
		
			log.debug("pipeline = {}" , pipeline);
			
		} catch (GitLabApiException e) {
			e.printStackTrace();
		}

	}
	
	public static final int getRandomInt() {
        int i = ((int)(Math.random() * Integer.MAX_VALUE + 1));
        return i ; 
    }

	private void createPiplineYaml(PipelineBean bean) {
		
		String TEST_GITLAB_CI_YML_CONTENT = "build:\n" + "  stage: build\n  script:\n    - echo 'Empty build for testing variables with GitLab4J-API'";
		String projectName = bean.getProjectName() ; 

		RepositoryFile gitlabCiYml;
		RepositoryFile createdGitlabCiYml;
		
		Optional<RepositoryFile> fileInfo = gitlab4jclient.getGitLabApi().getRepositoryFileApi().getOptionalFileInfo(projectName, ".gitlab-ci.yml", "master");
        if (fileInfo.isPresent()) {
            gitlabCiYml = fileInfo.get();
        } else {

            try {
                RepositoryFile file = new RepositoryFile();
                file.setFilePath(".gitlab-ci.yml");
                file.setContent(TEST_GITLAB_CI_YML_CONTENT);
                
                createdGitlabCiYml = gitlab4jclient.getGitLabApi().getRepositoryFileApi().createFile(projectName, file, "master", "Need for testing pipelines.");
                
                gitlabCiYml = createdGitlabCiYml;
                log.debug("Created .gitlab-ci.yml file for testing purposes: {}" , gitlabCiYml );
            } catch (Exception e) {
            	e.printStackTrace();
            }
        }
		
	}
	
}
