package com.alinesno.infra.base.starter.git.properties;

import com.alinesno.infra.base.starter.bean.GitInfoBean;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "alinesno.base.starter.git")
public class GitProperties {

    private GitInfoBean gitlab;
    private GitInfoBean gitee ;
    private GitInfoBean gitea ;
    private GitInfoBean github ;

}
