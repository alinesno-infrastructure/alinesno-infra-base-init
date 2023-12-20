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
 * 项目模块实体类
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("starter_plugin")
@TableComment("插件管理实体")
public class PluginEntity extends InfraBaseEntity {

    /**
     * 所属场景
     */
    @ColumnType(length = 100)
    @ColumnComment("所属场景")
    @TableField("screen")
    private String screen;

    /**
     * 行业
     */
    @ColumnType(length = 100)
    @ColumnComment("行业")
    @TableField("industry")
    private String industry;

    /**
     * 所属类型
     */
    @ColumnType(length = 100)
    @ColumnComment("所属类型")
    @TableField("type")
    private String type;

    /**
     * 模块范围(1个人使用|2全部使用)
     */
    @ColumnType(length = 100)
    @ColumnComment("模块范围")
    @TableField("temp_scope")
    private String tempScope;

    /**
     * 背景图
     */
    @ColumnType(length = 100)
    @ColumnComment("背景图")
    @TableField("temp_banner")
    private String tempBanner;

    /**
     * 名称
     */
    @ColumnType(length = 100)
    @ColumnComment("名称")
    @TableField("temp_name")
    private String tempName;

    /**
     * 描述
     */
    @ColumnType(length = 100)
    @ColumnComment("描述")
    @TableField("temp_desc")
    private String tempDesc;

    /**
     * 模板内容
     */
    @ColumnType(length = 100)
    @ColumnComment("模板内容")
    @TableField("temp_zip")
    private String tempZip;

    /**
     * 来源作者
     */
    @ColumnType(length = 100)
    @ColumnComment("来源作者")
    @TableField("temp_team")
    private String tempTeam;

    /**
     * 安装次数
     */
    @ColumnType
    @ColumnComment("安装次数")
    @TableField("install_count")
    private int installCount;

    /**
     * 评分
     */
    @ColumnType
    @ColumnComment("评分")
    @TableField("grade_count")
    private int gradeCount;

    /**
     * 支持版本
     */
    @ColumnType(length = 100)
    @ColumnComment("支持版本")
    @TableField("support_version")
    private String supprtVersion;

    /**
     * 排序
     */
    @ColumnType
    @ColumnComment("排序")
    @TableField("temp_order")
    private int tempOrder;

}