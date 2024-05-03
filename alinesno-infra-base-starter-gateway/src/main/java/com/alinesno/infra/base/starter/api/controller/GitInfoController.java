package com.alinesno.infra.base.starter.api.controller;

import com.alinesno.infra.base.starter.entity.GitInfoEntity;
import com.alinesno.infra.base.starter.service.IGitInfoService;
import com.alinesno.infra.common.core.constants.SpringInstanceScope;
import com.alinesno.infra.common.facade.constants.FieldConstants;
import com.alinesno.infra.common.facade.pageable.DatatablesPageBean;
import com.alinesno.infra.common.facade.pageable.TableDataInfo;
import com.alinesno.infra.common.facade.response.AjaxResult;
import com.alinesno.infra.common.web.adapter.login.account.CurrentAccountJwt;
import com.alinesno.infra.common.web.adapter.rest.BaseController;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 【请填写功能名称】Controller
 *
 * @author luoxiaodong
 * @since 1.0.0
 */
@Slf4j
@RestController
@Scope(SpringInstanceScope.PROTOTYPE)
@RequestMapping("/api/infra/base/starter/gitInfo")
public class GitInfoController extends BaseController<GitInfoEntity, IGitInfoService> {

	@Autowired
	private IGitInfoService gitInfoService;

	@Autowired
	private IGitInfoService IGitInfoService;

	/**
	 * 获取仓库信息的DataTables数据
	 *
	 * @param request HttpServletRequest对象
	 * @param model Model对象
	 * @param page DatatablesPageBean对象
	 * @return 包含DataTables数据的TableDataInfo对象
	 */
	@ResponseBody
	@PostMapping("/datatables")
	public TableDataInfo datatables(HttpServletRequest request, Model model, DatatablesPageBean page) {
		log.debug("page = {}", ToStringBuilder.reflectionToString(page));
		return this.toPage(model, getFeign(), page);
	}

	@Override
	public IGitInfoService getFeign() {
		return IGitInfoService;
	}

	@ResponseBody
	@PostMapping("saveEntity")
	public AjaxResult save(Model model, @RequestBody GitInfoEntity entity) {

		log.debug("===> save Entity:{}", ToStringBuilder.reflectionToString(entity));

		if (entity.getGitType().equals("gitlab")) {
			entity.setGitIcon("fab fa-gitlab");
		} else if (entity.getGitType().equals("gitee")) {
			entity.setGitIcon("fab fa-gitkraken");
		} else if (entity.getGitType().equals("github")) {
			entity.setGitIcon("fab fa-github");
		}

		getFeign().save(entity);

		return AjaxResult.success();
	}

	/**
	 * 绑定gitlab账号
	 * 
	 * @param gitType
	 * @return
	 */
	@GetMapping("bindingGit")
	public AjaxResult bindingGit(String gitType) {
		log.debug("git type = {}" , gitType);
		return AjaxResult.success();
	}

}
