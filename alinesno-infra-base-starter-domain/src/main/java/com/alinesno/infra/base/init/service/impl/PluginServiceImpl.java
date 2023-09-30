package com.alinesno.infra.base.init.service.impl;

import com.alinesno.infra.base.init.mapper.PluginMapper;
import com.alinesno.infra.base.starter.entity.PluginEntity;
import com.alinesno.infra.base.starter.service.PluginService;
import com.alinesno.infra.common.core.service.impl.IBaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 项目模块 服务实现类
 *
 * @version 1.0.0
 * @since 2023/9/30 17:03:57
 */
@Slf4j
@Service
public class PluginServiceImpl extends IBaseServiceImpl<PluginEntity , PluginMapper> implements PluginService {
}
