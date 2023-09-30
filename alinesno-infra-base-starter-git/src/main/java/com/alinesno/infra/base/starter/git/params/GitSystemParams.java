package com.alinesno.infra.base.starter.git.params;

import com.alibaba.fastjson.JSONObject;
import com.alinesno.infra.base.starter.bean.GitInfoBean;
import com.alinesno.infra.base.starter.git.properties.GitProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 获取系统参数
 * 
 * @author luoxiaodong
 * @version 1.0.0
 */
@Component
public class GitSystemParams {

	@Autowired
	private GitProperties gitProperties ;

	/**
	 * 获取gitlab认证信息
	 * @return
	 */
	public GitInfoBean getGitlabOauthParams() {
		return gitProperties.getGitlab() ;
	}

	/**
	 * 获取github认证信息
	 * @return
	 */
	public GitInfoBean getGithubOauthParams() {
		return gitProperties.getGithub() ;
	}

	/**
	 * 获取gitee认证授权
	 * @return
	 */
	public GitInfoBean getGiteeOauthParams() {
		return gitProperties.getGitee() ;
	}

	/**
	 * 获取得到Gitea配置
	 * @return
	 */
	public GitInfoBean getGiteaOauthParams() {
		return gitProperties.getGitea();
	}

}
