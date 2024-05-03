package com.alinesno.infra.base.starter.pipeline.git.gitlab.common;

import java.util.concurrent.ConcurrentHashMap;

import javax.lang.exception.RpcServiceRuntimeException;

import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;

/**
 * Gitlab客户端
 * 
 * @author luoxiaodong
 * @since 1.0.0
 */
public class Gitlab4jClient {
	
	private static final Integer ROOT_USER_ID = 1;

	private Gitlab4jProperties properties ; 

	private ConcurrentHashMap<String, GitLabApi> clientMap = new ConcurrentHashMap<>();

	private GitLabApi createGitLabApi(GitLabApi.ApiVersion apiVersion, Integer userId) {
		try {
			GitLabApi newGitLabApi = new GitLabApi(apiVersion, properties.getUrl(), properties.getPrivateToken());
			if (userId != null) {
				newGitLabApi.setSudoAsId(userId);
			}
			return newGitLabApi;
		} catch (GitLabApiException e) {
			throw new RpcServiceRuntimeException(e);
		}
	}

	public GitLabApi getGitLabApiUser(Integer userId) {
		return getGitLabApi(GitLabApi.ApiVersion.V4, userId);
	}

	public GitLabApi getGitLabApiVersion(GitLabApi.ApiVersion apiVersion) {
		return getGitLabApi(apiVersion, ROOT_USER_ID);
	}

	public GitLabApi getGitLabApi() {
		return getGitLabApiUser(ROOT_USER_ID);
	}

	public GitLabApi getGitLabApi(Integer userId) {
		return getGitLabApi(GitLabApi.ApiVersion.V4, userId);
	}

	public Gitlab4jProperties getProperties() {
		return properties;
	}

	public void setProperties(Gitlab4jProperties properties) {
		this.properties = properties;
	}

	/**
	 * 创建gitLabApi
	 *
	 * @param apiVersion api版本
	 * @param userId     用户ID
	 * @return GitLabApi
	 */
	public GitLabApi getGitLabApi(GitLabApi.ApiVersion apiVersion, Integer userId) {
		String key = apiVersion.getApiNamespace() + "-" + userId;
		GitLabApi gitLabApi = clientMap.get(key);
		if (gitLabApi != null) {
			return gitLabApi;
		} else {
			gitLabApi = createGitLabApi(apiVersion, userId);
			clientMap.put(key, gitLabApi);
			return gitLabApi;
		}
	}
}