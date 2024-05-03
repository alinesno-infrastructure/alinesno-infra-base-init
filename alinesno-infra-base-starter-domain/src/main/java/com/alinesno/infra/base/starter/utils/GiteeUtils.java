package com.alinesno.infra.base.starter.utils;

import java.util.HashMap;
import java.util.Map;

import com.alinesno.infra.base.starter.bean.GiteeBean;
import com.alinesno.infra.base.starter.utils.bean.GiteeResponseBean;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;

import cn.hutool.http.HttpUtil;

/**
 * 更新文件上传
 * 
 * @author luoxiaodong
 * @since 1.0.0
 */
@Slf4j
@Data
public class GiteeUtils {
		
	private static RestTemplate restTemplate = new RestTemplate();

	/**
	 * 更新Gitee文件
	 * 
	 * @param gitee
	 */
	public static void updateFile(GiteeBean gitee) {

		GiteeResponseBean resp = getFile(gitee.getAccessToken() , gitee.getRepo() , gitee.getPath() , gitee.getOwner()) ;
		
		log.debug("git files = {}" , resp);
		
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add("accept", "application/json");
		
		String pushFileUrl = String.format("https://gitee.com/api/v5/repos/%s/%s/contents/%s", gitee.getOwner(), gitee.getRepo(), gitee.getPath());
		log.debug("url = " + pushFileUrl);

		// 如果已经存在文件则更新
		if(resp != null) {
			
			// 封装参数，千万不要替换为Map与HashMap，否则参数无法传递
			MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<String, Object>();
			paramMap.add("access_token", gitee.getAccessToken());
			paramMap.add("owner", gitee.getOwner());
			paramMap.add("repo", gitee.getRepo());
			paramMap.add("sha", resp.getSha());
			paramMap.add("filepath", gitee.getPath());
			paramMap.add("branch", "master") ; 
			paramMap.add("content", gitee.getNewFileBase64()) ; 
			paramMap.add("message", "更新文件:" + gitee.getPath());
			
			HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<MultiValueMap<String, Object>>(paramMap,requestHeaders);
			ResponseEntity<String> response = restTemplate.exchange(pushFileUrl, HttpMethod.PUT, requestEntity, String.class);
			String pushInfo = response.getBody();
			log.debug("pushInfo = " + pushInfo);
			
		}else {
		
			// 不存在则上传
			// 封装参数，千万不要替换为Map与HashMap，否则参数无法传递
			MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<String, Object>();
			paramMap.add("access_token", gitee.getAccessToken());
			paramMap.add("owner", gitee.getOwner());
			paramMap.add("repo", gitee.getRepo());
			paramMap.add("filepath", gitee.getPath());
			paramMap.add("branch", "master") ; 
			paramMap.add("content", gitee.getNewFileBase64()) ; 
			paramMap.add("message", "代码生成器初始化项目库");
			
			HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<MultiValueMap<String, Object>>(paramMap,requestHeaders);
			ResponseEntity<String> response = restTemplate.exchange(pushFileUrl, HttpMethod.POST, requestEntity, String.class);
			String pushInfo = response.getBody();
			log.debug("pushInfo = " + pushInfo);
			
		}
		
	}

	/**
	 * 通过路径获取文件
	 * 
	 * @return
	 */
	public static GiteeResponseBean getFile(String token, String repo, String path, String owner) {

		String url = String.format("https://gitee.com/api/v5/repos/%s/%s/contents/%s", owner, repo, path);
		System.out.println("url = " + url);

		Map<String, Object> map = new HashMap<>();// 存放参数
		map.put("access_token", token);

		HashMap<String, String> headers = new HashMap<>();// 存放请求头，可以存放多个请求头
		headers.put("Content-Type", "application/json");

		// get
		String body = HttpUtil.createGet(url).addHeaders(headers).form(map).execute().body();
		System.out.println("body = " + body);

		GiteeResponseBean arr = JSONObject.parseObject(body, GiteeResponseBean.class) ; 

		return arr;

	}

	/**
	 * 判断是否存在文件
	 * 
	 * @param token
	 * @param repo
	 * @param path
	 * @param owner
	 * @return
	 */
	public static boolean hasFile(String token, String repo, String path, String owner) {

		GiteeResponseBean rep = getFile(token, repo, path, owner);

		return rep != null;
	}

}
