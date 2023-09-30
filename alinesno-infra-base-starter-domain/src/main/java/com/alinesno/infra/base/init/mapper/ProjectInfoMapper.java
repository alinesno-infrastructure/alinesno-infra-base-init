package com.alinesno.infra.base.init.mapper;

import com.alinesno.infra.base.starter.entity.ProjectInfoEntity;
import com.alinesno.infra.common.facade.mapper.repository.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 种子工程项目基本信息 Mapper 接口
 *
 * @version 1.0.0
 * @since 2023/9/30 17:03:57
 */
@Mapper
public interface ProjectInfoMapper extends IBaseMapper<ProjectInfoEntity> {
}
