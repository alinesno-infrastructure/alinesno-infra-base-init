package com.alinesno.infra.base.starter.service.impl;

import com.alinesno.infra.base.starter.entity.DatasourceEntity;
import com.alinesno.infra.base.starter.mapper.DatasourceMapper;
import com.alinesno.infra.base.starter.service.IDatasourceService;
import com.alinesno.infra.common.core.service.impl.IBaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 数据库管理 服务实现类
 *
 * @version 1.0.0
 * @author luoxiaodong
 */
@Slf4j
@Service
public class DatasourceServiceImpl extends IBaseServiceImpl<DatasourceEntity , DatasourceMapper> implements IDatasourceService {

}
