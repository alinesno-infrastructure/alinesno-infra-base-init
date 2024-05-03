package com.alinesno.infra.base.starter.pipeline.jenkins;

import com.alinesno.infra.base.starter.pipeline.jenkins.client.JenkinsClient;
import lombok.Data;

/**
 * 工程基类
 * 
 * @author luoxiaodong
 * @since 1.0.0
 */
@Data
public class BaseJenkinsClient {

	protected JenkinsClient client;

}
