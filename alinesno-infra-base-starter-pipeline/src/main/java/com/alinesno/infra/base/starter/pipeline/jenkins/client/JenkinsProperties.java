package com.alinesno.infra.base.starter.pipeline.jenkins.client;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * gitlab配置项
 * 
 * @author luoxiaodong
 * @since 1.0.0
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "alinesno.jenkins")
public class JenkinsProperties {

	private String url;
	private String adminName ;
	private String adminPassword ;

}
