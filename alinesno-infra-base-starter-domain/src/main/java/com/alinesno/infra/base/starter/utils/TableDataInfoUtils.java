package com.alinesno.infra.base.starter.utils;

import cn.hutool.http.HttpStatus;
import com.alinesno.infra.common.facade.pageable.TableDataInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;

public class TableDataInfoUtils {

    public static <T> TableDataInfo build(IPage<T> page) {

        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.HTTP_OK);
        rspData.setMsg("查询成功");
        rspData.setRows(page.getRecords());
        rspData.setTotal(page.getTotal());

        return rspData;
    }

}
