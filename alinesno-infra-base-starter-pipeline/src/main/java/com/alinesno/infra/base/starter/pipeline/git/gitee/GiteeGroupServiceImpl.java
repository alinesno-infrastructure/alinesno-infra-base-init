package com.alinesno.infra.base.starter.pipeline.git.gitee;

import java.util.List;

import com.alinesno.infra.base.starter.api.dto.GroupDto;
import com.alinesno.infra.base.starter.entity.GitInfoEntity;
import com.alinesno.infra.base.starter.service.git.IGitGroupService;
import org.springframework.stereotype.Service;

/**
 * 处理gitee服务
 * 
 * @author luoxiaodong
 * @since 1.0.0
 */
@Service("giteeGroupService")
public class GiteeGroupServiceImpl implements IGitGroupService {

	@Override
	public List<GroupDto> listGroup(GitInfoEntity e, String accessToken) {
		// TODO Auto-generated method stub
		return null;
	}

}
