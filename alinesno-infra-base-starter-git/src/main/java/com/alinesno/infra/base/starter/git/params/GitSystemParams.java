package com.alinesno.infra.base.starter.git.params;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 获取系统参数
 * 
 * @author luoxiaodong
 * @since 1.0.0
 */
@Component
public class GitSystemParams {

	// github
	private static final String GITHUB_CLIENT_ID = "alinesno.init.gen.github.clientId";
	private static final String GITHUB_CLIENT_SEC = "alinesno.init.gen.github.clientSecrets";
	private static final String GITHUB_AUTH_URL = "alinesno.init.gen.github.authurl";
	private static final String GITHUB_REDIRECT_URL = "alinesno.init.gen.github.redirectUrl";
	private static final String GITHUB_USERINFO = "alinesno.init.gen.github.userInfo" ; 

	// gitee 
	private static final String GITEE_CLIENT_ID = "alinesno.init.gen.gitee.clientId";
	private static final String GITEE_CLIENT_SEC = "alinesno.init.gen.gitee.clientSecrets";
	private static final String GITEE_AUTH_URL = "alinesno.init.gen.gitee.authurl";
	private static final String GITEE_REDIRECT_URL = "alinesno.init.gen.gitee.redirectUrl";
	private static final String GITEE_USERINFO = "alinesno.init.gen.gitee.userInfo" ; 
	
	// gitea
	private static final String GITEA_CLIENT_ID = "alinesno.init.gen.gitea.clientId";
	private static final String GITEA_CLIENT_SEC = "alinesno.init.gen.gitea.clientSecrets";
	private static final String GITEA_AUTH_URL = "alinesno.init.gen.gitea.authurl";
	private static final String GITEA_REDIRECT_URL = "alinesno.init.gen.gitea.redirectUrl";
	private static final String GITEA_USERINFO = "alinesno.init.gen.gitea.userInfo" ;

	@Autowired
	private GitParamMap gitParamMap ;

	/**
	 * 获取github认证信息
	 * 
	 * @return
	 */
	public Oauth2Params getOauthParams() {
		
		Oauth2Params dto = new Oauth2Params(); 
	
		String authUrl = gitParamMap.getConfigValueByKey(GITHUB_AUTH_URL) ; 
		String clientId = gitParamMap.getConfigValueByKey(GITHUB_CLIENT_ID) ; 
		String clientSec = gitParamMap.getConfigValueByKey(GITHUB_CLIENT_SEC) ; 
		String githubRedirectUrl = gitParamMap.getConfigValueByKey(GITHUB_REDIRECT_URL); 
		String userInfo = gitParamMap.getConfigValueByKey(GITHUB_USERINFO); 
		
		dto.setAuthorizeUrl(authUrl);
		dto.setClientId(clientId);
		dto.setClientSecret(clientSec);
		dto.setRedirectUrl(githubRedirectUrl);
		dto.setUserInfoUrl(userInfo);

		return dto ; 
	}

	/**
	 * 获取gitee认证授权
	 * @return
	 */
	public Oauth2Params getGiteeOauthParams() {
		Oauth2Params dto = new Oauth2Params(); 
	
		String authUrl = gitParamMap.getConfigValueByKey(GITEE_AUTH_URL) ; 
		String clientId = gitParamMap.getConfigValueByKey(GITEE_CLIENT_ID) ; 
		String clientSec = gitParamMap.getConfigValueByKey(GITEE_CLIENT_SEC) ; 
		String githubRedirectUrl = gitParamMap.getConfigValueByKey(GITEE_REDIRECT_URL); 
		String userInfo = gitParamMap.getConfigValueByKey(GITEE_USERINFO); 
		
		dto.setAuthorizeUrl(authUrl);
		dto.setClientId(clientId);
		dto.setClientSecret(clientSec);
		dto.setRedirectUrl(githubRedirectUrl);
		dto.setUserInfoUrl(userInfo);

		return dto ; 
	}

	public Oauth2Params getGiteaOauthParams() {
		Oauth2Params dto = new Oauth2Params(); 
	
		String authUrl = gitParamMap.getConfigValueByKey(GITEA_AUTH_URL) ; 
		String clientId = gitParamMap.getConfigValueByKey(GITEA_CLIENT_ID) ; 
		String clientSec = gitParamMap.getConfigValueByKey(GITEA_CLIENT_SEC) ; 
		String githubRedirectUrl = gitParamMap.getConfigValueByKey(GITEA_REDIRECT_URL); 
		String userInfo = gitParamMap.getConfigValueByKey(GITEA_USERINFO); 
		
		dto.setAuthorizeUrl(authUrl);
		dto.setClientId(clientId);
		dto.setClientSecret(clientSec);
		dto.setRedirectUrl(githubRedirectUrl);
		dto.setUserInfoUrl(userInfo);

		return dto ; 
	}

}
