package com.alinesno.infra.base.starter.entity;

import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 种子工程项目基本信息
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "starter_project", autoResultMap = true)
public class ProjectInfoEntity extends InfraBaseEntity {

    /**
     * 应用名称
     */
    @TableField
    private String projectName;

    /**
     * 应用代码
     */
    @TableField
    private String projectCode;

    /**
     * 项目工程名称
     */
    @TableField
    private String artifactId;

    /**
     * 所在分组
     */
    @TableField
    private String groupId;

    /**
     * 服务端口
     */
    @TableField
    private String servicePort;

    /**
     * 是否检测环境
     */
    @TableField
    private String checkEnv;

    /**
     * 是否生成demo
     */
    @TableField
    private String generatorDemo;

    /**
     * jdk版本
     */
    @TableField
    private String jdk;

    /**
     * 包类型
     */
    @TableField
    private String packageType;

    /**
     * 中台版本号
     */
    @TableField
    private String alinesnoVersion;

    /**
     * 依赖组件
     */
    @TableField
    private String dependency;

    /**
     * 作者名称
     */
    @TableField
    private String author;

    /**
     * 作者邮箱
     */
    @TableField
    private String authorEmail;

    /**
     * 打包语言
     */
    @TableField
    private String language;

    /**
     * 打包类型
     */
    @TableField
    private String deployType;

    /**
     * 前端中台版本
     */
    @TableField
    private String alinesnoUI;

    /**
     * 仓库名称
     */
    @TableField
    private String repositoryName;

    /**
     * 业务分组
     */
    @TableField
    private String gitGroupName;

    /**
     * 仓库地址
     */
    @TableField
    private String gitProjectLink;

    /**
     * 仓库ID
     */
    @TableField
    private String gitProjectId;

    /**
     * 自动化操作Token
     */
    @TableField
    private String gitRunnersToken;

    /**
     * 应用Icons
     */
    @TableField
    private String icons;

    /**
     * 显示名称
     */
    @TableField
    private String showName;

    /**
     * 域名
     */
    @TableField
    private String domainName;

    /**
     * 安全存储
     */
    @TableField
    private String storagePath;

    /**
     * 应用目标
     */
    @TableField
    private String target;

    /**
     * 日志监控
     */
    @TableField
    private String loggerWatch;

    /**
     * Promethues监控
     */
    @TableField
    private String prothumensWatch;

    // >>>>>>>>>>>>>>>>>>>>>>>> k8s_start >>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    /**
     * 镜像名称
     */
    @TableField
    private String imageName;

    /**
     * 空间
     */
    @TableField
    private String namespace;

    /**
     * 镜像密钥
     */
    @TableField
    private String dockerSecret;

    // >>>>>>>>>>>>>>>>>>>>>>>> k8s_end >>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    // >>>>>>>>>>>>>>>>>>>>>>>> ci信息_start >>>>>>>>>>>>>>>>>>>>>>>>>>>

    /**
     * Jenkins工程名称
     */
    @TableField
    private String jenkinsJobName;

    /**
     * Jenkins任务状态
     */
    @TableField
    private String jenkinsJobStatus;

    /**
     * 服务构建的ID
     */
    @TableField
    private String gitId;

    /**
     * 工程模型
     */
    @TableField("project_gen_type")
    private String projectGenType;

    // >>>>>>>>>>>>>>>>>>>>>>>> ci信息_end >>>>>>>>>>>>>>>>>>>>>>>>>>>

    /**
     * 关联应用的ID(与authority平台进行关联)
     */
    @TableField("associat_application_id")
    private String associatApplicationId;
}
