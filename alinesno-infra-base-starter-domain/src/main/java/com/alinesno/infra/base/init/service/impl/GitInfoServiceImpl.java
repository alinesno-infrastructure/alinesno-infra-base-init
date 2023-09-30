package com.alinesno.infra.base.init.service.impl;

import com.alinesno.infra.base.init.mapper.GitInfoMapper;
import com.alinesno.infra.base.starter.entity.GitInfoEntity;
import com.alinesno.infra.base.starter.service.GitInfoService;
import com.alinesno.infra.common.core.service.impl.IBaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 仓库信息 服务实现类
 *
 * @version 1.0.0
 * @since 2023/9/30 17:03:57
 */
@Slf4j
@Service
public class GitInfoServiceImpl extends IBaseServiceImpl<GitInfoEntity , GitInfoMapper> implements GitInfoService {
}
