package com.alinesno.infra.base.starter.pipeline.git.gitlab.healthy;

import javax.lang.exception.RpcServiceRuntimeException;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import com.alinesno.infra.base.starter.pipeline.git.gitlab.common.Gitlab4jProperties;

/**
 * 健康状态检查
 * 
 * @author luoxiaodong
 * @since 1.0.0
 */
@Data
@Slf4j
@Component
public class GitlabHealthy implements HealthIndicator {

	private Gitlab4jProperties properties ;

	@Override
    public Health health() {
        log.info("健康检查探测");
        GitLabApi gitLabApi = new GitLabApi(properties.getUrl(), properties.getPrivateToken());
        int errorCode = 0;
        try {
            gitLabApi.getUserApi().getCurrentUser();
        } catch (GitLabApiException e) {
            if (e.getHttpStatus() == 401) {
                errorCode = 401;
            } else if (e.getHttpStatus() == 404) {
                errorCode = 404;
            } else {
                throw new RpcServiceRuntimeException(e);
            }
        }
        if (errorCode == 401 || errorCode == 404) {
            return Health.down().withDetail("Error Code", "the token or the url is error, or the ingress resolution error!").build();
        } else {
            return Health.up().build();
        }
    }
}