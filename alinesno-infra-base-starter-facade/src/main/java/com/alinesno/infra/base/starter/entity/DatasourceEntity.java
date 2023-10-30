package com.alinesno.infra.base.starter.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnComment;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnType;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 数据库管理实体类
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("starter_database")
public class DatasourceEntity extends InfraBaseEntity {

    /**
     * 描述
     */
    @ColumnType(length = 100)
    @ColumnComment("描述")
    @TableField("db_desc")
    private String dbDesc;

    /**
     * 数据库名称
     */
    @ColumnType(length = 50)
    @ColumnComment("数据库名称")
    @TableField("db_name")
    private String dbName;

    /**
     * 数据库连接
     */
    @ColumnType(length = 200)
    @ColumnComment("数据库连接")
    @TableField("db_url")
    private String dbUrl;

    /**
     * 数据库连接地址
     */
    @ColumnType(length = 200)
    @ColumnComment("数据库连接地址")
    @TableField("jdbc_url")
    private String jdbcUrl;

    /**
     * 数据库用户名
     */
    @ColumnType(length = 50)
    @ColumnComment("数据库用户名")
    @TableField("db_username")
    private String dbUsername;

    /**
     * 数据库密码
     */
    @ColumnType(length = 50)
    @ColumnComment("数据库密码")
    @TableField("db_passwd")
    private String dbPasswd;

    /**
     * 数据库连接端口
     */
    @ColumnType(length = 10)
    @ColumnComment("数据库连接端口")
    @TableField("db_port")
    private String dbPort;

    /**
     * 数据库类型
     */
    @ColumnType(length = 50)
    @ColumnComment("数据库类型")
    @TableField("db_type")
    private String dbType;

    /**
     * 作者名
     */
    @ColumnType(length = 50)
    @ColumnComment("作者名")
    @TableField("author")
    private String author;

    /**
     * 生成包路径
     */
    @ColumnType(length = 200)
    @ColumnComment("生成包路径")
    @TableField("package_path")
    private String packagePath;

    /**
     * 模块名
     */
    @ColumnType(length = 30)
    @ColumnComment("模块名")
    @TableField("module_name")
    private String moduleName;

    /**
     * 生成类型
     */
    @ColumnType(length = 20)
    @ColumnComment("生成类型")
    @TableField("create_type")
    private String createType;
}