package com.alinesno.infra.base.starter.pipeline.git.gitlab.service;

import javax.lang.exception.RpcServiceRuntimeException;

import com.alinesno.infra.base.starter.api.dto.GroupDto;
import com.alinesno.infra.common.core.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.GroupApi;
import org.gitlab4j.api.models.Group;
import org.gitlab4j.api.models.Project;
import org.gitlab4j.api.models.RepositoryFile;
import org.gitlab4j.api.models.User;
import org.gitlab4j.api.models.Visibility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alinesno.infra.base.starter.pipeline.git.gitlab.common.Gitlab4jClient;
import com.google.gson.Gson;

/**
 * 基础服务
 * 
 * @author luoxiaodong
 * @since 1.0.0
 */
@Slf4j
@Service
public class ProjectService {

	@SuppressWarnings("unused")
	private final Gson gson = new Gson();

	private Gitlab4jClient gitlab4jclient;

	/**
	 * 创建项目
	 * 
	 * @param group
	 * @param projectName
	 * @param userId
	 * @param visibility
	 * @param gitlab4jclient2 
	 * @return
	 */
	public Project createProject(GroupDto group, String projectName, Integer userId, boolean visibility, Gitlab4jClient gitlab4jclient2) {
		GitLabApi gitLabApi = gitlab4jclient.getGitLabApi() ; //.getGitLabApi(userId);
		try {
		
			Project project = new Project()
		            .withName(projectName)
		            .withNamespaceId(group.getId())
		            .withDescription("自动生成平台工程:" + projectName)
		            .withVisibility(Visibility.PRIVATE);
			
			Project newProject = gitLabApi.getProjectApi().createProject(project);
			
			return newProject ; 
		} catch (GitLabApiException e) {

			log.info("groupId:{},projectName:{},userId:{},visibility:{}", group, projectName, userId, visibility);

			log.info("{}", e.getMessage());
			throw new RpcServiceRuntimeException(e);
		}
	}

	public Gitlab4jClient getGitlab4jclient() {
		return gitlab4jclient;
	}

	public void setGitlab4jclient(Gitlab4jClient gitlab4jclient) {
		this.gitlab4jclient = gitlab4jclient;
	}

	/**
	 * 通过分组名称查询组别
	 * 
	 * @param groupName
	 * @param userId
	 * @return
	 */
	public Group queryGroupByName(String groupName, Integer userId) {
		GitLabApi gitLabApi = gitlab4jclient.getGitLabApi(userId);
		try {
		
			// TODO 没有分组则创建
			GroupApi groupApi = gitLabApi.getGroupApi() ; 
			
			return groupApi.getGroup(groupName);
		} catch (GitLabApiException e) {
			return null;
		}
	}

	/**
	 * 获取当前用户
	 * 
	 * @return
	 */
	public User queryCurrentUser(Gitlab4jClient client) {
		try {
			return client.getGitLabApi().getUserApi().getCurrentUser() ; //.getGitLabApi(null).getUserApi().getCurrentUser();
		} catch (GitLabApiException e) {
			e.printStackTrace();
			throw new RpcServiceRuntimeException(e) ; 
		}
	}

	/**
	 * 上传文件
	 * @param projectId
	 * @param path
	 * @param content
	 * @param commitMessage
	 * @param userId
	 * @return
	 */
	public RepositoryFile updateFile(Integer projectId, String path, String content, String commitMessage, Integer userId) {
        GitLabApi gitLabApi = gitlab4jclient.getGitLabApi(userId);
        RepositoryFile repositoryFile = new RepositoryFile();
        try {
            repositoryFile.setContent(content);
            repositoryFile.setFilePath(path);
            repositoryFile = gitLabApi.getRepositoryFileApi().updateFile(projectId, repositoryFile , "master", commitMessage);
        } catch (GitLabApiException e) {
        	e.printStackTrace(); 
            throw new RpcServiceRuntimeException(e) ; 
        }
        return repositoryFile;
    }

	public RepositoryFile createFile(Integer projectId, String path, String content, String commitMessage, Integer userId, String branchName) {
        GitLabApi gitLabApi = gitlab4jclient.getGitLabApi(userId);
       
        RepositoryFile repositoryFile = new RepositoryFile();
        try {
            repositoryFile.setContent(content);
            repositoryFile.setFilePath(path);
            repositoryFile = gitLabApi.getRepositoryFileApi().updateFile(projectId, repositoryFile,  StringUtils.isEmpty(branchName) ? "master" : branchName, commitMessage);
        } catch (GitLabApiException e) {
            throw new RpcServiceRuntimeException(e) ; 
        }
        return repositoryFile;
    }

}
