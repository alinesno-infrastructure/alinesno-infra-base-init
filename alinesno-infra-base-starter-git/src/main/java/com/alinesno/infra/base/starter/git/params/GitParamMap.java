package com.alinesno.infra.base.starter.git.params;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * 获取git参数
 */
@Slf4j
@Component
public class GitParamMap {

    @Resource
    private Environment environment;

    /**
     * 通过传递configKey获取到configValue
     * @param configKey 配置键
     * @return 配置值
     */
    public String getConfigValueByKey(String configKey) {

        String configValue = environment.getProperty(configKey , "") ;

        log.debug("configKey = {} , configValue = {}" , configKey , configValue);

        return configValue ;
    }
}
