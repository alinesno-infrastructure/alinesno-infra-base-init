import request from 'alinesno-infra-ui/utils/request' 

// 获取路由
export const getRouters = () => {
  return request({
    url: '/getRouters',
    method: 'get'
  })
}