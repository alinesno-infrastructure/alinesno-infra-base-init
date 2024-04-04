package com.alinesno.infra.base.starter.service;

import com.alinesno.infra.base.starter.entity.PluginEntity;
import com.alinesno.infra.common.facade.services.IBaseService;

import java.io.IOException;

/**
 * 项目模块 服务类
 *
 * @version 1.0.0
 * @author luoxiaodong
 */
public interface IPluginService extends IBaseService<PluginEntity> {

    /**
     * 同步用户插件
     * @param accountId
     * @param gitUrl
     */
    void syncPlugin(Long accountId, String gitUrl) throws IOException;

}
