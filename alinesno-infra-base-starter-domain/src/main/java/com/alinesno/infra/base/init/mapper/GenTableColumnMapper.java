package com.alinesno.infra.base.init.mapper;

import com.alinesno.infra.base.starter.entity.GenTableColumn;
import com.baomidou.mybatisplus.annotation.InterceptorIgnore;

import java.util.List;

/**
 * 业务字段 数据层
 *
 * @author Lion Li
 */
@InterceptorIgnore(dataPermission = "true")
public interface GenTableColumnMapper extends BaseMapperPlus<GenTableColumnMapper, GenTableColumn, GenTableColumn> {
    /**
     * 根据表名称查询列信息
     *
     * @param tableName 表名称
     * @return 列信息
     */
    List<GenTableColumn> selectDbTableColumnsByName(String tableName);

}
