import request from '@/utils/request'
import { parseStrEmpty } from "@/utils/ruoyi";

/**
 * 仓库接口操作
 *
 * @author luoxiaodong
 * @since 1.0.0
 */

// 接口配置项
var prefix = '/api/infra/base/starter/gitInfo/' ;
var managerUrl = {
    datatables : prefix +"datatables" ,
    createUrl: prefix + 'add' ,
    saveUrl: prefix + 'save' ,
    updateUrl: prefix +"modify" ,
    statusUrl: prefix +"changeStatus" ,
    cleanUrl: prefix + "clean",
    detailUrl: prefix +"detail",
    removeUrl: prefix + "delete" ,
    exportUrl: prefix + "exportExcel",
    changeField: prefix + "changeField",
    downloadfile: prefix + "downloadfile"
}

// 查询仓库列表
export function listGit(query) {
    return request({
        url: managerUrl.datatables ,
        method: 'post',
        params: query
    })
}

// 查询仓库详细
export function getGit(id) {
    return request({
        url: managerUrl.detailUrl + '/' + parseStrEmpty(id),
        method: 'get'
    })
}

// 新增仓库
export function addGit(data) {
    return request({
        url: managerUrl.saveUrl ,
        method: 'post',
        data: data
    })
}

// 修改仓库
export function updateGit(data) {
    return request({
        url: managerUrl.updateUrl ,
        method: 'put',
        data: data
    })
}

// 删除仓库
export function delGit(id) {
    return request({
        url: managerUrl.removeUrl + '/' + parseStrEmpty(id),
        method: 'delete'
    })
}
