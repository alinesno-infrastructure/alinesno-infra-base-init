package com.alinesno.infra.base.init.entity;

import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import cn.afterturn.easypoi.excel.annotation.Excel;

/**
 * 数据库管理实体类
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@TableName("build_database")
public class DatasourceEntity extends InfraBaseEntity {

    /**
     * 描述
     */
    @TableField("db_desc")
    @Excel(name = "描述")
    private String dbDesc;

    /**
     * 数据库名称
     */
    @TableField("db_name")
    @Excel(name = "数据库名称")
    private String dbName;

    /**
     * 数据库连接
     */
    @Excel(name = "数据库连接")
    private String dbUrl;

    /**
     * 数据库连接地址
     */
    @Excel(name = "数据库连接地址")
    private String jdbcUrl;

    /**
     * 数据库用户名
     */
    @Excel(name = "数据库用户名")
    private String dbUsername;

    /**
     * 数据库密码
     */
    @Excel(name = "数据库密码")
    private String dbPasswd;

    /**
     * 数据库连接端口
     */
    @TableField("db_port")
    @Excel(name = "数据库连接端口")
    private String dbPort;

    /**
     * 数据库类型
     */
    @TableField("db_type")
    @Excel(name = "数据库类型")
    private String dbType;

    /**
     * 作者名
     */
    @TableField("author")
    @Excel(name = "作者名")
    private String author;

    /**
     * 生成包路径
     */
    @TableField("package_path")
    @Excel(name = "生成包路径")
    private String packagePath;

    /**
     * 模块名
     */
    @TableField("module_name")
    @Excel(name = "模块名")
    private String moduleName;

    /**
     * 生成类型
     */
    @TableField("create_type")
    @Excel(name = "生成类型")
    private String createType; // 生成类型(Java|Vue|Html|PHP)

    // getter and setter methods

    public String getDbDesc() {
        return dbDesc;
    }

    public void setDbDesc(String dbDesc) {
        this.dbDesc = dbDesc;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getDbUsername() {
        return dbUsername;
    }

    public void setDbUsername(String dbUsername) {
        this.dbUsername = dbUsername;
    }
}
