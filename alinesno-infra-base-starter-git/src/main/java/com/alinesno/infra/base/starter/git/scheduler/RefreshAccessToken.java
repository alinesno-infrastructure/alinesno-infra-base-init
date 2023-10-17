package com.alinesno.infra.base.starter.git.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时刷新accessToken
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@Slf4j
@EnableScheduling
@Component
public class RefreshAccessToken {

	/**
	 * 刷新token，每两个小时刷新一次
	 */
	@Scheduled(cron = "0 0 */2 * * ?")
	public void refreshToken() {

       log.debug("刷新token");

	}

}
