package com.alinesno.infr.base.starter.plugins.properties;

import com.alinesno.infra.base.starter.bean.GitInfoBean;
import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ToString
@Data
@Configuration
@ConfigurationProperties(prefix = "alinesno.base.starter.plugin")
public class GitPluginProperties {

    private String gitPluginUrl;

}
