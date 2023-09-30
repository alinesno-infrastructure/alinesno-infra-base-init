package com.alinesno.infra.base.starter.git.properties;

import com.alinesno.infra.base.starter.bean.GitInfoBean;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "alinesno.base.starter.git")
public class GitProperties {

    private GitInfoBean gitlab;
    private GitInfoBean gitee ;
    private GitInfoBean gitea ;
    private GitInfoBean github ;

}
