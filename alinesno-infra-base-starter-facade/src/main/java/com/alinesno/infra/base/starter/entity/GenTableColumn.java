package com.alinesno.infra.base.starter.entity;


import cn.hutool.core.util.StrUtil;
import com.alinesno.infra.base.starter.CodeGenBaseEntity;
import com.alinesno.infra.common.facade.wrapper.RpcWrapper;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnComment;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnType;
import com.google.common.base.CaseFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

/**
 * 代码生成业务字段表 gen_table_column
 *
 * @author ruoyi
 * @author luoxiaodong
 *
 * @version 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("starter_gen_column")
public class GenTableColumn extends CodeGenBaseEntity {

    /**
     * 编号
     */
    @ColumnType(length = 20)
    @ColumnComment("编号")
    @TableField("column_id")
    private Long columnId;

    /**
     * 归属表编号
     */
    @ColumnType(length = 20)
    @ColumnComment("归属表编号")
    @TableField("table_id")
    private Long tableId;

    @ColumnType(length = 100)
    @ColumnComment("表模式")
    @TableField("table_schema")
    private String tableSchema;

    /**
     * 列名称
     */
    @ColumnType(length = 200)
    @ColumnComment("列名称")
    @TableField("column_name")
    private String columnName;

    /**
     * 列长度
     */
    @ColumnType(length = 50)
    @ColumnComment("列长度")
    @TableField("column_length")
    private String columnLength;

    /**
     * 小数点保留位数，默认为0时，会去读取该类型实际的小数点默认长度
     */
    @ColumnType(length = 50)
    @ColumnComment("小数点保留位数")
    @TableField("decimal_column_length")
    private String decimalColumnLength;

    /**
     * 列描述
     */
    @ColumnType(length = 500)
    @ColumnComment("列描述")
    @TableField("column_comment")
    private String columnComment;

    /**
     * 列类型
     */
    @ColumnType(length = 100)
    @ColumnComment("列类型")
    @TableField("column_type")
    private String columnType;

    /**
     * JAVA类型
     */
    @ColumnType(length = 500)
    @ColumnComment("JAVA类型")
    @TableField("java_type")
    private String javaType;

    /**
     * JAVA字段名
     */
    @NotBlank(message = "Java属性不能为空")
    @ColumnType(length = 200)
    @ColumnComment("JAVA字段名")
    @TableField("java_field")
    private String javaField;

    /**
     * 是否主键（1是）
     */
    @ColumnType(length = 1)
    @ColumnComment("是否主键")
    @TableField("is_pk")
    private String isPk;

    /**
     * 是否自增（1是）
     */
    @ColumnType(length = 1)
    @ColumnComment("是否自增")
    @TableField("is_increment")
    private String isIncrement;

    /**
     * 是否必填（1是）
     */
    @ColumnType(length = 1)
    @ColumnComment("是否必填")
    @TableField("is_required")
    private String isRequired;

    /**
     * 是否为插入字段（1是）
     */
    @ColumnType(length = 1)
    @ColumnComment("是否为插入字段")
    @TableField("is_insert")
    private String isInsert;

    /**
     * 是否编辑字段（1是）
     */
    @ColumnType(length = 1)
    @ColumnComment("是否编辑字段")
    @TableField("is_edit")
    private String isEdit;

    /**
     * 是否列表字段（1是）
     */
    @ColumnType(length = 1)
    @ColumnComment("是否列表字段")
    @TableField("is_list")
    private String isList;

    /**
     * 是否查询字段（1是）
     */
    @ColumnType(length = 1)
    @ColumnComment("是否查询字段")
    @TableField("is_query")
    private String isQuery;

    /**
     * 查询方式（EQ等于、NE不等于、GT大于、LT小于、LIKE模糊、BETWEEN范围）
     */
    @ColumnType(length = 200)
    @ColumnComment("查询方式")
    @TableField("query_type")
    private String queryType;

    /**
     * 显示类型（input文本框、textarea文本域、select下拉框、checkbox复选框、radio单选框、datetime日期控件、upload上传控件、summernote富文本控件）
     */
    @ColumnType(length = 200)
    @ColumnComment("显示类型")
    @TableField("html_type")
    private String htmlType;

    /**
     * 字典类型
     */
    @ColumnType(length = 200)
    @ColumnComment("字典类型")
    @TableField("dict_type")
    private String dictType;

    /**
     * 排序
     */
    @ColumnType
    @ColumnComment("排序")
    @TableField("sort")
    private Integer sort;

    public void setDecimalColumnLength(String decimalColumnLength) {
        this.decimalColumnLength = decimalColumnLength;
    }


    public void setColumnLength(String columnLength) {
		this.columnLength = columnLength;
	}

	public void setColumnId(Long columnId) {
        this.columnId = columnId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public void setJavaField(String javaField) {
        this.javaField = javaField;
    }

    public String getCapJavaField() {
        return StringUtils.capitalize(javaField);
    }

    public void setIsPk(String isPk) {
        this.isPk = isPk;
    }

    public boolean isPk() {
        return isPk(this.isPk);
    }

    public boolean isPk(String isPk) {
        return isPk != null && StringUtils.equals("1", isPk);
    }

    public void setIsIncrement(String isIncrement) {
        this.isIncrement = isIncrement;
    }

    public boolean isIncrement() {
        return isIncrement(this.isIncrement);
    }

    public boolean isIncrement(String isIncrement) {
        return isIncrement != null && StringUtils.equals("1", isIncrement);
    }

    public void setIsRequired(String isRequired) {
        this.isRequired = isRequired;
    }

    public boolean isRequired() {
        return isRequired(this.isRequired);
    }

    public boolean isRequired(String isRequired) {
        return isRequired != null && StringUtils.equals("1", isRequired);
    }

    public void setIsInsert(String isInsert) {
        this.isInsert = isInsert;
    }

    public boolean isInsert() {
        return isInsert(this.isInsert);
    }

    public boolean isInsert(String isInsert) {
        return isInsert != null && StringUtils.equals("1", isInsert);
    }

    public void setIsEdit(String isEdit) {
        this.isEdit = isEdit;
    }

    public boolean isEdit() {
        return isInsert(this.isEdit);
    }

    public boolean isEdit(String isEdit) {
        return isEdit != null && StringUtils.equals("1", isEdit);
    }

    public void setIsList(String isList) {
        this.isList = isList;
    }

    public boolean isList() {
        return isList(this.isList);
    }

    public boolean isList(String isList) {
        return isList != null && StringUtils.equals("1", isList);
    }

    public void setIsQuery(String isQuery) {
        this.isQuery = isQuery;
    }

    public boolean isQuery() {
        return isQuery(this.isQuery);
    }

    public boolean isQuery(String isQuery) {
        return isQuery != null && StringUtils.equals("1", isQuery);
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }

    public void setHtmlType(String htmlType) {
        this.htmlType = htmlType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public void setTableSchema(String tableSchema) {
        this.tableSchema = tableSchema;
    }


    public boolean isSuperColumn() {
        return isSuperColumn(this.javaField);
    }

    public static boolean isSuperColumn(String javaField) {
        return StringUtils.equalsAnyIgnoreCase(javaField,
                // BaseEntity
                "createBy", "createTime", "updateBy", "updateTime", "remark",
                // TreeEntity
                "parentName", "parentId", "orderNum", "ancestors");
    }

    public boolean isUsableColumn() {
        return isUsableColumn(javaField);
    }

    public static boolean isUsableColumn(String javaField) {
        // isSuperColumn()中的名单用于避免生成多余Domain属性，若某些属性在生成页面时需要用到不能忽略，则放在此处白名单
        return StringUtils.equalsAnyIgnoreCase(javaField, "parentId", "orderNum", "remark");
    }

    public String readConverterExp() {
        String remarks = StringUtils.substringBetween(this.columnComment, "（", "）");
        StringBuffer sb = new StringBuffer();
        if (StringUtils.isNotEmpty(remarks)) {
            for (String value : remarks.split(" ")) {
                if (StringUtils.isNotEmpty(value)) {
                    Object startStr = value.subSequence(0, 1);
                    String endStr = value.substring(1);
                    sb.append("").append(startStr).append("=").append(endStr).append(",");
                }
            }
            return sb.deleteCharAt(sb.length() - 1).toString();
        } else {
            return this.columnComment;
        }
    }

    /**
     * 字段转换
     *
     * @return
     */
    public String commentConverter() {
        if (StrUtil.isNotEmpty(this.columnComment)) {
            return this.columnComment;
        } else {
            return this.javaField;
        }

    }

    public String getterMethod() {
        if (javaField != null) {
            return "get" + CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, javaField);

        }
        return "";
    }

    public String setterMethod() {
        if (javaField != null) {
            return "set" + CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, javaField);

        }
        return "";
    }

    public boolean isDateType() {
        return "Date".equals(javaType);
    }

    public String resolveSearchType() {
        //查询方式（EQ等于、NE不等于、GT大于、LT小于、LIKE模糊、BETWEEN范围）
        return switch (getQueryType()) {
            case "EQ" -> RpcWrapper.EQ;
            case "NE" -> RpcWrapper.NE;
            case "GT" -> RpcWrapper.GT;
            case "LT" -> RpcWrapper.LT;
            default -> RpcWrapper.LIKE;
        };
    }

}