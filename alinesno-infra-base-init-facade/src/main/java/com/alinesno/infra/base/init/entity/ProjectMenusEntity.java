package com.alinesno.infra.base.init.entity;

import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 创建示例应用菜单实体类，通过git进行示例功能传输
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@TableName(value = "build_project_menus", autoResultMap = true)
public class ProjectMenusEntity extends InfraBaseEntity {

    private static final long serialVersionUID = 1L;

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

    // getter and setter methods

    public String getMenusJson() {
        return menusJson;
    }

    public void setMenusJson(String menusJson) {
        this.menusJson = menusJson;
    }

    public String getAccountJson() {
        return accountJson;
    }

    public void setAccountJson(String accountJson) {
        this.accountJson = accountJson;
    }

    public String getCodingJson() {
        return codingJson;
    }

    public void setCodingJson(String codingJson) {
        this.codingJson = codingJson;
    }

    public String getSettingsJson() {
        return settingsJson;
    }

    public void setSettingsJson(String settingsJson) {
        this.settingsJson = settingsJson;
    }

    public String getDepJson() {
        return depJson;
    }

    public void setDepJson(String depJson) {
        this.depJson = depJson;
    }

    public String getNoticeJson() {
        return noticeJson;
    }

    public void setNoticeJson(String noticeJson) {
        this.noticeJson = noticeJson;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    @Override
    public String toString() {
        return "BuildProjectMenusEntity [menusJson=" + menusJson + ", accountJson=" + accountJson + ", codingJson="
                + codingJson + ", settingsJson=" + settingsJson + ", depJson=" + depJson + ", noticeJson=" + noticeJson
                + ", templateId=" + templateId + "]";
    }
}
