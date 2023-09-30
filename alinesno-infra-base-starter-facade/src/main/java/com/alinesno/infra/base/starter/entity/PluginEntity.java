package com.alinesno.infra.base.starter.entity;

import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 项目模块实体类
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("starter_plugin")
public class PluginEntity extends InfraBaseEntity {

    /**
     * 所属场景
     */
    @TableField
    private String screen;

    /**
     * 行业
     */
    @TableField
    private String industry;

    /**
     * 所属类型
     */
    @TableField
    private String type;

    /**
     * 模块范围(1个人使用|2全部使用)
     */
    @TableField
    private String tempScope;

    /**
     * 背景图
     */
    @TableField
    private String tempBanner;

    /**
     * 名称
     */
    @TableField
    private String tempName;

    /**
     * 描述
     */
    @TableField
    private String tempDesc;

    /**
     * 模板内容
     */
    @TableField
    private String tempZip;

    /**
     * 来源作者
     */
    @TableField
    private String tempTeam;

    /**
     * 安装次数
     */
    @TableField
    private int installCount;

    /**
     * 评分
     */
    @TableField
    private int gradeCount;

    /**
     * 支持版本
     */
    @TableField
    private String supprtVersion;

    /**
     * 排序
     */
    @TableField
    private int tempOrder;

}
