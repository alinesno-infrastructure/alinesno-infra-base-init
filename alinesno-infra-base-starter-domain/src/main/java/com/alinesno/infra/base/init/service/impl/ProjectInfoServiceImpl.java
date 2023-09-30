package com.alinesno.infra.base.init.service.impl;

import com.alinesno.infra.base.init.mapper.ProjectInfoMapper;
import com.alinesno.infra.base.starter.entity.ProjectInfoEntity;
import com.alinesno.infra.base.starter.service.ProjectInfoService;
import com.alinesno.infra.common.core.service.impl.IBaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 种子工程项目基本信息 服务实现类
 *
 * @version 1.0.0
 * @since 2023/9/30 17:03:57
 */
@Slf4j
@Service
public class ProjectInfoServiceImpl extends IBaseServiceImpl<ProjectInfoEntity , ProjectInfoMapper> implements ProjectInfoService {
}
