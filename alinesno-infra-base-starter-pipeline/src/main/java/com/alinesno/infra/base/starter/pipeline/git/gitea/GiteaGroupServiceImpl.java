package com.alinesno.infra.base.starter.pipeline.git.gitea;

import java.util.ArrayList;
import java.util.List;

import com.alinesno.infra.base.starter.api.dto.GroupDto;
import com.alinesno.infra.base.starter.entity.GitInfoEntity;
import com.alinesno.infra.base.starter.service.git.IGitGroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 用户业务服务
 * 
 * @author luoxiaodong
 * @since 1.0.0
 */
@Service("giteaGroupService")
public class GiteaGroupServiceImpl implements IGitGroupService {

	private static final Logger log = LoggerFactory.getLogger(GiteaGroupServiceImpl.class) ; 
	
	@Override
	public List<GroupDto> listGroup(GitInfoEntity e, String accessToken) {
	
		String url = e.getGitHost() + "/api/v1/user/orgs" ; 
		
		log.info("getOrgsInfo url:{}", url);
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("accept", "application/json");
        requestHeaders.add("Authorization", "token " + accessToken);
        
        HttpEntity<String> requestEntity = new HttpEntity<>(requestHeaders);
        RestTemplate restTemplate = new RestTemplate();
        
        // get请求方式
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
        String groupInfo = response.getBody();
        log.info("groupInfo={}", groupInfo);
        
        List<GroupDto> gs = new ArrayList<GroupDto>() ; 
        
        List<JSONObject> groupJson = JSONArray.parseArray(groupInfo , JSONObject.class); 
        for(JSONObject g : groupJson) {
        	
        	log.debug("json object = {}" , g.toJSONString()) ;
        	
        	GroupDto ge = new GroupDto() ;
        	
        	ge.setName(g.getString("username")) ;
        	ge.setDescription(g.getString("description") == null?g.getString("username"):g.getString("description")) ; 
        	ge.setId(g.getInteger("id")) ;
        	
        	gs.add(ge) ;
        }
        
		return gs ;
	}

}
