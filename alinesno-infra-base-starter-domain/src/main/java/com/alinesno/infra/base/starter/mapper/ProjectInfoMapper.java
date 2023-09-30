package com.alinesno.infra.base.starter.mapper;

import com.alinesno.infra.base.starter.entity.ProjectInfoEntity;
import com.alinesno.infra.common.facade.mapper.repository.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 种子工程项目基本信息 Mapper 接口
 *
 * @version 1.0.0
 * @author luoxiaodong
 */
@Mapper
public interface ProjectInfoMapper extends IBaseMapper<ProjectInfoEntity> {
}
