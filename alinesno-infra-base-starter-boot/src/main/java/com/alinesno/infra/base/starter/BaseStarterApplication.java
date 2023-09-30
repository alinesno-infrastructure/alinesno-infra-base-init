package com.alinesno.infra.base.starter;

import com.alinesno.infra.common.web.adapter.sso.enable.EnableInfraSsoApi;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 集成一个Java开发示例工具
 * @author luoxiaodong
 * @since 2023年8月3日 上午6:23:43
 */
@EnableInfraSsoApi
@MapperScan("com.alinesno.infra.base.starter.mapper")
@SpringBootApplication
public class BaseStarterApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaseStarterApplication.class, args);
	}

}