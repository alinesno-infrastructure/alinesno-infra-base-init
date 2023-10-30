package com.alinesno.infra.base.starter.entity;


import com.alinesno.infra.base.starter.CodeGenBaseEntity;
import com.alinesno.infra.base.starter.constants.GenConstants;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnComment;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnType;
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

    @ColumnType(length = 50)
    @ColumnComment("表模式")
    @TableField("table_schema")
    private String tableSchema;

    /**
     * 编号
     */
    @ColumnType(length = 20)
    @ColumnComment("编号")
    @TableField("table_id")
    private Long tableId;

    /**
     * 表名称
     */
    @ColumnType(length = 200)
    @ColumnComment("表名称")
    @TableField("table_name")
    private String tableName;

    /**
     * 表描述
     */
    @ColumnType(length = 500)
    @ColumnComment("表描述")
    @TableField("table_comment")
    private String tableComment;

    /**
     * 关联父表的表名
     */
    @ColumnType(length = 64)
    @ColumnComment("关联父表的表名")
    @TableField("sub_table_name")
    private String subTableName;

    /**
     * 本表关联父表的外键名
     */
    @ColumnType(length = 64)
    @ColumnComment("本表关联父表的外键名")
    @TableField("sub_table_fk_name")
    private String subTableFkName;

    /**
     * 实体类名称(首字母大写)
     */
    @ColumnType(length = 100)
    @ColumnComment("实体类名称(首字母大写)")
    @TableField("class_name")
    private String className;

    /**
     * 使用的模板（crud单表操作 tree树表操作 sub主子表操作）
     */
    @ColumnType(length = 200)
    @ColumnComment("使用的模板")
    @TableField("tpl_category")
    private String tplCategory;

    /**
     * 生成包路径
     */
    @ColumnType(length = 100)
    @ColumnComment("生成包路径")
    @TableField("package_name")
    private String packageName;

    /**
     * 生成模块名
     */
    @ColumnType(length = 30)
    @ColumnComment("生成模块名")
    @TableField("module_name")
    private String moduleName;

    /**
     * 生成业务名
     */
    @ColumnType(length = 30)
    @ColumnComment("生成业务名")
    @TableField("business_name")
    private String businessName = className;

    /**
     * 页面路径
     */
    @ColumnType(length = 200)
    @ColumnComment("页面路径")
    @TableField("page_path")
    private String pagePath;

    /**
     * 生成功能名
     */
    @ColumnType(length = 50)
    @ColumnComment("生成功能名")
    @TableField("function_name")
    private String functionName;

    /**
     * 生成作者
     */
    @ColumnType(length = 50)
    @ColumnComment("生成作者")
    @TableField("function_author")
    private String functionAuthor;

    /**
     * 生成代码方式（0zip压缩包 1自定义路径）
     */
    @ColumnType(length = 1)
    @ColumnComment("生成代码方式")
    @TableField("gen_type")
    private String genType;

    /**
     * 生成路径（不填默认项目路径）
     */
    @ColumnType(length = 200)
    @ColumnComment("生成路径")
    @TableField("gen_path")
    private String genPath;

    /**
     * 主键信息
     */
    @ColumnType
    @ColumnComment("主键信息")
    @TableField("pk_column")
    private GenTableColumn pkColumn;

    /**
     * 子表信息
     */
    @ColumnType
    @ColumnComment("子表信息")
    @TableField("sub_table")
    private GenTable subTable;

    /**
     * 表列信息
     */
    @ColumnType
    @ColumnComment("表列信息")
    @TableField("columns")
    private List<GenTableColumn> columns;

    /**
     * 菜单id列表
     */
    @ColumnType
    @ColumnComment("菜单id列表")
    @TableField("menu_ids")
    private List<Long> menuIds;

    /**
     * 其它生成选项
     */
    @ColumnType(length = 1000)
    @ColumnComment("其它生成选项")
    @TableField("options")
    private String options;

    /**
     * 树编码字段
     */
    @ColumnType(length = 50)
    @ColumnComment("树编码字段")
    @TableField("tree_code")
    private String treeCode;

    /**
     * 树父编码字段
     */
    @ColumnType(length = 50)
    @ColumnComment("树父编码字段")
    @TableField("tree_parent_code")
    private String treeParentCode;

    /**
     * 树名称字段
     */
    @ColumnType(length = 100)
    @ColumnComment("树名称字段")
    @TableField("tree_name")
    private String treeName;

    /**
     * 上级菜单ID字段
     */
    @ColumnType(length = 50)
    @ColumnComment("上级菜单ID字段")
    @TableField("parent_menu_id")
    private String parentMenuId;

    /**
     * 上级菜单名称字段
     */
    @ColumnType(length = 100)
    @ColumnComment("上级菜单名称字段")
    @TableField("parent_menu_name")
    private String parentMenuName;

    /**
     * 作者名称
     */
    @ColumnType(length = 50)
    @ColumnComment("作者名称")
    @TableField("author")
    private String author;

    /**
     * 用户数据
     */
    @ColumnType
    @ColumnComment("用户数据")
    @TableField("schema_list")
    private List<String> schemaList;

    /**
     * 请求参数
     */
    @ColumnType
    @ColumnComment("请求参数")
    @TableField("params")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
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