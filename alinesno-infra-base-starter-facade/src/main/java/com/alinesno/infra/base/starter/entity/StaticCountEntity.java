package com.alinesno.infra.base.starter.entity;

import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnComment;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnType;
import com.gitee.sunchenbin.mybatis.actable.annotation.TableComment;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 数据统计表实体类
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("starter_static_count")
@TableComment("数据统计实体")
public class StaticCountEntity extends InfraBaseEntity {

    /**
     * 应用统计
     */
    @ColumnType(length = 11)
    @ColumnComment("应用统计")
    @TableField("gen_app_count")
    private int genAppCount;

    /**
     * 数据库统计
     */
    @ColumnType(length = 11)
    @ColumnComment("数据库统计")
    @TableField("gen_database_count")
    private int genDatabaseCount;

}
