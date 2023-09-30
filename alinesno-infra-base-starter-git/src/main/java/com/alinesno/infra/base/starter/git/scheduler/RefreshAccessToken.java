package com.alinesno.infra.base.starter.git.scheduler;

import com.alinesno.infra.base.starter.git.utils.AccessTokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

//	@Autowired
//	private IBuildGitService buildGitService ;

	@Autowired
	private AccessTokenUtils tokenUtil ;

	/**
	 * 刷新token，每两个小时刷新一次
	 */
	@Scheduled(cron = "0 0 */2 * * ?")
	public void refreshToken() {

       log.debug("刷新token");

//		List<BuildGitEntity> list = buildGitService.findAll() ;
//
//		for(BuildGitEntity e : list) {
//
//			if(e.getRefreshToken() == null) {
//				continue ;
//			}
//
//			JSONObject accessToken  = null ;
//
//			if(GitRepositoryEnum.GITEE.getName().equals(e.getGitType())) {
//				accessToken = tokenUtil.refreshGiteeToken(e) ;
//
//				if(accessToken != null) {
//
//					e.setAccessPrivateToken(accessToken.getString("access_token"));
//					e.setRefreshToken(accessToken.getString("refresh_token"));
//					e.setExpiresIn(accessToken.getIntValue("expires_in"));
//
//					buildGitService.update(e) ;
//
//				}
//			}
//		}

	}

}
