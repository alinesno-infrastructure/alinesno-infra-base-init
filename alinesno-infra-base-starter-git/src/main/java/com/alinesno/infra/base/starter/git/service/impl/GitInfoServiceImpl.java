package com.alinesno.infra.base.starter.git.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alinesno.infra.base.starter.bean.GitAccessTokenBean;
import com.alinesno.infra.base.starter.bean.GitInfoBean;
import com.alinesno.infra.base.starter.bean.GitRefreshTokeBean;
import com.alinesno.infra.base.starter.bean.GitUserInfoBean;
import com.alinesno.infra.base.starter.enums.GitTypeEnums;
import com.alinesno.infra.base.starter.git.utils.AccessTokenUtils;
import com.alinesno.infra.base.starter.service.IGitInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GitInfoServiceImpl implements IGitInfoService {

    @Autowired
    private AccessTokenUtils accessTokenUtils ;

    @Override
    public GitAccessTokenBean getAccessToken(GitInfoBean git) {

        log.debug("git info bean = {}" , git);

        String type = git.getType() ;
        String code = git.getCode() ;
        String authorizeUrl = git.getAuthUrl() ;
        String clientId = git.getClientId() ;
        String clientSecret = git.getClientSecrets() ;
        String redirectUri = git.getRedirectUrl() ;

        if(GitTypeEnums.GITEE.getValue().equals(type)){

            accessTokenUtils.getGiteeAccessToken(code , authorizeUrl, clientId , clientSecret , redirectUri) ;

        }else if(GitTypeEnums.GITEA.getValue().equals(type)){

            accessTokenUtils.getGiteaAccessToken(code , authorizeUrl, clientId , clientSecret) ;

        }else if(GitTypeEnums.GITHUB.getValue().equals(type)){

            String json = accessTokenUtils.getGithubAccessToken(code, authorizeUrl, clientId, clientSecret)  ;
            JSONObject.parseObject(json) ;

        }else if(GitTypeEnums.GITLAB.getValue().equals(type)){

            accessTokenUtils.getGitlabAccessToken(code, authorizeUrl, clientId, clientSecret)  ;
        }

        return new GitAccessTokenBean();
    }

    @Override
    public GitUserInfoBean getUserInfo(String accessToken, String url) {
        return null;
    }

    @Override
    public GitRefreshTokeBean refreshToken(GitInfoBean git) {
        return null;
    }
}
