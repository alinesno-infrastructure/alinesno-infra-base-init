package com.alinesno.infra.base.starter.mapper;

import com.alinesno.infra.base.starter.entity.PluginEntity;
import com.alinesno.infra.common.facade.mapper.repository.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 项目模块 Mapper 接口
 *
 * @version 1.0.0
 * @author luoxiaodong
 */
@Mapper
public interface PluginMapper extends IBaseMapper<PluginEntity> {
}
