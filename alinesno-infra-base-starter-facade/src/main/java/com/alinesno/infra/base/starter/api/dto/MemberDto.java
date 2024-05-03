package com.alinesno.infra.base.starter.api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 会员信息
 * @author luoxiaodong
 * @since 1.0.0
 */
@Data
public class MemberDto {

	@NotNull
	private Integer id;

	@NotNull
	private Integer accessLevel;

	private String expiresAt;

}