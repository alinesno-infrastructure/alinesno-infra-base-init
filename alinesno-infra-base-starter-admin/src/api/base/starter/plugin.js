import request from '@/utils/request'
import { parseStrEmpty } from "@/utils/ruoyi";

// 查询用户列表
export function listPlugin(query) {
  return request({
    url: '/api/infra/base/starter/plugin/datatables',
    method: 'post',
    params: query
  })
}

// 查询用户详细
export function getPlugin(userId) {
  return request({
    url: '/api/infra/base/starter/plugin/' + parseStrEmpty(userId),
    method: 'get'
  })
}

// 新增用户
export function addPlugin(data) {
  return request({
    url: '/api/infra/base/starter/plugin',
    method: 'post',
    data: data
  })
}

// 修改用户
export function updatePlugin(data) {
  return request({
    url: '/api/infra/base/starter/plugin',
    method: 'put',
    data: data
  })
}

// 删除用户
export function delPlugin(userId) {
  return request({
    url: '/api/infra/base/starter/plugin/' + userId,
    method: 'delete'
  })
}

// 用户密码重置
export function resetPluginPwd(userId, password) {
  const data = {
    userId,
    password
  }
  return request({
    url: '/api/infra/base/starter/plugin/resetPwd',
    method: 'put',
    data: data
  })
}

// 用户状态修改
export function changePluginStatus(userId, status) {
  const data = {
    userId,
    status
  }
  return request({
    url: '/api/infra/base/starter/plugin/changeStatus',
    method: 'put',
    data: data
  })
}

// 查询用户个人信息
export function getPluginProfile() {
  return request({
    url: '/api/infra/base/starter/plugin/profile',
    method: 'get'
  })
}

// 修改用户个人信息
export function updatePluginProfile(data) {
  return request({
    url: '/api/infra/base/starter/plugin/profile',
    method: 'put',
    data: data
  })
}

// 用户密码重置
export function updatePluginPwd(oldPassword, newPassword) {
  const data = {
    oldPassword,
    newPassword
  }
  return request({
    url: '/api/infra/base/starter/plugin/profile/updatePwd',
    method: 'put',
    params: data
  })
}

// 用户头像上传
export function uploadAvatar(data) {
  return request({
    url: '/api/infra/base/starter/plugin/profile/avatar',
    method: 'post',
    data: data
  })
}

// 查询授权角色
export function getAuthRole(userId) {
  return request({
    url: '/api/infra/base/starter/plugin/authRole/' + userId,
    method: 'get'
  })
}

// 保存授权角色
export function updateAuthRole(data) {
  return request({
    url: '/api/infra/base/starter/plugin/authRole',
    method: 'put',
    params: data
  })
}

// 查询部门下拉树结构
export function deptTreeSelect() {
  return request({
    url: '/api/infra/base/starter/plugin/deptTree',
    method: 'get'
  })
}
