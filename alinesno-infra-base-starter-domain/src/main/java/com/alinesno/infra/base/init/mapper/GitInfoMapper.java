package com.alinesno.infra.base.init.mapper;

import com.alinesno.infra.base.starter.entity.GitInfoEntity;
import com.alinesno.infra.common.facade.mapper.repository.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 仓库信息 Mapper 接口
 *
 * @version 1.0.0
 * @since 2023/9/30 17:03:57
 */
@Mapper
public interface GitInfoMapper extends IBaseMapper<GitInfoEntity> {
}
