package com.alinesno.infra.base.starter.service;

import com.alinesno.infra.base.starter.api.dto.ProjectInfoDto;
import com.alinesno.infra.base.starter.entity.ApplicationEntity;
import com.alinesno.infra.common.facade.services.IBaseService;

/**
 * 应用构建Service接口
 * 
 * @version 1.0.0
 * @since 2023-09-30
 */
public interface IApplicationService extends IBaseService<ApplicationEntity> {

    /**
     * 添加工程服务
     * @param dto
     */
    void addProject(ProjectInfoDto dto);
}
