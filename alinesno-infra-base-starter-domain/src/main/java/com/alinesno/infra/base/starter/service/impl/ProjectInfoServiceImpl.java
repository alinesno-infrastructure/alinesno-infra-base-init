package com.alinesno.infra.base.starter.service.impl;

import com.alinesno.infra.base.starter.entity.ProjectInfoEntity;
import com.alinesno.infra.base.starter.mapper.ProjectInfoMapper;
import com.alinesno.infra.base.starter.service.IProjectInfoService;
import com.alinesno.infra.common.core.service.impl.IBaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 种子工程项目基本信息 服务实现类
 *
 * @version 1.0.0
 * @author luoxiaodong
 */
@Slf4j
@Service
public class ProjectInfoServiceImpl extends IBaseServiceImpl<ProjectInfoEntity , ProjectInfoMapper> implements IProjectInfoService {
}
