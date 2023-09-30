package com.alinesno.infra.base.starter.mapper;

import com.alinesno.infra.base.starter.entity.DatasourceEntity;
import com.alinesno.infra.common.facade.mapper.repository.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 数据库管理 Mapper 接口
 *
 * @version 1.0.0
 * @author luoxiaodong
 */
@Mapper
public interface DatasourceMapper extends IBaseMapper<DatasourceEntity> {
}
