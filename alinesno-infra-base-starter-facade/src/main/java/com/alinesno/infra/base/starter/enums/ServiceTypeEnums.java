package com.alinesno.infra.base.starter.enums;

import lombok.Getter;

@Getter
public enum ServiceTypeEnums {

    ALL("所有", "all"),
    FRONT_END("前端", "frontEnd"),
    BACK_END("后端", "backEnd");

    private final String description;
    private final String code;

    ServiceTypeEnums(String description, String code) {
        this.description = description;
        this.code = code;
    }

}