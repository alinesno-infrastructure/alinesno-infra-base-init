package com.alinesno.infra.base.starter.git.controller;

import com.alinesno.infra.base.starter.git.params.GitSystemParams;
import com.alinesno.infra.base.starter.git.params.Oauth2Params;
import com.alinesno.infra.base.starter.git.utils.AccessTokenUtils;
import com.alinesno.infra.base.starter.service.IGitInfoService;
import com.alinesno.infra.common.core.constants.SpringInstanceScope;
import com.alinesno.infra.common.facade.response.AjaxResult;
import com.alinesno.infra.common.web.adapter.login.account.CurrentAccountJwt;
import com.alinesno.infra.common.web.adapter.rest.SuperController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

/**
 * 绑定git
 * 
 * @author luoxiaodong
 * @since 1.0.0
 */
@Slf4j
@RestController
@Scope(SpringInstanceScope.PROTOTYPE)
@RequestMapping("/api/infra/base/starter/bingGit")
public class BingGithubController extends SuperController {

	@Autowired
	private IGitInfoService gitInfoService ;

	@Autowired
	private GitSystemParams gitParams ;
	
	@Autowired
	private AccessTokenUtils accessTokenUtils ;

	/**
	 * github 绑定回调
	 * @return
	 */
	@GetMapping("/callback")
	public AjaxResult githubCallback(@RequestParam("code") String code , String from) {
		
		long accountId = CurrentAccountJwt.getUserId() ;
		log.info("code={} , from={} , accountId={}", code , from , accountId);
		
		if(from.contains("github")) { // 处理github
			Oauth2Params param = gitParams.getOauthParams() ;
		
			String accessToken = accessTokenUtils.getGithubAccessToken(code, param.getAuthorizeUrl() , param.getClientId(), param.getClientSecret());
			String userInfo = accessTokenUtils.getGithubUserInfo(accessToken , param.getUserInfoUrl());

			gitInfoService.bingGithub(accountId, accessToken, JSONObject.parseObject(userInfo)) ;
		}else if(from.contains("gitee")) { // 处理gitee
			
			Oauth2Params param = gitParams.getGiteeOauthParams() ; 
			
			JSONObject token = accessTokenUtils.getGiteeAccessToken(code, param.getAuthorizeUrl() , param.getClientId(), param.getClientSecret() , param.getRedirectUrl());
			String userInfo = accessTokenUtils.getGiteeUserInfo(token.getString("access_token"), param.getUserInfoUrl());

			gitInfoService.bingGitee(accountId, token, JSONObject.parseObject(userInfo)) ;
		}else if(from.contains("gitea")) { // 处理gitea
			
			Oauth2Params param = gitParams.getGiteaOauthParams() ; 
		
			JSONObject token = accessTokenUtils.getGiteaAccessToken(code, param.getAuthorizeUrl() , param.getClientId(), param.getClientSecret());
			String userInfo = accessTokenUtils.getGiteaUserInfo(token.getString("access_token") , param.getUserInfoUrl());

			gitInfoService.bingGitea(accountId, token, JSONObject.parseObject(userInfo)) ;
		}
        
		return AjaxResult.success() ; 
	}
	
}
