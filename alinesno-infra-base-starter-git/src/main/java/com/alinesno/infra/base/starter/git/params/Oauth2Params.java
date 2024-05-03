package com.alinesno.infra.base.starter.git.params;

import lombok.Data;

/**
 * github Oauth2认证信息
 * 
 * @author luoxiaodong
 * @since 1.0.0
 */
@Data
public class Oauth2Params {

	private String clientId;
	private String clientSecret;
	private String authorizeUrl;
	private String redirectUrl;
	private String accessTokenUrl;
	private String userInfoUrl;

}