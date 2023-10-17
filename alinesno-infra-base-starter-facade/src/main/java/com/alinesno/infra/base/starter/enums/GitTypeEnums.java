package com.alinesno.infra.base.starter.enums;

import lombok.Getter;

@Getter
public enum GitTypeEnums {

    NULL("null", "未配置"),
    GITHUB("github", "GitHub"),
    GITEE("gitee", "Gitee"),
    GITLAB("gitlab", "GitLab"),
    GITEA("gitea", "Gitea");

    private final String value;
    private final String desc;

    GitTypeEnums(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

}
