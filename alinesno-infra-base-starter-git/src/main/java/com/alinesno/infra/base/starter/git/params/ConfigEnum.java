package com.alinesno.infra.base.starter.git.params;

import lombok.Getter;

@Getter
public enum ConfigEnum {
    // Gitea配置信息
    GITEA_AUTH_URL("gitea 认证路径", "alinesno.init.gen.gitea.authurl", "http://git.linesno.com"),
    GITEA_OAUTH_CLIENT_ID("giea oauth clientId", "alinesno.init.gen.gitea.clientId", ""),
    GITEA_OAUTH_CLIENT_SECRETS("gitea oauth Client secrets", "alinesno.init.gen.gitea.clientSecrets", ""),
    GITEA_REDIRECT_URL("gitea 认证回调地址", "alinesno.init.gen.gitea.redirectUrl", "http://alinesno-initializr.beta.linesno.com/gitea/callback"),
    GITEA_USER_INFO("gitea 获取用户信息", "alinesno.init.gen.gitea.userInfo", "http://git.linesno.com/api/v1/user"),

    // Gitee配置信息
    GITEE_AUTH_URL("gitee 认证路径", "alinesno.init.gen.gitee.authurl", "https://gitee.com"),
    GITEE_OAUTH_CLIENT_ID("giee oauth clientId", "alinesno.init.gen.gitee.clientId", ""),
    GITEE_OAUTH_CLIENT_SECRETS("gitee oauth Client secrets", "alinesno.init.gen.gitee.clientSecrets", ""),
    GITEE_REDIRECT_URL("gitee 认证回调地址", "alinesno.init.gen.gitee.redirectUrl", "http://alinesno-initializr.beta.linesno.com/gitee/callback"),
    GITEE_USER_INFO("gitee 获取用户信息", "alinesno.init.gen.gitee.userInfo", "https://gitee.com/api/v5/user"),

    // GitHub配置信息
    GITHUB_AUTH_URL("github 认证路径", "alinesno.init.gen.github.authurl", "https://github.com"),
    GITHUB_OAUTH_CLIENT_ID("github oauth clientId", "alinesno.init.gen.github.clientId", ""),
    GITHUB_OAUTH_CLIENT_SECRETS("github oauth Client secrets", "alinesno.init.gen.github.clientSecrets", ""),
    GITHUB_REDIRECT_URL("github 认证回调地址", "alinesno.init.gen.github.redirectUrl", "http://alinesno-initializr.beta.linesno.com/github/callback"),
    GITHUB_USER_INFO("github获取用户信息", "alinesno.init.gen.github.userInfo", "https://api.github.com/user");

    private final String configName;
    private final String configKey;
    private final String configValue;

    // 构造函数
    ConfigEnum(String configName, String configKey, String configValue) {
        this.configName = configName;
        this.configKey = configKey;
        this.configValue = configValue;
    }

}
