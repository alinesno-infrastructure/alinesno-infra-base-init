package com.alinesno.infra.base.init.entity;

import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 项目模块实体类
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@TableName("build_project_template")
public class ProjectTemplateEntity extends InfraBaseEntity {

    private static final long serialVersionUID = 1L;

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

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTempScope() {
        return tempScope;
    }

    public void setTempScope(String tempScope) {
        this.tempScope = tempScope;
    }

    public String getTempBanner() {
        return tempBanner;
    }

    public void setTempBanner(String tempBanner) {
        this.tempBanner = tempBanner;
    }

    public String getTempName() {
        return tempName;
    }

    public void setTempName(String tempName) {
        this.tempName = tempName;
    }

    public String getTempDesc() {
        return tempDesc;
    }

    public void setTempDesc(String tempDesc) {
        this.tempDesc = tempDesc;
    }

    public String getTempZip() {
        return tempZip;
    }

    public void setTempZip(String tempZip) {
        this.tempZip = tempZip;
    }

    public String getTempTeam() {
        return tempTeam;
    }

    public void setTempTeam(String tempTeam) {
        this.tempTeam = tempTeam;
    }

    public int getInstallCount() {
        return installCount;
    }

    public void setInstallCount(int installCount) {
        this.installCount = installCount;
    }

    public int getGradeCount() {
        return gradeCount;
    }

    public void setGradeCount(int gradeCount) {
        this.gradeCount = gradeCount;
    }

    public String getSupprtVersion() {
        return supprtVersion;
    }

}
