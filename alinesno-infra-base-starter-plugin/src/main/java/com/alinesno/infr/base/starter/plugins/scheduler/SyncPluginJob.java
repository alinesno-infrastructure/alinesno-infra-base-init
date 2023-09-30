package com.alinesno.infr.base.starter.plugins.scheduler;

import com.alinesno.infr.base.starter.plugins.properties.GitPluginProperties;
import com.alinesno.infra.base.starter.service.IPluginSyncService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SyncPluginJob {

    @Autowired
    private IPluginSyncService pluginSyncService ;

    @Autowired
    private GitPluginProperties gitPluginProperties ;

    /**
     * 同步仓库，每两个小时同步一次
     */
    @SneakyThrows
    @Scheduled(cron = "0 0 */2 * * ?")
    public void refreshToken() {

        log.debug("gitPluginProperties = {}" , gitPluginProperties);

        pluginSyncService.syncPlugin(null , null);

    }

}
