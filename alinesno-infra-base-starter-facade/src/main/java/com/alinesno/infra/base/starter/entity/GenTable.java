package com.alinesno.infra.base.starter.entity;


import com.alinesno.infra.base.starter.CodeGenBaseEntity;
import com.alinesno.infra.base.starter.constants.GenConstants;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 业务表 gen_table
 *
 * @author ruoyi
 * @author luoxiaodong
 *
 * @version 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("starter_gen_table")
public class GenTable extends CodeGenBaseEntity {

    private String tableSchema;

    /**
     * 编号
     */
    private Long tableId;

    /**
     * 表名称
     */
    private String tableName;

    /**
     * 表描述
     */
    private String tableComment;

    /**
     * 关联父表的表名
     */
    private String subTableName;

    /**
     * 本表关联父表的外键名
     */
    private String subTableFkName;

    /**
     * 实体类名称(首字母大写)
     */
    private String className;

    /**
     * 使用的模板（crud单表操作 tree树表操作 sub主子表操作）
     */
    private String tplCategory;

    /**
     * 生成包路径
     */
    private String packageName;

    /**
     * 生成模块名
     */
    private String moduleName;

    /**
     * 生成业务名
     */
    private String businessName = className;

    /**
     * 页面路径
     */
    private String pagePath;

    /**
     * 生成功能名
     */
    private String functionName;

    /**
     * 生成作者
     */
    @NotBlank(message = "作者不能为空")
    private String functionAuthor;

    /**
     * 生成代码方式（0zip压缩包 1自定义路径）
     */
    private String genType;

    /**
     * 生成路径（不填默认项目路径）
     */
    private String genPath;

    /**
     * 主键信息
     */
    private GenTableColumn pkColumn;

    /**
     * 子表信息
     */
    private GenTable subTable;

    /**
     * 表列信息
     */
    @Valid
    private List<GenTableColumn> columns;

    /*
     * 菜单id列表
     */
    @TableField(exist = false)
    private List<Long> menuIds;

    /**
     * 其它生成选项
     */
    private String options;

    /**
     * 树编码字段
     */
    private String treeCode;

    /**
     * 树父编码字段
     */
    private String treeParentCode;

    /**
     * 树名称字段
     */
    private String treeName;

    /**
     * 上级菜单ID字段
     */
    private String parentMenuId;

    /**
     * 上级菜单名称字段
     */
    private String parentMenuName;

    /**
     * 作者名称
     */
    private String author;
   
    /**
     * 用户数据 
     */
    private List<String> schemaList ;

    /**
     * 请求参数
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @TableField(exist = false)
    private Map<String, Object> params = new HashMap<>();
   
    public void setParentMenuName(String parentMenuName) {
        this.parentMenuName = parentMenuName;
    }

    public boolean isSub() {
        return isSub(this.tplCategory);
    }

    public static boolean isSub(String tplCategory) {
        return tplCategory != null && StringUtils.equals(GenConstants.TPL_SUB, tplCategory);
    }

    public void setTableSchema(String tableSchema) {
        this.tableSchema = tableSchema;
    }

    public boolean isTree() {
        return isTree(this.tplCategory);
    }

    public static boolean isTree(String tplCategory) {
        return tplCategory != null && StringUtils.equals(GenConstants.TPL_TREE, tplCategory);
    }

    public boolean isCrud() {
        return isCrud(this.tplCategory);
    }

    public static boolean isCrud(String tplCategory) {
        return tplCategory != null && StringUtils.equals(GenConstants.TPL_CRUD, tplCategory);
    }

    public boolean isSuperColumn(String javaField) {
        return isSuperColumn(this.tplCategory, javaField);
    }

    public static boolean isSuperColumn(String tplCategory, String javaField) {
        if (isTree(tplCategory)) {
            String[] stars = (String[]) ArrayUtils.addAll(GenConstants.TREE_ENTITY, GenConstants.BASE_ENTITY);
            return StringUtils.equalsAnyIgnoreCase(javaField, stars);
        }
        return StringUtils.equalsAnyIgnoreCase(javaField, GenConstants.BASE_ENTITY);
    }

}