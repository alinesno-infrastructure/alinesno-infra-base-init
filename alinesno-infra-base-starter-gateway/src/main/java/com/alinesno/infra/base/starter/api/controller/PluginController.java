package com.alinesno.infra.base.starter.api.controller;

import com.alinesno.infra.base.starter.entity.PluginEntity;
import com.alinesno.infra.base.starter.service.IPluginService;
import com.alinesno.infra.common.facade.pageable.DatatablesPageBean;
import com.alinesno.infra.common.facade.pageable.TableDataInfo;
import com.alinesno.infra.common.facade.response.AjaxResult;
import com.alinesno.infra.common.web.adapter.rest.BaseController;
import io.swagger.annotations.Api;
import jakarta.servlet.http.HttpServletRequest;
import jodd.util.StringUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目模块 前端控制器
 *
 * @version 1.0.0
 * @author luoxiaodong
 */
@Slf4j
@Api(tags = "插件模块")
@RestController
@Scope("prototype")
@RequestMapping("/api/infra/base/starter/plugin")
public class PluginController extends BaseController<PluginEntity, IPluginService> {

    @Autowired
    private IPluginService pluginService;

    /**
     * 获取项目模块的DataTables数据
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

    /**
     * 获取过滤模板分类
     * @return
     */
    @PostMapping("/getFilterTemplate")
    public AjaxResult getFilterTemplate() {

        String _SCREEN = "initializr.admin.project.template.screen" ;
        String _INDUSTRY = "initializr.admin.project.template.industry" ;
        String _TYPE = "initializr.admin.project.template.type" ;

        // 初始化 screen 列表并添加示例数据
        List<FilterTemplateBean.ItemName> screen = new ArrayList<>();
        screen.add(new FilterTemplateBean.ItemName("screen_code_1", "旅游预订"));
        screen.add(new FilterTemplateBean.ItemName("screen_code_2", "在线购物"));
        screen.add(new FilterTemplateBean.ItemName("screen_code_3", "社交媒体"));
        screen.add(new FilterTemplateBean.ItemName("screen_code_4", "健身健康"));
        screen.add(new FilterTemplateBean.ItemName("screen_code_5", "在线视频"));

        // 初始化 industry 列表并添加示例数据
        List<FilterTemplateBean.ItemName> industry = new ArrayList<>();
        industry.add(new FilterTemplateBean.ItemName("industry_code_1", "零售"));
        industry.add(new FilterTemplateBean.ItemName("industry_code_2", "娱乐"));
        industry.add(new FilterTemplateBean.ItemName("industry_code_3", "教育"));
        industry.add(new FilterTemplateBean.ItemName("industry_code_5", "旅游"));

        // 初始化 type 列表并添加示例数据
        List<FilterTemplateBean.ItemName> type = new ArrayList<>();
        type.add(new FilterTemplateBean.ItemName("type_code_1", "移动应用"));
        type.add(new FilterTemplateBean.ItemName("type_code_2", "网页应用"));
        type.add(new FilterTemplateBean.ItemName("type_code_3", "桌面应用"));
        type.add(new FilterTemplateBean.ItemName("type_code_4", "社交平台"));
        type.add(new FilterTemplateBean.ItemName("type_code_5", "健身应用"));


        List<FilterTemplateBean> l = new ArrayList<FilterTemplateBean>() ;

        FilterTemplateBean b1 = new FilterTemplateBean() ;
        b1.setCodeValue(_SCREEN) ;
        b1.setName("场景") ;
        b1.setItems(screen) ;

        FilterTemplateBean b2 = new FilterTemplateBean() ;
        b2.setCodeValue(_INDUSTRY) ;
        b2.setName("行业") ;
        b2.setItems(industry) ;

        FilterTemplateBean b3 = new FilterTemplateBean() ;
        b3.setCodeValue(_TYPE) ;
        b3.setName("类型") ;
        b3.setItems(type) ;

        l.add(b1) ;
        l.add(b2) ;
        l.add(b3) ;

        return AjaxResult.success(l) ;
    }

    /**
     * 同步集成模板
     * @param gitUrl 仓库地址
     * @return
     * @throws Exception
     */
    @GetMapping("/syncTemplates")
    public AjaxResult syncTemplates(String gitUrl) throws Exception {

//        if(StringUtil.isEmpty(gitUrl)) {
//            String _SYNC_TEMPLATE_GIT_URL = "alinesno.admin.init.template.git.url" ;
//            ManagerSettingsEntity managerSettings = authorityConfigClient.getConfigByKey(_SYNC_TEMPLATE_GIT_URL) ;
//
//            gitUrl = managerSettings.getConfigValue() ;
//        }

        gitUrl = "https://gitee.com/alinesno-cloud/alinesno-infra-plugins-templates.git" ;
        Assert.notNull(gitUrl , "仓库地址为空.");

        log.debug("gitUrl = {}" , gitUrl) ;

        pluginService.syncPlugin(1L , gitUrl) ;

        return AjaxResult.success() ;

    }

    @Data
    static
    class FilterTemplateBean {
        private String name ;
        private String codeValue ;
        private List<ItemName> items ;

        @Data
        @AllArgsConstructor
        static
        class ItemName {
            private String code ;
            private String name ;
        }
    }

    @Override
    public IPluginService getFeign() {
        return pluginService;
    }
}
