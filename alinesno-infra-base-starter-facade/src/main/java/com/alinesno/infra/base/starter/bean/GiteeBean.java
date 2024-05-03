package com.alinesno.infra.base.starter.bean;

import lombok.Data;

/**
 * Gitee应用对象
 * 
 * @author luoxiaodong
 * @since 1.0.0
 */
@Data
public class GiteeBean {

	private String accessToken;
	private String repo;
	private String path;
	private String owner;

	/**
	 * 最新的文件
	 */
	private byte[] newFileBase64;

}
