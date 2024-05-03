package com.alinesno.infra.base.starter.git.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.alinesno.infra.base.starter.api.dto.BingFormGitlabDto;
import com.alinesno.infra.base.starter.api.dto.GroupDto;
import com.alinesno.infra.base.starter.entity.GitInfoEntity;
import com.alinesno.infra.base.starter.enums.GitRepositoryEnum;
import com.alinesno.infra.base.starter.git.params.GitSystemParams;
import com.alinesno.infra.base.starter.git.utils.AccessTokenUtils;
import com.alinesno.infra.base.starter.pipeline.git.gitlab.common.Gitlab4jClient;
import com.alinesno.infra.base.starter.pipeline.git.gitlab.common.Gitlab4jProperties;
import com.alinesno.infra.base.starter.pipeline.git.gitlab.service.GroupService;
import com.alinesno.infra.base.starter.service.IGitInfoService;
import com.alinesno.infra.base.starter.service.git.IGitGroupService;
import com.alinesno.infra.common.core.constants.SpringInstanceScope;
import com.alinesno.infra.common.core.context.SpringContext;
import com.alinesno.infra.common.facade.pageable.DatatablesPageBean;
import com.alinesno.infra.common.facade.pageable.TableDataInfo;
import com.alinesno.infra.common.facade.response.AjaxResult;
import com.alinesno.infra.common.web.adapter.login.account.CurrentAccountJwt;
import com.alinesno.infra.common.web.adapter.rest.SuperController;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.Pager;
import org.gitlab4j.api.models.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import cn.hutool.http.HttpStatus;

/**
 * github操作
 *
 * @author luoxiaodong
 * @since 1.0.0
 */
@Slf4j
@RestController
@Scope(SpringInstanceScope.PROTOTYPE)
@RequestMapping("/api/infra/base/starter/gitOperation")
public class GitOperationController extends SuperController {

	@Autowired
	private IGitInfoService buildGitService;

	@Autowired
	private GitSystemParams gitParams;
	
	@Autowired
	private GroupService groupService ;
	
	@Autowired
	private AccessTokenUtils accessTokenUtils ;

	/**
	 * 获取所有分组
	 * 
	 * @return
	 */
	@GetMapping("/getGitGroups")
	public AjaxResult getGitGroups(String id) {

		GitInfoEntity e = buildGitService.getById(id) ;
		
		List<GroupDto> listGroup = new ArrayList<GroupDto>() ;
		
		if(GitRepositoryEnum.GITLAB.getName().equals(e.getGitType())) { // 处理gitlab
			
			Gitlab4jProperties p = new Gitlab4jProperties() ;
			p.setPrivateToken(e.getAccessPrivateToken()) ; 
			p.setUrl(e.getGitHost()) ; 
			p.setUser(e.getGitUsername()) ; 
			
			Gitlab4jClient client = new Gitlab4jClient() ;
			client.setProperties(p) ;
			groupService.setGitlab4jclient(client) ;
			
			List<Group> list = groupService.listGroups() ; 
			
			for(Group g : list) {
				GroupDto dto = new GroupDto() ; 
				
				dto.setName(g.getName()); 
				dto.setId(g.getId());
				
				listGroup.add(dto) ;
			}
		} else if(GitRepositoryEnum.GITEE.getName().equals(e.getGitType())) { // 处理gitee
		
			String accessToken = e.getAccessPrivateToken() ; 
			IGitGroupService giteaGroupService = (IGitGroupService) SpringContext.getBean("giteeGroupService") ;
			listGroup = giteaGroupService.listGroup(e , accessToken) ; 
			
		} else if(GitRepositoryEnum.GITEA.getName().equals(e.getGitType())) {  // 处理gitea
			
			String accessToken = e.getAccessPrivateToken() ; 
			IGitGroupService giteaGroupService = (IGitGroupService)SpringContext.getBean("giteaGroupService") ;
			listGroup = giteaGroupService.listGroup(e , accessToken) ; 
		}
				
		return AjaxResult.success(listGroup) ; 
	}

	/**
	 * 解绑git
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/unBing")
	public AjaxResult unBing(String id, String gitType) {
		buildGitService.unBingGit(id, gitType);
		return AjaxResult.success();
	}

	/**
	 * 让用户跳转到 GitHub 这里不能加@ResponseBody，因为这里是要跳转而不是返回响应
	 * 另外LoginController也不能用@RestController修饰
	 *
	 * @return 跳转url
	 */
	@GetMapping("/getGithubAuthurl")
	public AjaxResult getGithubAuthurl(String gitType) {

		String url = null;

		// github
		if (GitRepositoryEnum.GITHUB.getName().equals(gitType)) {
			String authUrl = gitParams.getOauthParams().getAuthorizeUrl();
			String clientId = gitParams.getOauthParams().getClientId();
			String redirectUrl = gitParams.getOauthParams().getRedirectUrl();

			url = authUrl + "/login/oauth/authorize" + "?client_id=" + clientId + "&redirect_uri=" + redirectUrl + "&scope=read:user,repo";
		} else if (GitRepositoryEnum.GITEE.getName().equals(gitType)) { // gitee

			String authUrl = gitParams.getGiteeOauthParams().getAuthorizeUrl();
			String clientId = gitParams.getGiteeOauthParams().getClientId();
			String redirectUrl = gitParams.getGiteeOauthParams().getRedirectUrl();

			url = authUrl + "/oauth/authorize" + "?client_id=" + clientId + "&redirect_uri=" + redirectUrl + "&response_type=code";
		} else if (GitRepositoryEnum.GITEA.getName().equals(gitType)) { // gitea

			String authUrl = gitParams.getGiteaOauthParams().getAuthorizeUrl();
			String clientId = gitParams.getGiteaOauthParams().getClientId();
			String redirectUrl = gitParams.getGiteaOauthParams().getRedirectUrl();

			url = authUrl + "/login/oauth/authorize" + "?client_id=" + clientId + "&redirect_uri=" + redirectUrl + "&response_type=code&state=STATE";
		}
		log.info("授权url:{}", url);

		return AjaxResult.success("操作成功.", url);
	}

	/**
	 * 绑定gitlab配置
	 * 
	 * @return
	 */
	@PutMapping("/bingFormGitlab")
	public AjaxResult bingFormGitlab(@RequestBody BingFormGitlabDto dto) {

		log.debug("BingFormGitlabDto = {}", dto.toString());
		long accountId = CurrentAccountJwt.getUserId();
		
		if(GitRepositoryEnum.GITEA.getName().equals(dto.getGitType())) {
	
			
			String userInfo = accessTokenUtils.getGiteaUserInfo(dto.getAccessPrivateToken(), dto.getGitHost() + "/api/v1/user") ; 
			
			buildGitService.bingGitea(accountId, dto , JSONObject.parseObject(userInfo));
		}else if(GitRepositoryEnum.GITLAB.getName().equals(dto.getGitType())) {
			buildGitService.bingGitlab(accountId, dto);
		}
		
		return AjaxResult.success();
	}
	
	@ResponseBody
	@PostMapping("/gitGroupsDatatables")
	public TableDataInfo gitGroupsDatatables(HttpServletRequest request, String selectGit , Model model, DatatablesPageBean page) throws GitLabApiException {
		log.debug("page = {}", ToStringBuilder.reflectionToString(page));

		GitInfoEntity e = buildGitService.getById(selectGit) ;
		
		List<GroupDto> listGroup = new ArrayList<GroupDto>() ; 
		int total = 0 ;
		
		if(GitRepositoryEnum.GITLAB.getName().equals(e.getGitType())) { // 处理gitlab
			
			Gitlab4jProperties p = new Gitlab4jProperties() ; 
			p.setPrivateToken(e.getAccessPrivateToken()) ; 
			p.setUrl(e.getGitHost()) ; 
			p.setUser(e.getGitUsername()) ; 
			
			Gitlab4jClient client = new Gitlab4jClient() ; 
			client.setProperties(p) ;
			groupService.setGitlab4jclient(client) ;
		
			Pager<Group> gP = groupService.getGroupPage(page) ; 
			List<Group> list = groupService.listGroups(page) ; 
			total = gP.getTotalItems() ; 
			
			for(Group g : list) {
				GroupDto dto = new GroupDto() ; 
				dto.setDescription(g.getDescription()) ;
				dto.setName(g.getName()); 
				dto.setId(g.getId());
				
				listGroup.add(dto) ;
			}
		} else if(GitRepositoryEnum.GITEE.getName().equals(e.getGitType())) { // 处理gitee
		
			String accessToken = e.getAccessPrivateToken() ; 
			IGitGroupService giteaGroupService = (IGitGroupService)SpringContext.getBean("giteeGroupService") ;
			
			total = 100 ; 
			listGroup = giteaGroupService.listGroup(e , accessToken) ; 
			
		} else if(GitRepositoryEnum.GITEA.getName().equals(e.getGitType())) {  // 处理gitea
			
			String accessToken = e.getAccessPrivateToken() ; 
			IGitGroupService giteaGroupService = (IGitGroupService)SpringContext.getBean("giteaGroupService") ;

			if(1 == e.getInnerGit()) { // 内置的gitea
				
				String authUrl = gitParams.getGiteaOauthParams().getAuthorizeUrl();
	//			String clientId = gitParams.getGiteaOauthParams().getClientId();
	//			String redirectUrl = gitParams.getGiteaOauthParams().getRedirectUrl();
				
				e.setGitHost(authUrl) ;
			}
			
			listGroup = giteaGroupService.listGroup(e , accessToken) ; 
			
		}

		TableDataInfo tableInfo = new TableDataInfo() ; 
	
		tableInfo.setCode(HttpStatus.HTTP_OK) ;
		tableInfo.setRows(listGroup) ; 
		tableInfo.setTotal(total) ;

		return tableInfo;
	}

}
