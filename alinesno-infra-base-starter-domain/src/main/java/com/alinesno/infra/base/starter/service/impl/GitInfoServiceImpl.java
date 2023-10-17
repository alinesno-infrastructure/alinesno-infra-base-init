package com.alinesno.infra.base.starter.service.impl;

import com.alinesno.infra.base.starter.entity.GitInfoEntity;
import com.alinesno.infra.base.starter.mapper.GitInfoMapper;
import com.alinesno.infra.base.starter.service.IGitInfoService;
import com.alinesno.infra.common.core.service.impl.IBaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 仓库信息 服务实现类
 *
 * @version 1.0.0
 * @author luoxiaodong
 */
@Slf4j
@Service
public class GitInfoServiceImpl extends IBaseServiceImpl<GitInfoEntity , GitInfoMapper> implements IGitInfoService {
}
