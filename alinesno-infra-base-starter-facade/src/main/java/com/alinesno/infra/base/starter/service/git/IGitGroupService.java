package com.alinesno.infra.base.starter.service.git;

import com.alinesno.infra.base.starter.api.dto.GroupDto;
import com.alinesno.infra.base.starter.entity.GitInfoEntity;

import java.util.List;

/**
 * 处理git分组
 * 
 * @author luoxiaodong
 * @since 1.0.0
 */
public interface IGitGroupService {

	/**
	 * 列出所有分组
	 * 
	 * @param e
	 * @param accessToken
	 * @return
	 */
	List<GroupDto> listGroup(GitInfoEntity e, String accessToken);

}
