package com.alinesno.infra.base.starter.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alinesno.infra.base.starter.entity.PluginEntity;
import com.alinesno.infra.base.starter.mapper.PluginMapper;
import com.alinesno.infra.base.starter.service.IPluginService;
import com.alinesno.infra.base.starter.utils.GitUtils;
import com.alinesno.infra.common.core.service.impl.IBaseServiceImpl;
import com.alinesno.infra.common.facade.constants.FieldConstants;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.esotericsoftware.yamlbeans.YamlReader;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 项目模块 服务实现类
 *
 * @version 1.0.0
 * @author luoxiaodong
 */
@Slf4j
@Service
public class PluginServiceImpl extends IBaseServiceImpl<PluginEntity , PluginMapper> implements IPluginService {

    @Override
    public void syncPlugin(Long userId, String gitUrl) throws IOException {

        String path = "/tmp/gitPath/"+ UUID.randomUUID().toString()+"/" ;

        File files = new File(path) ;

        // 清理插件
        this.remove(new QueryWrapper<PluginEntity>().eq(FieldConstants.OPERATOR_ID, userId)) ;

        GitUtils.clone(gitUrl, path);

        List<PluginEntity> es = new ArrayList<PluginEntity>() ;

        for(File sub : files.listFiles()) {

            if(sub.isDirectory()) {

                String directName = sub.getName() ;

                for(File s : sub.listFiles()) {

                    if(s.getName().equals("info.yaml")) {

                        YamlReader reader = new YamlReader(new FileReader(s.getAbsoluteFile()));
                        JSONObject obj = reader.read(JSONObject.class) ;

                        PluginEntity e = new PluginEntity() ;

                        e.setScreen(obj.getString("screen"));
                        e.setIndustry(obj.getString("industry"));
                        e.setType(obj.getString("classes"));
                        e.setTempScope("1");
                        e.setOperatorId(userId);

                        e.setTempZip(directName); // 插件目录名称
                        e.setTempName(obj.getString("name"));
                        e.setTempBanner("");
                        e.setFieldProp(obj.getString("status"));
                        e.setTempDesc(obj.getString("desc"));
                        e.setTempTeam(obj.getString("team"));

                        e.setInstallCount(0);
                        e.setGradeCount(0);

                        es.add(e) ;

                        log.debug("e = {}" , e.toString());
                    }

                }
            }

        }

        this.saveBatch(es) ;
        FileUtils.forceDeleteOnExit(files) ;

    }

}
