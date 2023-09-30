package com.alinesno.infra.base.starter.enums;

import lombok.Getter;

@Getter
public enum PluginTypeEnums {

    COMPONENT("component", "组件"),
    PAGE("page", "前端");

    private final String value;
    private final String desc;

    PluginTypeEnums(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

}
