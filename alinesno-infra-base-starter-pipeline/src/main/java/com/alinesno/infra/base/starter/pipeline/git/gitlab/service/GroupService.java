package com.alinesno.infra.base.starter.pipeline.git.gitlab.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.lang.exception.RpcServiceRuntimeException;

import com.alinesno.infra.base.starter.api.dto.GroupDto;
import com.alinesno.infra.base.starter.api.dto.MemberDto;
import com.alinesno.infra.base.starter.api.dto.VariableDto;
import com.alinesno.infra.common.facade.pageable.DatatablesPageBean;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.GroupApi;
import org.gitlab4j.api.Pager;
import org.gitlab4j.api.models.AccessLevel;
import org.gitlab4j.api.models.AccessRequest;
import org.gitlab4j.api.models.Group;
import org.gitlab4j.api.models.Member;
import org.gitlab4j.api.models.Project;
import org.gitlab4j.api.models.User;
import org.gitlab4j.api.models.Variable;
import org.gitlab4j.api.models.Visibility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.alinesno.infra.base.starter.pipeline.git.gitlab.common.Gitlab4jClient;

/**
 * 组操作
 * 
 * @author luoxiaodong
 * @since 1.0.0
 */
@Scope("prototype")
@Service
public class GroupService {

	private static final Logger LOGGER = LoggerFactory.getLogger(GroupService.class);
	private Gitlab4jClient gitlab4jclient;

	public Gitlab4jClient getGitlab4jclient() {
		return gitlab4jclient;
	}

	public void setGitlab4jclient(Gitlab4jClient gitlab4jclient) {
		this.gitlab4jclient = gitlab4jclient;
	}

	@SuppressWarnings("deprecation")
	public GroupDto createGroup(GroupDto group, Integer userId) {
		GitLabApi gitLabApi = gitlab4jclient.getGitLabApi(userId);

		try {
			GroupApi groupApi = gitLabApi.getGroupApi();
			groupApi.addGroup(group.getName(), group.getPath(), group.getDescription(), null, null, Visibility.PRIVATE,
					null, group.getRequestAccessEnabled(), group.getParentId(), group.getSharedRunnersMinutesLimit());
			Group group1 = gitLabApi.getGroupApi().getGroup(group.getPath());
			GroupDto groupVO = new GroupDto();
			BeanUtils.copyProperties(group1, groupVO);
			return groupVO;
		} catch (GitLabApiException e) {
			if (e.getHttpStatus() == 404) {
				try {
					GroupDto groupVO = new GroupDto();
					Group group1 = gitLabApi.getGroupApi().getGroup(group.getPath());
					BeanUtils.copyProperties(group1, groupVO);
					return groupVO;
				} catch (GitLabApiException e1) {
					LOGGER.info(e.getMessage());
					throw new RpcServiceRuntimeException(e);
				}
			}
			throw new RpcServiceRuntimeException(e);
		}
	}

	@SuppressWarnings("deprecation")
	public Group updateGroup(Integer groupId, Integer userId, Group group) {
		GitLabApi gitLabApi = gitlab4jclient.getGitLabApi(userId);
		try {
			return gitLabApi.getGroupApi().updateGroup(groupId, group.getName(), group.getPath(),
					group.getDescription(), null, null, group.getVisibility(), null, group.getRequestAccessEnabled(),
					group.getParentId(), group.getSharedRunnersMinutesLimit());
		} catch (GitLabApiException e) {
			throw new RpcServiceRuntimeException(e);
		}
	}

	public void deleteGroup(Integer groupId, Integer userId) {
		GitLabApi gitLabApi = gitlab4jclient.getGitLabApi(userId);
		GroupApi groupApi = gitLabApi.getGroupApi();
		try {
			User user = gitLabApi.getUserApi().getUser(userId);
			List<Member> members = groupApi.getMembers(groupId).stream()
					.filter(t -> user.getUsername().equals(t.getUsername())).collect(Collectors.toList());
			if (members != null && AccessLevel.OWNER.value.equals(members.get(0).getAccessLevel().value)) {
				groupApi.deleteGroup(groupId);
			} else {
				throw new RpcServiceRuntimeException("error.groups.deleteGroup.Owner");
			}
		} catch (GitLabApiException e) {
			throw new RpcServiceRuntimeException(e);
		}
	}

	public List<Project> listProjects(Integer groupId, Integer userId) {
		GitLabApi gitLabApi = gitlab4jclient.getGitLabApi(userId);
		try {
			return gitLabApi.getGroupApi().getProjects(groupId);
		} catch (GitLabApiException e) {
			throw new RpcServiceRuntimeException(e);
		}
	}

	public Group queryGroupByName(String groupName, Integer userId) {
		GitLabApi gitLabApi = gitlab4jclient.getGitLabApi(userId);
		try {
			return gitLabApi.getGroupApi().getGroup(groupName);
		} catch (GitLabApiException e) {
			return null;
		}
	}

	public List<AccessRequest> listAccessRequests(Integer groupId) {
		GitLabApi gitLabApi = gitlab4jclient.getGitLabApi();
		try {
			return gitLabApi.getGroupApi().getAccessRequests(groupId);
		} catch (GitLabApiException e) {
			LOGGER.info("ex: {}", e);
			return null;
		}
	}

	public void denyAccessRequest(Integer groupId, Integer userIdToBeDenied) {
		GitLabApi gitLabApi = gitlab4jclient.getGitLabApi();
		try {
			gitLabApi.getGroupApi().denyAccessRequest(groupId, userIdToBeDenied);
		} catch (GitLabApiException e) {
			if ("404 Not found".equals(e.getMessage())) {
				LOGGER.info("Swallow not found access request exception...");
			} else {
				LOGGER.info("Swallow exception when denying access request of group id {}, userIdToBeDenied {}",
						groupId, userIdToBeDenied);
				LOGGER.info("The exception is {}", e);
			}
		}
	}
	
	public Pager<Group> getGroupPage(DatatablesPageBean page) {
		GitLabApi gitLabApi = gitlab4jclient.getGitLabApi();
		try {
			return gitLabApi.getGroupApi().getGroups(page.getPageSize()) ; 
		} catch (GitLabApiException e) {
			throw new RpcServiceRuntimeException(e);
		}
	}
	
	public List<Group> listGroups() {
		GitLabApi gitLabApi = gitlab4jclient.getGitLabApi();
		try {
			return gitLabApi.getGroupApi().getGroups() ; 
		} catch (GitLabApiException e) {
			throw new RpcServiceRuntimeException(e);
		}
	}

	public List<Group> listGroups(DatatablesPageBean page) {
		GitLabApi gitLabApi = gitlab4jclient.getGitLabApi();
		try {
			return gitLabApi.getGroupApi().getGroups(null , page.getPageNum() ,page.getPageSize()) ; 
		} catch (GitLabApiException e) {
			throw new RpcServiceRuntimeException(e);
		}
	}

	public List<Member> listMember(Integer groupId) {
		GitLabApi gitLabApi = gitlab4jclient.getGitLabApi();
		try {
			return gitLabApi.getGroupApi().getMembers(groupId);
		} catch (GitLabApiException e) {
			throw new RpcServiceRuntimeException(e);
		}
	}

	public Member queryMemberByUserId(Integer groupId, Integer userId) {
		GitLabApi gitLabApi = gitlab4jclient.getGitLabApi();
		try {
			return gitLabApi.getGroupApi().getMember(groupId, userId);
		} catch (GitLabApiException e) {
			return new Member();
		}
	}

	public Member createMember(Integer groupId, MemberDto member) {
		GitLabApi gitLabApi = gitlab4jclient.getGitLabApi();
		try {
			return gitLabApi.getGroupApi().addMember(groupId, member.getId(), member.getAccessLevel());
		} catch (GitLabApiException e) {
			throw new RpcServiceRuntimeException(e);
		}
	}

	public Member updateMember(Integer groupId, MemberDto member) {
		GitLabApi gitLabApi = gitlab4jclient.getGitLabApi();
		try {
			return gitLabApi.getGroupApi().updateMember(groupId, member.getId(), member.getAccessLevel());
		} catch (GitLabApiException e) {
			throw new RpcServiceRuntimeException(e);
		}
	}

	public void deleteMember(Integer groupId, Integer userId) {
		GitLabApi gitLabApi = gitlab4jclient.getGitLabApi();
		try {
			gitLabApi.getGroupApi().removeMember(groupId, userId);
		} catch (GitLabApiException e) {
			throw new RpcServiceRuntimeException(e);
		}
	}

	public List<Variable> getGroupVariable(Integer groupId, Integer userId) {
		try {
			return gitlab4jclient.getGitLabApi(userId).getGroupApi().getVariables(groupId);
		} catch (GitLabApiException e) {
			throw new RpcServiceRuntimeException(e);
		}
	}

	public Variable createVariable(Integer groupId, String key, String value, boolean protecteds, Integer userId) {
		try {
			return gitlab4jclient.getGitLabApi(userId).getGroupApi().createVariable(groupId, key, value, protecteds);
		} catch (GitLabApiException e) {
			throw new RpcServiceRuntimeException(e);
		}
	}

	public void deleteVariable(Integer groupId, String key, Integer userId) {
		try {
			gitlab4jclient.getGitLabApi(userId).getGroupApi().deleteVariable(groupId, key);
		} catch (GitLabApiException e) {
			e.printStackTrace();
		}
	}

	public void batchDeleteVariable(Integer groupId, List<String> keys, Integer userId) {
		keys.forEach(key -> {
			deleteVariable(groupId, key, userId);
		});
	}

	public List<Variable> batchCreateVariable(Integer groupId, List<VariableDto> list, Integer userId) {
		List<Variable> oldlist = getGroupVariable(groupId, userId);
		return list.stream().filter(t -> t.getValue() != null).map(v -> {
			try {
				String key = v.getKey();
				Optional<Variable> optional = oldlist.stream().filter(t -> key.equals(t.getKey())).findFirst();
				if (optional.isPresent() && !optional.get().getKey().isEmpty()) {
					return gitlab4jclient.getGitLabApi(userId).getGroupApi().updateVariable(groupId, v.getKey(),
							v.getValue(), false);
				} else {
					return gitlab4jclient.getGitLabApi(userId).getGroupApi().createVariable(groupId, v.getKey(),
							v.getValue(), false);
				}
			} catch (GitLabApiException e) {
				throw new RpcServiceRuntimeException(e);
			}
		}).collect(Collectors.toList());
	}

}
