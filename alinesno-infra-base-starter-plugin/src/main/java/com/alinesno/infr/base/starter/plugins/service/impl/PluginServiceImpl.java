package com.alinesno.infr.base.starter.plugins.service.impl;

import com.alinesno.infra.base.starter.api.dto.PluginDto;
import com.alinesno.infra.base.starter.service.IPluginSyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PluginServiceImpl implements IPluginSyncService {

    @Override
    public void syncPlugin(String userId, String gitUrl) throws Exception {

        log.debug("user id = {} , git url = {}" , userId , gitUrl);

    }

    @Override
    public void uploadPluginToProject(PluginDto dto, String gitUrl) {

        log.debug("plugin dto = {} , git url = {}" , dto , gitUrl);

    }

}
