package com.alinesno.infra.base.starter.entity;

import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnComment;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnType;
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
    @ColumnType(length = 500)
    @ColumnComment("菜单的json")
    @TableField("menus_json")
    private String menusJson;

    /**
     * 账号的示例json
     */
    @ColumnType(length = 500)
    @ColumnComment("账号的示例json")
    @TableField("account_json")
    private String accountJson;

    /**
     * 数据字典的示例
     */
    @ColumnType(length = 500)
    @ColumnComment("数据字典的示例")
    @TableField("coding_json")
    private String codingJson;

    /**
     * 系统参数的示例
     */
    @ColumnType(length = 500)
    @ColumnComment("系统参数的示例")
    @TableField("settings_json")
    private String settingsJson;

    /**
     * 部署示例
     */
    @ColumnType(length = 500)
    @ColumnComment("部署示例")
    @TableField("dep_json")
    private String depJson;

    /**
     * 通知示例
     */
    @ColumnType(length = 500)
    @ColumnComment("通知示例")
    @TableField("notice_json")
    private String noticeJson;

    /**
     * 所属模板ID
     */
    @ColumnType(length = 100)
    @ColumnComment("所属模板ID")
    @TableField("template_id")
    private String templateId;

}