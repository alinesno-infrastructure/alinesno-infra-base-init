package com.alinesno.infra.base.starter.service;

import com.alibaba.fastjson.JSONObject;
import com.alinesno.infra.base.starter.bean.GitAccessTokenBean;
import com.alinesno.infra.base.starter.bean.GitInfoBean;
import com.alinesno.infra.base.starter.bean.GitRefreshTokeBean;
import com.alinesno.infra.base.starter.bean.GitUserInfoBean;

public interface IGitHandleService {

    /**
     * 获取git信息
     * @param git
     * @return
     */
    public GitAccessTokenBean getAccessToken(GitInfoBean git) ;

    /**
     * 获取用户信息
     * @param accessToken
     * @param url
     * @return
     */
    public GitUserInfoBean getUserInfo(String accessToken, String url) ;

    /**
     * 刷新Token
     * @param git
     * @return
     */
    public GitRefreshTokeBean refreshToken(GitInfoBean git) ;
}
