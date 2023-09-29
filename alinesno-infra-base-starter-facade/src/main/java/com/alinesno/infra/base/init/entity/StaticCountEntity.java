package com.alinesno.infra.base.init.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 数据统计表实体类
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@TableName("build_static_count")
public class StaticCountEntity {

    /**
     * 主键ID
     */
    @TableId
    private int id;

    /**
     * 应用统计
     */
    @TableField
    private int genAppCount;

    /**
     * 数据库统计
     */
    @TableField
    private int genDatabaseCount;

    /**
     * 操作用户
     */
    @TableField
    private String operatorId;

    // getter and setter methods

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGenAppCount() {
        return genAppCount;
    }

    public void setGenAppCount(int genAppCount) {
        this.genAppCount = genAppCount;
    }

    public int getGenDatabaseCount() {
        return genDatabaseCount;
    }

    public void setGenDatabaseCount(int genDatabaseCount) {
        this.genDatabaseCount = genDatabaseCount;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }
}
