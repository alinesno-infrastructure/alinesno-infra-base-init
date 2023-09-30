package com.alinesno.infra.base.starter.api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

/**
 * 组信息
 * @author luoxiaodong
 * @version 1.0.0
 */
@ToString
@Data
public class GroupDto {

	private Integer id;
	@NotNull
	private String name;
	@NotNull
	private String path;
	private String description;
	private Boolean membershipLock;
	private Boolean shareWithGroupLock;
	private boolean visibility;
	private Boolean lfsEnabled;
	private Boolean requestAccessEnabled;
	private Integer parentId;
	private Integer sharedRunnersMinutesLimit;

}