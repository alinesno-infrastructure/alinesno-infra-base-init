package com.alinesno.infra.base.starter.api.dto;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 参数值
 *
 * @author luoxiaodong
 * @since 1.0.0
 */
@Data
public class VariableDto {

    @NotNull
    private String key;

    @NotNull
    private String value;

}