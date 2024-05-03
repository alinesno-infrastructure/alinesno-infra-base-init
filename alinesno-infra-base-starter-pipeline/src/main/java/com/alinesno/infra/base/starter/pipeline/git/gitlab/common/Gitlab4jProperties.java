package com.alinesno.infra.base.starter.pipeline.git.gitlab.common;

import lombok.Data;

/**
 * gitlab配置项
 * 
 * @author luoxiaodong
 * @since 1.0.0
 */
@Data
public class Gitlab4jProperties {

	private String url;
	private String user;
	private String privateToken;

}
