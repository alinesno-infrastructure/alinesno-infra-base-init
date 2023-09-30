package com.alinesno.infra.base.init.mapper;

import com.alinesno.infra.base.starter.entity.ApplicationEntity;
import com.alinesno.infra.common.facade.mapper.repository.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 应用构建Mapper接口
 * 
 * @version 1.0.0
 * @since 2023-09-30
 */
@Mapper
public interface ApplicationMapper extends IBaseMapper<ApplicationEntity> {

}
