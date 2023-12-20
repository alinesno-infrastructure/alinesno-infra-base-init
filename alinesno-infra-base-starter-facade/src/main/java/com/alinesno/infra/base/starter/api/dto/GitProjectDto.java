package com.alinesno.infra.base.starter.api.dto;

import lombok.Data;
import lombok.ToString;

/**
 * 种子工程项目基本信息
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@ToString
@Data
public class GitProjectDto {

	private String projectId;

	private String projectIcon; // 项目图标
	private String projectName; // 项目名称
	private String projectCode; // 项目代码

	private String namespace; // git 名称空间
	private String artifactId; // 项目名称
	private String groupId; // 所在分组
	private String alinesnoUI; // 前端UI版本
	private String domain; // 项目域名
	private String checkEnv; // 是否检查环境
	private String generatorDemo; // 是否生成demo
	private String jdk; // JDK版本
	private String delivery;
	private String packageType; // 打包类型(java/node)
	private String alinesnoVersion; // 中台版本号
	private String[] dependency; // 依赖信息

	private String gitRepository; // 生成git地址

}
