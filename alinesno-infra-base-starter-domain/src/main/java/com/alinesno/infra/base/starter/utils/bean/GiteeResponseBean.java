package com.alinesno.infra.base.starter.utils.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 返回的json对象
 * 
 * @author luoxiaodong
 * @since 1.0.0
 */
@Data
public class GiteeResponseBean {

	private String type;
	private String encoding;
	private int size;
	private String name;
	private String path;
	private String content;
	private String sha;
	private String url;

	@JsonProperty("html_url")
	private String htmlUrl;

	@JsonProperty("download_url")
	private String downloadUrl;

	@JsonProperty("_links")
	private GiteeReponseLinks Links;

}
