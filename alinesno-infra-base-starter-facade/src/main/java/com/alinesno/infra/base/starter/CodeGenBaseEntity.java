package com.alinesno.infra.base.starter;


import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnComment;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnType;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * Entity基类
 * 
 * @author ruoyi
 * @author luoxiaodong
 *
 * @version 1.0.0
 */
@ToString
@Data
public class CodeGenBaseEntity implements Serializable {

    /** 搜索值 */
    @ColumnType(length = 128)
    @ColumnComment("搜索值")
    @TableField("search_value")
    private String searchValue;

    /** 创建者 */
    @ColumnType(length = 32)
    @ColumnComment("创建者")
    @TableField("create_by")
    private String createBy;

    /** 创建时间 */
    @ColumnType(length = 20)
    @ColumnComment("创建时间")
    @TableField("crate_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 更新者 */
    @ColumnType(length = 32)
    @ColumnComment("更新者")
    @TableField("update_by")
    private String updateBy;

    /** 更新时间 */
    @ColumnType(length = 20)
    @ColumnComment("创建时间")
    @TableField("update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /** 备注 */
    @ColumnType(length = 256)
    @ColumnComment("备注")
    @TableField("remark")
    private String remark;

    /** 请求参数 */
    @TableField(exist = false)
    private Map<String, Object> params;

}
