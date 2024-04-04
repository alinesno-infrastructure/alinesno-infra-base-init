import request from '@/utils/request'
import { parseStrEmpty } from "@/utils/ruoyi";

/**
 * 插件接口操作
 *
 * @author luoxiaodong
 * @since 1.0.0
 */

// 接口配置项
var prefix = '/api/infra/base/starter/seed/' ;
var managerUrl = {
  generatorSeed: prefix +"generatorSeed" ,
}

// 查询插件列表
export function generatorSeed(data) {
  return request({
    url: managerUrl.generatorSeed ,
    method: 'post',
    data: data 
  })
}
