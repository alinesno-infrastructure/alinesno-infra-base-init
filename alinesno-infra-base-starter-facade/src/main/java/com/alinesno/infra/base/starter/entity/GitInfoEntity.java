package com.alinesno.infra.base.starter.entity;

import com.alinesno.infra.base.starter.enums.GitTypeEnums;
import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * <p>
 * 仓库信息实体类
 * </p>
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@ToString
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("starter_gitinfo")
public class GitInfoEntity extends InfraBaseEntity {

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
    private String gitType = GitTypeEnums.NULL.getValue() ; // 仓库类型(github/gitee/gitlab/gitea)

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

}
