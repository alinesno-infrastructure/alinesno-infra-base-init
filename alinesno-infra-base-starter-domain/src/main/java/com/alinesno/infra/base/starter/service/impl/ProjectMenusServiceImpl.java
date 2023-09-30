package com.alinesno.infra.base.starter.service.impl;

import com.alinesno.infra.base.starter.mapper.ProjectMenusMapper;
import com.alinesno.infra.base.starter.entity.ProjectMenusEntity;
import com.alinesno.infra.base.starter.service.IProjectMenusService;
import com.alinesno.infra.common.core.service.impl.IBaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 创建示例应用菜单 服务实现类
 *
 * @version 1.0.0
 * @since 2023/9/30 17:11:56
 */
@Slf4j
@Service
public class ProjectMenusServiceImpl extends IBaseServiceImpl<ProjectMenusEntity , ProjectMenusMapper> implements IProjectMenusService {
}
