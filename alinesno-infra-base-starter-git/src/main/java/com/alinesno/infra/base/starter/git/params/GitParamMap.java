package com.alinesno.infra.base.starter.git.params;

import org.springframework.stereotype.Component;

@Component
public class GitParamMap {

    /**
     * 通过传递configKey获取到configValue
     * @param configKey 配置键
     * @return 配置值
     */
    public String getConfigValueByKey(String configKey) {
        // 遍历枚举类中的所有枚举值
        for (ConfigEnum configEnum : ConfigEnum.values()) {
            // 如果传入的configKey与枚举值中的configKey相同，则返回对应的configValue
            if (configEnum.getConfigKey().equals(configKey)) {
                return configEnum.getConfigValue();
            }
        }
        // 如果未找到对应配置键的配置值，则返回null或者抛出异常，取决于你的业务需求
        return null;
    }
}
