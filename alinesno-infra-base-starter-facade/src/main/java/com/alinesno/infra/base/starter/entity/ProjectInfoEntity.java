package com.alinesno.infra.base.starter.entity;

import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnComment;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnType;
import com.gitee.sunchenbin.mybatis.actable.annotation.TableComment;
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
@TableComment("应用管理实体")
public class ProjectInfoEntity extends InfraBaseEntity {

    /**
     * 应用名称
     */
    @ColumnType(length = 100)
    @ColumnComment("应用名称")
    @TableField("project_name")
    private String projectName;

    /**
     * 应用代码
     */
    @ColumnType(length = 100)
    @ColumnComment("应用代码")
    @TableField("project_code")
    private String projectCode;

    /**
     * 项目工程名称
     */
    @ColumnType(length = 100)
    @ColumnComment("项目工程名称")
    @TableField("artifact_id")
    private String artifactId;

    /**
     * 所在分组
     */
    @ColumnType(length = 100)
    @ColumnComment("所在分组")
    @TableField("group_id")
    private String groupId;

    /**
     * 服务端口
     */
    @ColumnType(length = 100)
    @ColumnComment("服务端口")
    @TableField("service_port")
    private String servicePort;

    /**
     * 是否检测环境
     */
    @ColumnType(length = 100)
    @ColumnComment("是否检测环境")
    @TableField("check_env")
    private String checkEnv;

    /**
     * 是否生成demo
     */
    @ColumnType(length = 100)
    @ColumnComment("是否生成demo")
    @TableField("generator_demo")
    private String generatorDemo;

    /**
     * jdk版本
     */
    @ColumnType(length = 100)
    @ColumnComment("jdk版本")
    @TableField("jdk")
    private String jdk;

    /**
     * 包类型
     */
    @ColumnType(length = 100)
    @ColumnComment("包类型")
    @TableField("package_type")
    private String packageType;

    /**
     * 中台版本号
     */
    @ColumnType(length = 100)
    @ColumnComment("中台版本号")
    @TableField("alinesno_version")
    private String alinesnoVersion;

    /**
     * 依赖组件
     */
    @ColumnType(length = 100)
    @ColumnComment("依赖组件")
    @TableField("dependency")
    private String dependency;

    /**
     * 作者名称
     */
    @ColumnType(length = 100)
    @ColumnComment("作者名称")
    @TableField("author")
    private String author;

    /**
     * 作者邮箱
     */
    @ColumnType(length = 100)
    @ColumnComment("作者邮箱")
    @TableField("author_email")
    private String authorEmail;

    /**
     * 打包语言
     */
    @ColumnType(length = 100)
    @ColumnComment("打包语言")
    @TableField("language")
    private String language;

    /**
     * 打包类型
     */
    @ColumnType(length = 100)
    @ColumnComment("打包类型")
    @TableField("deploy_type")
    private String deployType;

    /**
     * 前端中台版本
     */
    @ColumnType(length = 100)
    @ColumnComment("前端中台版本")
    @TableField("alinesno_ui")
    private String alinesnoUI;

    /**
     * 仓库名称
     */
    @ColumnType(length = 100)
    @ColumnComment("仓库名称")
    @TableField("repository_name")
    private String repositoryName;

    /**
     * 业务分组
     */
    @ColumnType(length = 100)
    @ColumnComment("业务分组")
    @TableField("git_group_name")
    private String gitGroupName;

    /**
     * 仓库地址
     */
    @ColumnType(length = 100)
    @ColumnComment("仓库地址")
    @TableField("git_project_link")
    private String gitProjectLink;

    /**
     * 仓库ID
     */
    @ColumnType(length = 100)
    @ColumnComment("仓库ID")
    @TableField("git_project_id")
    private String gitProjectId;

    /**
     * 自动化操作Token
     */
    @ColumnType(length = 100)
    @ColumnComment("自动化操作Token")
    @TableField("git_runners_token")
    private String gitRunnersToken;

    /**
     * 应用Icons
     */
    @ColumnType(length = 100)
    @ColumnComment("应用Icons")
    @TableField("icons")
    private String icons;

    /**
     * 显示名称
     */
    @ColumnType(length = 100)
    @ColumnComment("显示名称")
    @TableField("show_name")
    private String showName;

    /**
     * 域名
     */
    @ColumnType(length = 100)
    @ColumnComment("域名")
    @TableField("domain_name")
    private String domainName;

    /**
     * 安全存储
     */
    @ColumnType(length = 100)
    @ColumnComment("安全存储")
    @TableField("storage_path")
    private String storagePath;

    /**
     * 应用目标
     */
    @ColumnType(length = 100)
    @ColumnComment("应用目标")
    @TableField("target")
    private String target;

    /**
     * 日志监控
     */
    @ColumnType(length = 100)
    @ColumnComment("日志监控")
    @TableField("logger_watch")
    private String loggerWatch;

    /**
     * Promethues监控
     */
    @ColumnType(length = 100)
    @ColumnComment("Promethues监控")
    @TableField("prothumens_watch")
    private String prothumensWatch;

    // >>>>>>>>>>>>>>>>>>>>>>>> k8s_start >>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    /**
     * 镜像名称
     */
    @ColumnType(length = 100)
    @ColumnComment("镜像名称")
    @TableField("image_name")
    private String imageName;

    /**
     * 空间
     */
    @ColumnType(length = 100)
    @ColumnComment("空间")
    @TableField("namespace")
    private String namespace;

    /**
     * 镜像密钥
     */
    @ColumnType(length = 100)
    @ColumnComment("镜像密钥")
    @TableField("docker_secret")
    private String dockerSecret;

    // >>>>>>>>>>>>>>>>>>>>>>>> k8s_end >>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    // >>>>>>>>>>>>>>>>>>>>>>>> ci信息_start >>>>>>>>>>>>>>>>>>>>>>>>>>>

    /**
     * Jenkins工程名称
     */
    @ColumnType(length = 100)
    @ColumnComment("Jenkins工程名称")
    @TableField("jenkins_job_name")
    private String jenkinsJobName;

    /**
     * Jenkins任务状态
     */
    @ColumnType(length = 100)
    @ColumnComment("Jenkins任务状态")
    @TableField("jenkins_job_status")
    private String jenkinsJobStatus;

    /**
     * 服务构建的ID
     */
    @ColumnType(length = 100)
    @ColumnComment("服务构建的ID")
    @TableField("git_id")
    private String gitId;

    /**
     * 工程模型
     */
    @ColumnType(length = 100)
    @ColumnComment("工程模型")
    @TableField("project_gen_type")
    private String projectGenType;

    // >>>>>>>>>>>>>>>>>>>>>>>> ci信息_end >>>>>>>>>>>>>>>>>>>>>>>>>>>

    /**
     * 关联应用的ID(与authority平台进行关联)
     */
    @ColumnType(length = 100)
    @ColumnComment("关联应用的ID(与authority平台进行关联)")
    @TableField("associat_application_id")
    private String associatApplicationId;
}