package com.alinesno.infra.base.starter.entity;

import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
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
public class StaticCountEntity extends InfraBaseEntity {

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

}
