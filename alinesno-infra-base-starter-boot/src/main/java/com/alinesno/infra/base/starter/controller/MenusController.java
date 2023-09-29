package com.alinesno.infra.base.starter.controller;

import com.alinesno.infra.common.facade.response.AjaxResult;
import com.alinesno.infra.common.web.adapter.dto.menus.Menu;
import com.alinesno.infra.common.web.adapter.login.controller.CommonLoginController;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

public class MenusController extends CommonLoginController {

    /**
     * 重写这个路径菜单
     * @return
     */
    @Override
    public AjaxResult getRouters() {

        Menu dashboardMenu = new Menu("Dashboard", "/dashboard", false, "noRedirect", "Layout", true, new Menu.Meta("仪盘表", "dashboard", false, null), List.of(
                new Menu("Dashboard", "dashboard", false, "dashboard/index", new Menu.Meta("概览", "dashboard", false, null)),
                new Menu("Starter", "starter", false, "dashboard/index", new Menu.Meta("工程脚架", "starter", false, null))
        ));

        Menu systemMenu = new Menu("System", "/system", false, "noRedirect", "Layout", true, new Menu.Meta("功能管理", "system", false, null), List.of(
                new Menu("Role", "role", false, "system/role/index", new Menu.Meta("应用列表", "peoples", false, null)),
                new Menu("User", "user", false, "system/user/index", new Menu.Meta("代码生成", "user", false, null)),
                new Menu("Menu", "menu", false, "system/menu/index", new Menu.Meta("模板市场", "tree-table", false, null)),
                new Menu("Dept", "dept", false, "system/dept/index", new Menu.Meta("服务部署", "tree", false, null))
        ));

        Menu monitorMenu = new Menu("Monitor", "/monitor", false, "noRedirect", "Layout", true, new Menu.Meta("资源配置", "monitor", false, null), List.of(
                new Menu("Online", "online", false, "monitor/online/index", new Menu.Meta("数据管理", "online", false, null)),
                new Menu("Job", "job", false, "monitor/job/index", new Menu.Meta("仓库管理", "job", false, null)),
                new Menu("Druid", "druid", false, "monitor/druid/index", new Menu.Meta("持续集成", "druid", false, null))
        ));

        List<Menu> menus = List.of(dashboardMenu , systemMenu, monitorMenu) ;

        return AjaxResult.success(menus);
    }
}
