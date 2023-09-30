package com.alinesno.infra.base.starter.entity;

import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 创建示例应用菜单实体类，通过git进行示例功能传输
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@ToString
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "starter_project_menus", autoResultMap = true)
public class ProjectMenusEntity extends InfraBaseEntity {

    /**
     * 菜单的json
     */
    @TableField
    private String menusJson;

    /**
     * 账号的示例json
     */
    @TableField
    private String accountJson;

    /**
     * 数据字典的示例
     */
    @TableField
    private String codingJson;

    /**
     * 系统参数的示例
     */
    @TableField
    private String settingsJson;

    /**
     * 部署示例
     */
    @TableField
    private String depJson;

    /**
     * 通知示例
     */
    @TableField
    private String noticeJson;

    /**
     * 所属模板ID
     */
    @TableField
    private String templateId;

}