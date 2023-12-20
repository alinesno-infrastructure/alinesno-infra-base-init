package com.alinesno.infra.base.starter.api.controller;


import cn.hutool.core.io.IoUtil;
import com.alinesno.infra.base.starter.api.dto.ProjectInfoDto;
import com.alinesno.infra.base.starter.service.IGenTableService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * 初始化种子工程结构
 */
@Slf4j
@RestController
@Scope("prototype")
@RequestMapping("/api/infra/base/starter/seed")
public class StarterSeedController {

    @Autowired
    private IGenTableService genTableService;

    /**
     * 生成种子工程结构
     * @param dto
     * @return
     */
    @PostMapping("/generatorSeed")
    public void generatorSeed(HttpServletResponse response , @RequestBody ProjectInfoDto dto) throws IOException {

        log.debug("dto = {}" , dto);

        byte[] data = genTableService.generatorSeed(dto) ;
        genCode(response,data , dto);

    }


    /**
     * 生成zip文件
     */
    private void genCode(HttpServletResponse response,byte[] data , ProjectInfoDto dto) throws IOException {
        response.reset();

//        response.addHeader("Access-Control-Allow-Origin", origin);
        response.addHeader("Access-Control-Expose-Headers", "Content-Disposition");
        response.setHeader("Content-Disposition", "attachment; filename=\""+ dto.getArtifactId() +".zip\"");

        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");

        IoUtil.write(response.getOutputStream(), false, data);
    }

}
