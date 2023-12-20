package com.alinesno.infra.base.starter.api.dto;

import com.alinesno.infra.common.facade.base.BaseDto;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 文件上传模板DTO
 * 
 * @author luoxiaodong
 * @version 1.0.0
 */
@ToString
@EqualsAndHashCode(callSuper = true)
@Data
public class PluginDto extends BaseDto {

	@NotBlank(message = "请选择项目")
	private String projectId; // 项目ID

	@NotBlank(message = "请选择模板")
	private String templateId; // 模板ID

	private String branch; // 上传的分支

}
