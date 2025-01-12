package com.alinesno.infra.base.starter.config;

import com.alinesno.infra.common.core.auto.EnableCore;
import com.alinesno.infra.common.extend.datasource.enable.EnableInfraDataScope;
import com.alinesno.infra.common.facade.enable.EnableActable;
import com.alinesno.infra.common.web.adapter.sso.enable.EnableInfraSsoApi;
import com.dtflys.forest.springboot.annotation.ForestScan;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

/**
 * 应用配置
 */
@Slf4j
@EnableInfraDataScope
@EnableActable
@EnableCore
@EnableInfraSsoApi
@MapperScan({"com.alinesno.infra.base.starter.mapper"})
@ForestScan({
        "com.alinesno.infra.common.web.adapter.base.consumer" ,
        "com.alinesno.infra.base.platform.adapter"
})
@Configuration
public class AppConfiguration implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
       log.debug("Application started successfully.");
    }
}
