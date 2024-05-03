package com.alinesno.infra.base.starter.entity;

import com.alinesno.infra.base.starter.enums.GitTypeEnums;
import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnComment;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnType;
import com.gitee.sunchenbin.mybatis.actable.annotation.TableComment;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
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
@TableComment("Git管理")
public class GitInfoEntity extends InfraBaseEntity {

    /**
     * 仓库名称
     */
    @ColumnType(length = 100)
    @ColumnComment("仓库名称")
    @TableField("git_name")
    private String gitName;

    /**
     * 仓库图标
     */
    @ColumnType(MySqlTypeConstant.TEXT)
    @ColumnComment("仓库图标")
    @TableField("git_icon")
    private String gitIcon;

    /**
     * 仓库地址
     */
    @ColumnType(length = 100)
    @ColumnComment("仓库地址")
    @TableField("git_host")
    private String gitHost;

    /**
     * gitlab管理员
     */
    @ColumnType(length = 100)
    @ColumnComment("gitlab管理员")
    @TableField("git_username")
    private String gitUsername;

    /**
     * 仓库空间
     */
    @ColumnType(length = 100)
    @ColumnComment("仓库空间")
    @TableField("git_namespace")
    private String gitNamespace; // 仓库空间

    /**
     * 仓库类型
     */
    @ColumnType(length = 100)
    @ColumnComment("仓库类型")
    @TableField("git_type")
    private String gitType = GitTypeEnums.NULL.getValue(); // 仓库类型(github/gitee/gitlab/gitea)

    /**
     * 仓库管理员密码
     */
    @ColumnType(length = 100)
    @ColumnComment("仓库管理员密码")
    @TableField("access_private_token")
    private String accessPrivateToken;

    /**
     * 内置gitlab
     */
    @ColumnType
    @ColumnComment("内置gitlab")
    @TableField("inner_git")
    private int innerGit;

    /**
     * 是否绑定
     */
    @ColumnType
    @ColumnComment("是否绑定")
    @TableField("has_bing")
    private int hasBing; // 是否绑定

    /**
     * 刷新token
     */
    @ColumnType(length = 100)
    @ColumnComment("刷新token")
    @TableField("refresh_token")
    private String refreshToken;

    /**
     * 超时时间
     */
    @ColumnType
    @ColumnComment("超时时间")
    @TableField("expires_in")
    private int expiresIn;

    /**
     * 绑定第三方git账号信息
     */
    @ColumnType(length = 100)
    @ColumnComment("绑定第三方git账号信息")
    @TableField("bing_git_info")
    private String bingGitInfo; // 绑定第三方git账号信息

}
