package com.alinesno.infra.base.starter.git.utils;

import com.alibaba.fastjson.JSONObject;
import com.alinesno.infra.base.starter.entity.GitInfoEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * token认证授权
 * 
 * @author luoxiaodong
 * @version 1.0.0
 */
@Component
public class AccessTokenUtils {

	private static final Logger log = LoggerFactory.getLogger(AccessTokenUtils.class);

	public String getGithubAccessToken(String code, String authUrl, String clientId, String clientSec) {

		String url = authUrl + "/login/oauth/access_token" + "?client_id=" + clientId + "&client_secret=" + clientSec  + "&code=" + code + "&grant_type=authorization_code";
		log.info("getAccessToken url:{}", url);

		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add("accept", "application/json");

		HttpEntity<String> requestEntity = new HttpEntity<>(requestHeaders);
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);
		String responseStr = response.getBody();
		log.info("responseStr={}", responseStr);

		JSONObject jsonObject = JSONObject.parseObject(responseStr);
		String accessToken = jsonObject.getString("access_token");

		log.info("accessToken={}", accessToken);
		return accessToken;
	}

	public String getGithubUserInfo(String accessToken, String url) {

		log.info("getUserInfo url:{}", url);
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add("accept", "application/json");
		requestHeaders.add("Authorization", "token " + accessToken);

		HttpEntity<String> requestEntity = new HttpEntity<>(requestHeaders);
		RestTemplate restTemplate = new RestTemplate();

		// get请求方式
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
		String userInfo = response.getBody();
		log.info("userInfo={}", userInfo);
		return userInfo;
	}

	public JSONObject getGiteeAccessToken(String code, String authorizeUrl, String clientId, String clientSecret,
			String redirectUri) {

		String url = authorizeUrl + "/oauth/token" + "?client_id=" + clientId + "&client_secret=" + clientSecret  + "&code=" + code + "&redirect_uri=" + redirectUri + "&grant_type=authorization_code";
		log.info("getGiteeAccessToken url:{}", url);

		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add("accept", "application/json");

		HttpEntity<String> requestEntity = new HttpEntity<>(requestHeaders);
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);
		String responseStr = response.getBody();
		log.info("responseStr={}", responseStr);

		return JSONObject.parseObject(responseStr);
	}

	public String getGiteeUserInfo(String accessToken, String userInfoUrl) {

		log.info("getGiteeUserInfo url:{}", userInfoUrl);
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add("accept", "application/json");
		requestHeaders.add("Authorization", "token " + accessToken);

		HttpEntity<String> requestEntity = new HttpEntity<>(requestHeaders);
		RestTemplate restTemplate = new RestTemplate();

		// get请求方式
		ResponseEntity<String> response = restTemplate.exchange(userInfoUrl, HttpMethod.GET, requestEntity,
				String.class);
		String userInfo = response.getBody();
		log.info("userInfo={}", userInfo);
		return userInfo;
	}

	public JSONObject getGiteaAccessToken(String code, String authUrl, String clientId, String clientSec) {
		String url = authUrl + "/login/oauth/access_token" + "?client_id=" + clientId + "&client_secret=" + clientSec + "&code=" + code + "&grant_type=authorization_code";
		log.info("getAccessToken url:{}", url);

		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add("accept", "application/json");

		HttpEntity<String> requestEntity = new HttpEntity<>(requestHeaders);
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);
		String responseStr = response.getBody();
		log.info("responseStr={}", responseStr);

        return JSONObject.parseObject(responseStr);
	}

	public String getGiteaUserInfo(String accessToken, String url) {

		log.info("getUserInfo url:{}", url);
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add("accept", "application/json");
		requestHeaders.add("Authorization", "token " + accessToken);

		HttpEntity<String> requestEntity = new HttpEntity<>(requestHeaders);
		RestTemplate restTemplate = new RestTemplate();

		// get请求方式
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
		String userInfo = response.getBody();
		log.info("userInfo={}", userInfo);
		return userInfo;
	}

	/**
	 * 刷新gitee token
	 */
	public JSONObject refreshGiteeToken(GitInfoEntity e) {
		String refreshToken = e.getRefreshToken();

		String refreshUrl = "https://gitee.com/oauth/token?grant_type=refresh_token&refresh_token=" + refreshToken;

		log.info("refreshGiteeToken url:{}", refreshUrl);
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add("accept", "application/json");

		HttpEntity<String> requestEntity = new HttpEntity<>(requestHeaders);
		RestTemplate restTemplate = new RestTemplate();

		// get请求方式
		ResponseEntity<String> response = restTemplate.exchange(refreshUrl, HttpMethod.POST, requestEntity, String.class);
		String refreshGiteeToken = response.getBody();
		log.info("refreshGiteeToken={}", refreshGiteeToken);
		
		return JSONObject.parseObject(refreshGiteeToken) ; 
	}

	/**
	 * 刷新gitea token
	 * 
	 * @param e
	 */
	public JSONObject refreshGiteaToken(GitInfoEntity e) {

		String refreshToken = e.getRefreshToken();
		String refreshUrl = e.getGitHost() + "/login/oauth/token?grant_type=refresh_token&refresh_token=" + refreshToken;

		log.info("refreshGiteeToken url:{}", refreshUrl);
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add("accept", "application/json");

		HttpEntity<String> requestEntity = new HttpEntity<>(requestHeaders);
		RestTemplate restTemplate = new RestTemplate();

		// get请求方式
		ResponseEntity<String> response = restTemplate.exchange(refreshUrl, HttpMethod.POST, requestEntity,  String.class);
		
		String refreshGiteeToken = response.getBody();
		log.info("refreshGiteeToken={}", refreshGiteeToken);
		
		return JSONObject.parseObject(refreshGiteeToken) ; 
	}

	/**
	 * 刷新第三方gitea token
	 * 
	 * @param e
	 */
	public JSONObject refreshGiteaSelfToken(GitInfoEntity e) {
		
		return null ; 
	}

	public JSONObject getGitlabAccessToken(String code, String authorizeUrl, String clientId, String clientSecret) {

		return null ;
	}
}
