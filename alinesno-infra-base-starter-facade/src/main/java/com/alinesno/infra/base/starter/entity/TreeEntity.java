package com.alinesno.infra.base.starter.entity;

import com.alinesno.infra.base.starter.CodeGenBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnComment;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnType;
import com.gitee.sunchenbin.mybatis.actable.annotation.TableComment;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Tree基类
 * 
 * @author ruoyi
 * @author luoxiaodong
 *
 * @version 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("starter_tree")
@TableComment("树形关联实体展示")
public class TreeEntity extends CodeGenBaseEntity {

    /** 父菜单名称 */
    @ColumnType(length = 50)
    @ColumnComment("父菜单名称")
    @TableField("parent_name")
    private String parentName;

    /** 父菜单ID */
    @ColumnType(length = 20)
    @ColumnComment("父菜单ID")
    @TableField("parent_id")
    private Long parentId;

    /** 显示顺序 */
    @ColumnType(length = 4)
    @ColumnComment("显示顺序")
    @TableField("order_num")
    private Integer orderNum;

    /** 祖级列表 */
    @ColumnType(length = 50)
    @ColumnComment("祖级列表")
    @TableField("ancestors")
    private String ancestors;

}