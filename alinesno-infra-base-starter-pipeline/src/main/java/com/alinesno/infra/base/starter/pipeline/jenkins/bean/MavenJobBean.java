package com.alinesno.infra.base.starter.pipeline.jenkins.bean;

import lombok.Data;

/**
 * 构建信息
 * 
 * @author luoxiaodong
 * @since 1.0.0
 */
@Data
public class MavenJobBean {

	private String name;
	private String artifactid ; 
	private String assignedNode ; // 构建节点
	private String gitPath; // 地址
	private String branches; // 分支
	private String jobName ; // 工程名称
	
}
