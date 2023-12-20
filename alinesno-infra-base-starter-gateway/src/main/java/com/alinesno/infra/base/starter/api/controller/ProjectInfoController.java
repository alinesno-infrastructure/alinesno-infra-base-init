package com.alinesno.infra.base.starter.api.controller;

import com.alinesno.infra.base.starter.entity.ProjectInfoEntity;
import com.alinesno.infra.base.starter.service.IProjectInfoService;
import com.alinesno.infra.common.facade.pageable.DatatablesPageBean;
import com.alinesno.infra.common.facade.pageable.TableDataInfo;
import com.alinesno.infra.common.web.adapter.rest.BaseController;
import io.swagger.annotations.Api;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 种子工程项目基本信息 前端控制器
 *
 * @version 1.0.0
 * @author luoxiaodong
 */
@Slf4j
@Api(tags = "种子工程项目基本信息")
@RestController
@Scope("prototype")
@RequestMapping("/api/infra/base/starter/projectInfo")
public class ProjectInfoController extends BaseController<ProjectInfoEntity, IProjectInfoService> {

    @Autowired
    private IProjectInfoService projectInfoService;

    /**
     * 获取种子工程项目基本信息的DataTables数据
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
    public IProjectInfoService getFeign() {
        return projectInfoService;
    }
}
