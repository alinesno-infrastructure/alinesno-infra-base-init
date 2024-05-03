package com.alinesno.infra.base.starter.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alinesno.infra.base.starter.api.dto.BingFormGitlabDto;
import com.alinesno.infra.base.starter.bean.GiteeBean;
import com.alinesno.infra.base.starter.entity.GitInfoEntity;
import com.alinesno.infra.base.starter.entity.ProjectInfoEntity;
import com.alinesno.infra.base.starter.enums.BingGitEnum;
import com.alinesno.infra.base.starter.enums.GitRepositoryEnum;
import com.alinesno.infra.base.starter.mapper.GitInfoMapper;
import com.alinesno.infra.base.starter.service.IGitInfoService;
import com.alinesno.infra.base.starter.utils.GiteeUtils;
import com.alinesno.infra.base.starter.utils.base.BaseVelocityTemplate;
import com.alinesno.infra.common.core.service.impl.IBaseServiceImpl;
import com.alinesno.infra.common.facade.constants.FieldConstants;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.lang.exception.RpcServiceRuntimeException;
import java.util.Base64;
import java.util.Map;

/**
 * 仓库信息 服务实现类
 *
 * @version 1.0.0
 * @author luoxiaodong
 */
@Slf4j
@Service
public class GitInfoServiceImpl extends IBaseServiceImpl<GitInfoEntity , GitInfoMapper> implements IGitInfoService {

    @Override
    public void bingGithub(long accountId, String githubAccessToken, JSONObject userInfo) {

    }

    @Override
    public void unBingGit(String id, String gitType) {

        GitInfoEntity e = getById(id);

        if (GitRepositoryEnum.GITHUB.getName().equals(e.getGitType())) { // 解绑github

            e.setHasBing(BingGitEnum.NOT_BING.getV());
            this.update(e);

        } else if (GitRepositoryEnum.GITEE.getName().equals(e.getGitType())) { // 解绑gitee

            e.setHasBing(BingGitEnum.NOT_BING.getV());
            this.update(e);

        } else if (GitRepositoryEnum.GITLAB.getName().equals(e.getGitType())) { // 解绑gitlab

            e.setHasBing(BingGitEnum.NOT_BING.getV());
            this.update(e);

        } else if (GitRepositoryEnum.GITEA.getName().equals(e.getGitType())) { // 解绑gitea

            e.setHasBing(BingGitEnum.NOT_BING.getV());
            this.update(e);

        }

    }

    @Override
    public void bingGitee(long accountId, JSONObject accessToken, JSONObject userInfo) {

        QueryWrapper<GitInfoEntity> wrapper = new QueryWrapper<GitInfoEntity>();
        wrapper.eq(FieldConstants.OPERATOR_ID, accountId).eq("git_type", GitRepositoryEnum.GITEE.getName());

        GitInfoEntity e = getOne(wrapper);

        e.setAccessPrivateToken(accessToken.getString("access_token"));
        e.setRefreshToken(accessToken.getString("refresh_token"));
        e.setExpiresIn(accessToken.getIntValue("expires_in"));

        e.setHasBing(BingGitEnum.HAS_BING.getV());
        e.setBingGitInfo(userInfo.toJSONString());

        this.update(e);

    }

    @Override
    public void bingGitlab(long accountId, BingFormGitlabDto dto) {

        String gitlabId = dto.getGitlabId();

        GitInfoEntity e = getById(gitlabId);

        e.setGitHost(dto.getGitHost());
        e.setGitUsername(dto.getUserName());
        e.setAccessPrivateToken(dto.getAccessPrivateToken());
        e.setHasBing(BingGitEnum.HAS_BING.getV());

        this.update(e);

    }

    @Override
    public void bingGitea(long accountId, JSONObject accessToken, JSONObject userInfo) {

        QueryWrapper<GitInfoEntity> wrapper = new QueryWrapper<GitInfoEntity>();
        wrapper.eq(FieldConstants.OPERATOR_ID, accountId).eq("git_type", GitRepositoryEnum.GITEA.getName());

        GitInfoEntity e = getOne(wrapper);

        e.setAccessPrivateToken(accessToken.getString("access_token"));
        e.setRefreshToken(accessToken.getString("refresh_token"));
        e.setExpiresIn(accessToken.getIntValue("expires_in"));

        e.setHasBing(BingGitEnum.HAS_BING.getV());
        e.setBingGitInfo(userInfo.toJSONString());

        this.update(e);
    }

    @Override
    public void bingGitea(long accountId, BingFormGitlabDto dto, JSONObject userInfo) {
        String gitlabId = dto.getGitlabId();

        GitInfoEntity e = getById(gitlabId);

        e.setGitHost(dto.getGitHost());
        e.setGitUsername(dto.getUserName());
        e.setAccessPrivateToken(dto.getAccessPrivateToken());
        e.setHasBing(BingGitEnum.HAS_BING.getV());

        // 获取用户信息

        e.setBingGitInfo(userInfo.toJSONString());

        this.update(e);

    }

    @Override
    public void uploadFunction(Map<String, String> map, ProjectInfoEntity projectInfo) {

        String gitId = projectInfo.getGitId();
        String artifactId = projectInfo.getArtifactId() ;

        Assert.hasLength(gitId , "Git关联为空,请重新生成工程.");

        GitInfoEntity gitEntity = this.getById(gitId);

        JSONObject json = JSONObject.parseObject(gitEntity.getBingGitInfo())  ;
        String owner = json.getString("login")  ;

        // Gitee 仓库
        if (GitRepositoryEnum.GITEE.getName().equals(gitEntity.getGitType())) {

            for(String key : map.keySet()) {

                log.debug("key = {} , content = {}" , key);

                String prefix = artifactId ;
                String fname = key ;

                if(projectInfo.getPackageType().equals("maven")) {  // 整体工程

                    if(projectInfo.getProjectGenType() == null || projectInfo.getProjectGenType().equals("domain")) {  // 兼容domain领域工程
                        if(key.endsWith("Mapper.java") || key.endsWith("ServiceImpl.java") || key.endsWith("Mapper.xml")) { // domain 服务
                            prefix += BaseVelocityTemplate.STARTER_SUFFIX ;
                            fname = "src/" + key ;
                        } else if(key.endsWith("Entity.java") || key.endsWith("Service.java") || key.endsWith("Dto.java")) { // gateway
                            prefix += BaseVelocityTemplate.API_SUFFIX;
                            fname = "src/" + key ;
                        } else if(key.endsWith("Rest.java") || key.endsWith("Controller.java")) { // gateway
                            prefix += BaseVelocityTemplate.GATEWAY_SUFFIX;
                            fname = "src/" + key ;
                        } else if(key.contains("vue")) {
                            prefix += BaseVelocityTemplate.UI_SUFFIX;
                            fname = key.replaceFirst("vue", "src") ;  // 去掉vue/
                        }
                    }else if(projectInfo.getProjectGenType().equals("simple")) {  // 简单工程

                        if(key.contains("vue")) {
                            prefix += BaseVelocityTemplate.UI_SUFFIX;
                            fname = key.replaceFirst("vue", "src") ;  // 去掉vue/
                        }else {  // 直接上传到boot工程
                            prefix += BaseVelocityTemplate.BOOT_SUFFIX ;
                            fname = "src/" + key ;
                        }
                    }

                }else if(projectInfo.getPackageType().equals("npm")) {  // 只包含前端工程
                    prefix = "" ;
                    if(key.contains("vue")) {
                        fname = key.replaceFirst("vue", "src") ;  // 去掉vue/
                    }else {
                        continue ;
                    }
                } else {
                    Assert.isTrue(false , "工程类型不存在.");
                }


                String path = prefix + "/" + fname ;

                log.debug("path = {}" , path);

                GiteeBean gitee = new GiteeBean();

                gitee.setAccessToken(gitEntity.getAccessPrivateToken());

                gitee.setPath(path) ;
                gitee.setRepo(projectInfo.getProjectCode());
                gitee.setOwner(owner);
                gitee.setNewFileBase64(Base64.getEncoder().encode(map.get(key).getBytes()));

                try {
                    GiteeUtils.updateFile(gitee);
                } catch (Exception e) {
                    log.error("文件上传异常:{}", e);
                    if (e.getMessage().contains("401")) {
                        throw new RpcServiceRuntimeException("接口无权限或者Token过期");
                    } else {
                        throw new RpcServiceRuntimeException("文件上传异常");
                    }
                }
            }


        } else if (GitRepositoryEnum.GITEA.getName().equals(gitEntity.getGitType())) {

            // TODO 上传gitea
            Assert.isTrue(false, "当前未支持gitea集成，开发调试中.");

        } else if (GitRepositoryEnum.GITLAB.getName().equals(gitEntity.getGitType())) {

            // TODO 上传gitlab
            Assert.isTrue(false, "当前未支持gitlab集成，开发调试中.");

        }

    }
}
