package com.alinesno.infra.base.starter.mapper;

import com.alinesno.infra.base.starter.entity.ProjectMenusEntity;
import com.alinesno.infra.common.facade.mapper.repository.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 创建示例应用菜单 Mapper 接口
 *
 * @version 1.0.0
 * @since 2023/9/30 17:11:56
 */
@Mapper
public interface ProjectMenusMapper extends IBaseMapper<ProjectMenusEntity> {
}
