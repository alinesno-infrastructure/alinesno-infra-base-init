package com.alinesno.infra.base.init.entity;

import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * 仓库信息实体类
 * </p>
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@TableName("build_git")
public class BuildGitEntity extends InfraBaseEntity {

    /**
     * 仓库地址
     */
    @TableField
    private String gitName; // 账号地址

    /**
     * 仓库图标
     */
    @TableField
    private String gitIcon;

    /**
     * 仓库地址
     */
    @TableField
    private String gitHost; // 账号地址

    /**
     * gitlab管理员
     */
    @TableField
    private String gitUsername; // 仓库空间

    /**
     * 仓库空间
     */
    @TableField
    private String gitNamespace; // 仓库空间

    /**
     * 仓库类型
     */
    @TableField
    private String gitType; // 仓库类型

    /**
     * 仓库管理员密码
     */
    @TableField
    private String accessPrivateToken;

    /**
     * 内置gitlab
     */
    @TableField("inner_git")
    private int innerGit;

    /**
     * 是否绑定
     */
    @TableField("has_bing")
    private int hasBing; // 是否绑定

    /**
     * 刷新token
     */
    @TableField("refresh_token")
    private String refreshToken;

    /**
     * 超时时间
     */
    @TableField("expires_in")
    private int expiresIn;

    /**
     * 绑定第三方git账号信息
     */
    @TableField("bing_git_info")
    private String bingGitInfo; // 绑定第三方git账号信息

    public String getGitName() {
        return gitName;
    }

    public void setGitName(String gitName) {
        this.gitName = gitName;
    }

    public String getGitIcon() {
        return gitIcon;
    }

    public void setGitIcon(String gitIcon) {
        this.gitIcon = gitIcon;
    }

    public String getGitHost() {
        return gitHost;
    }

    public void setGitHost(String gitHost) {
        this.gitHost = gitHost;
    }

    public String getGitUsername() {
        return gitUsername;
    }

    public void setGitUsername(String gitUsername) {
        this.gitUsername = gitUsername;
    }

    public String getGitNamespace() {
        return gitNamespace;
    }

    public void setGitNamespace(String gitNamespace) {
        this.gitNamespace = gitNamespace;
    }

    public String getGitType() {
        return gitType;
    }

    public void setGitType(String gitType) {
        this.gitType = gitType;
    }

    public String getAccessPrivateToken() {
        return accessPrivateToken;
    }

    public void setAccessPrivateToken(String accessPrivateToken) {
        this.accessPrivateToken = accessPrivateToken;
    }

    public int getInnerGit() {
        return innerGit;
    }

    public void setInnerGit(int innerGit) {
        this.innerGit = innerGit;
    }

    public int getHasBing() {
        return hasBing;
    }

    public void setHasBing(int hasBing) {
        this.hasBing = hasBing;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getBingGitInfo() {
        return bingGitInfo;
    }

    public void setBingGitInfo(String bingGitInfo) {
        this.bingGitInfo = bingGitInfo;
    }
}
