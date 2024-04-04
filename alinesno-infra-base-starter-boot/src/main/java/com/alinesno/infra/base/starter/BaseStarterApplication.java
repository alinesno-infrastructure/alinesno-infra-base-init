package com.alinesno.infra.base.starter;

import com.alinesno.infra.common.core.auto.EnableCore;
import com.alinesno.infra.common.facade.enable.EnableActable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 集成一个Java开发示例工具
 * @author luoxiaodong
 * @since 2023年8月3日 上午6:23:43
 */
@EnableCore
@SpringBootApplication
public class BaseStarterApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaseStarterApplication.class, args);
	}

}