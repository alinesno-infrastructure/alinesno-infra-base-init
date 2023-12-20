package com.alinesno.infra.base.starter.api.dto;

import com.alinesno.infra.common.facade.base.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 种子工程项目基本信息
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@ToString
@EqualsAndHashCode(callSuper = true)
@Data
public class ProjectInfoDto extends BaseDto {

	private String projectName; // 项目名称

	private String serviceType ; // 服务类型(all前后端|service后端服务|ui前端)

	private String artifactId; // 项目标识

	private String groupId; // 所在分组

	private String gitId ; // 仓库Id

	private String deployType; // 打包类型[jar/k8s/docker-compose]

	private String projectGenType ; // 工程模型(mvc工程|ddd领域工程)

	private boolean generatorDemo; // 是否生成demo

}
