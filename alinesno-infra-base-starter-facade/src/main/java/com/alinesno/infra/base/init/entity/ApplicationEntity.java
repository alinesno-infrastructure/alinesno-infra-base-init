package com.alinesno.infra.base.init.entity;

import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * 应用构建实体类
 * </p>
 * @author luoxiaodong
 * @version 1.0.0
 */
@TableName("build_application")
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
    private String prothumensWatch;

    public String getProthumensWatch() {
        return prothumensWatch;
    }

    public void setProthumensWatch(String prothumensWatch) {
        this.prothumensWatch = prothumensWatch;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getStoragePath() {
        return storagePath;
    }

    public void setStoragePath(String storagePath) {
        this.storagePath = storagePath;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getLoggerWatch() {
        return loggerWatch;
    }

    public void setLoggerWatch(String loggerWatch) {
        this.loggerWatch = loggerWatch;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcons() {
        return icons;
    }

    public void setIcons(String icons) {
        this.icons = icons;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}
