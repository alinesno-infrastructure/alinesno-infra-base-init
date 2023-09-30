package com.alinesno.infra.base.starter.service.impl;

import com.alinesno.infra.base.starter.mapper.ApplicationMapper;
import com.alinesno.infra.base.starter.entity.ApplicationEntity;
import com.alinesno.infra.base.starter.service.IApplicationService;
import com.alinesno.infra.common.core.service.impl.IBaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 应用构建Service业务层处理
 * 
 * @version 1.0.0
 * @since 2023-09-30
 */
@Slf4j
@Service
public class ApplicationServiceImpl extends IBaseServiceImpl<ApplicationEntity, ApplicationMapper> implements IApplicationService {

}
