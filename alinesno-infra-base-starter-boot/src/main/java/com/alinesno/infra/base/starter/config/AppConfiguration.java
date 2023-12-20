package com.alinesno.infra.base.starter.config;

import com.alinesno.infra.common.facade.enable.EnableActable;
import com.alinesno.infra.common.web.adapter.sso.enable.EnableInfraSsoApi;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@EnableActable
@EnableInfraSsoApi
@MapperScan({"com.alinesno.infra.base.starter.mapper"})
@Configuration
public class AppConfiguration {

}
