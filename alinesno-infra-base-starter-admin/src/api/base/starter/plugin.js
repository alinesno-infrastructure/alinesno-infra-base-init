import request from '@/utils/request'
import { parseStrEmpty } from "@/utils/ruoyi";

/**
 * 插件接口操作
 *
 * @author luoxiaodong
 * @since 1.0.0
 */

// 接口配置项
var prefix = '/api/infra/base/starter/plugin/' ;
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

// 查询插件列表
export function listPlugin(query) {
  return request({
    url: managerUrl.datatables ,
    method: 'post',
    params: query
  })
}

// 查询插件详细
export function getPlugin(pluginId) {
  return request({
    url: managerUrl.detailUrl + '/' + parseStrEmpty(pluginId),
    method: 'get'
  })
}

// 新增插件
export function addPlugin(data) {
  return request({
    url: managerUrl.saveUrl ,
    method: 'post',
    data: data
  })
}

// 修改插件
export function updatePlugin(data) {
  return request({
    url: managerUrl.updateUrl ,
    method: 'put',
    data: data
  })
}

// 删除插件
export function delPlugin(pluginId) {
  return request({
    url: managerUrl.removeUrl + '/' + parseStrEmpty(pluginId),
    method: 'delete'
  })
}
