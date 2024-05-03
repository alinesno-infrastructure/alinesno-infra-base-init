package com.alinesno.infra.base.starter.pipeline.jenkins.client;

import java.net.URI;
import java.net.URISyntaxException;

import lombok.Data;
import org.springframework.stereotype.Component;

import com.offbytwo.jenkins.JenkinsServer;

/**
 * Jenkins客户端
 * 
 * @author luoxiaodong
 * @since 1.0.0
 */
@Data
@Component
public class JenkinsClient {

	private JenkinsProperties jenkinsProperties ; 

	public JenkinsServer instance() {
		JenkinsServer jenkins = null;
	
		String url = jenkinsProperties.getUrl() ; 
		String username = jenkinsProperties.getAdminName() ; 
		String password = jenkinsProperties.getAdminPassword() ;
		
		try {
			jenkins = new JenkinsServer(new URI(url), username , password);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return jenkins;
	}

}
