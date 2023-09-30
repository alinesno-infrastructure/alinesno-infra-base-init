package com.alinesno.infra.base.starter.api.dto;

import com.alinesno.infra.base.starter.api.dto.GroupDto;
import com.alinesno.infra.common.facade.base.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Arrays;

/**
 * 种子工程项目基本信息
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@ToString
@Data
public class ProjectInfoDto extends BaseDto {

	private String projectId;
	private String projectName; // 项目名称
	private String projectIcon; // 项目图标
	private String projectCode; // 项目代码
	private String artifactId; // 项目标识
	private String groupId; // 所在分组
	private String servicePort; // 服务端口
	private String domain; // 项目域名
	private String checkEnv; // 是否检测环境
	private String generatorDemo; // 是否生成demo
	private String jdk; // jdk版本
	private String packageType; // 包类型
	private String alinesnoVersion; // 中台版本号
	private String[] dependency; // 依赖组件
	private String author; // 作者名称
	private String authorEmail; // 作者邮件
	private String language; // 语言版本
	private String deployType; // 打包类型[jar/k8s/docker-compose]
	
	private GroupDto group ; //分组信息
	private String alinesnoUI; // 前端工程
	private String repositoryName; // 仓库名称
	private String gitGroupName; // git业务分组名称

	private String gitProjectLink; // 仓库地址
	private String gitProjectId; // 仓库Id
	private String gitType; // 仓库名
	private String gitTypeId ; // 仓库名
	private String namespace; // git 名称空间
	private String gitRunnersToken; // 自动化操作地址
	
	private String projectGenType ; // 工程模型
	private String gitHomePath ; // 仓库父类地址

}
