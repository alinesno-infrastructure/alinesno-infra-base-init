package com.alinesno.infra.base.starter.service;

import com.alinesno.infra.base.starter.api.dto.PluginDto;

/**
 * 代码插件服务
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
public interface IPluginSyncService {

    /**
     * 同步仓库地址
     */
    void syncPlugin(String userId, String gitUrl) throws Exception ;

    /**
     * 上传插件到指定的工程
     */
    void uploadPluginToProject(PluginDto dto, String gitUrl) ;

}
