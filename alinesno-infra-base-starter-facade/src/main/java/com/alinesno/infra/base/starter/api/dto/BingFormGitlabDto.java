package com.alinesno.infra.base.starter.api.dto;

import lombok.Data;

/**
 * 保存绑定的gitlab
 * 
 * @author luoxiaodong
 * @since 1.0.0
 */
@Data
public class BingFormGitlabDto {

	private String gitlabId ; 
	private String gitHost;
	private String userName;
	private String accessPrivateToken;
	private String gitType ; // 仓库类型

}
