package com.alinesno.infra.base.starter.service;

import com.alibaba.fastjson.JSONObject;
import com.alinesno.infra.base.starter.api.dto.BingFormGitlabDto;
import com.alinesno.infra.base.starter.entity.GitInfoEntity;
import com.alinesno.infra.base.starter.entity.ProjectInfoEntity;
import com.alinesno.infra.common.facade.services.IBaseService;

import java.util.Map;

/**
 * 仓库信息 服务类
 *
 * @version 1.0.0
 * @author luoxiaodong
 */
public interface IGitInfoService extends IBaseService<GitInfoEntity> {

    /**
     * 编写github
     *
     * @param accountId
     * @param githubAccessToken
     */
    void bingGithub(long accountId, String githubAccessToken, JSONObject userInfo);

    /**
     * 解除git绑定
     *
     * @param id
     */

    void unBingGit(String id, String gitType);

    /**
     * 保存gitee
     *
     * @param accountId
     * @param accessToken
     */
    void bingGitee(long accountId, JSONObject accessToken, JSONObject userInfo);

    /**
     * 绑定gitlab用户信息
     *
     * @param accountId
     * @param dto
     */
    void bingGitlab(long accountId, BingFormGitlabDto dto);

    /**
     * 绑定gitea用户信息
     *
     * @param accountId
     * @param accessToken
     * @param userInfo
     */
    void bingGitea(long accountId, JSONObject accessToken, JSONObject userInfo);

    /**
     * 绑定 gitea
     * @param accountId
     * @param dto
     */
    void bingGitea(long accountId, BingFormGitlabDto dto , JSONObject userInfo);

    /**
     * 上传git代码
     * @param map
     */
    void uploadFunction(Map<String, String> map, ProjectInfoEntity project);

    /**
     * 初始化技术框架
     * @param userId
     */
    void initAccountGitRepository(long userId);
}
