package com.alinesno.infra.base.starter.entity;

import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * <p>
 * 应用构建实体类
 * </p>
 * @author luoxiaodong
 * @version 1.0.0
 */
@ToString
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("starter_application")
public class ApplicationEntity extends InfraBaseEntity {

    /**
     * 应用名称
     */
    @TableField
    private String name;

    /**
     * 应用Icons
     */
    @TableField
    private String icons;

    /**
     * 所属领域
     */
    @TableField
    private String domain;

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
     * 安全存储路径
     */
    @TableField
    private String storagePath;

    /**
     * 应用目标（k8s/docker/jar）
     */
    @TableField
    private String target;

    /**
     * 日志监控
     */
    @TableField
    private String loggerWatch;

    /**
     * Prometheus监控
     */
    @TableField
    private String prothrombinsWatch;

}
