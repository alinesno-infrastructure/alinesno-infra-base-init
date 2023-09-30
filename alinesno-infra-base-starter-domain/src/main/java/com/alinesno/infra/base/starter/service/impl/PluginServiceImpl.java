package com.alinesno.infra.base.starter.service.impl;

import com.alinesno.infra.base.starter.mapper.PluginMapper;
import com.alinesno.infra.base.starter.entity.PluginEntity;
import com.alinesno.infra.base.starter.service.IPluginService;
import com.alinesno.infra.common.core.service.impl.IBaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 项目模块 服务实现类
 *
 * @version 1.0.0
 * @author luoxiaodong
 */
@Slf4j
@Service
public class PluginServiceImpl extends IBaseServiceImpl<PluginEntity , PluginMapper> implements IPluginService {
}
