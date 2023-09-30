package com.alinesno.infra.base.starter.entity;

import com.alinesno.infra.base.starter.CodeGenBaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
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
public class TreeEntity extends CodeGenBaseEntity {

    /** 父菜单名称 */
    private String parentName;

    /** 父菜单ID */
    private Long parentId;

    /** 显示顺序 */
    private Integer orderNum;

    /** 祖级列表 */
    private String ancestors;
}