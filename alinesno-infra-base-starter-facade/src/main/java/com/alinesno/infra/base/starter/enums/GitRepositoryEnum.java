package com.alinesno.infra.base.starter.enums;

import lombok.Getter;

/**
 * 仓库类型
 * @author luoxiaodong
 * @since 1.0.0
 */
@Getter
public enum GitRepositoryEnum {

	GITLAB("gitlab") , 
	GITHUB("github") , 
	GITEE("gitee") , 
	GITEA("gitea") , 
	BITBUCKET("bitbucket") , 
	ALICODE("alicode")  ;
	
	private final String name ;
	
	private GitRepositoryEnum(String name) {
		this.name = name ; 
	}
	
}
