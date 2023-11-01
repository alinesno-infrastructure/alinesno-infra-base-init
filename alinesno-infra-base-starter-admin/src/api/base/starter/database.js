import request from '@/utils/request'
import { parseStrEmpty } from "@/utils/ruoyi";

// 查询用户列表
export function listDatabase(query) {
  return request({
    url: '/api/infra/base/starter/application/datatables',
    method: 'post',
    params: query
  })
}

// 查询用户详细
export function getDatabase(DatabaseId) {
  return request({
    url: '/api/infra/base/starter/application/' + parseStrEmpty(DatabaseId),
    method: 'get'
  })
}

// 新增用户
export function addDatabase(data) {
  return request({
    url: '/api/infra/base/starter/application',
    method: 'post',
    data: data
  })
}

// 修改用户
export function updateDatabase(data) {
  return request({
    url: '/api/infra/base/starter/application',
    method: 'put',
    data: data
  })
}

// 删除用户
export function delDatabase(DatabaseId) {
  return request({
    url: '/api/infra/base/starter/application/' + DatabaseId,
    method: 'delete'
  })
}

// 用户密码重置
export function resetDatabasePwd(DatabaseId, password) {
  const data = {
    DatabaseId,
    password
  }
  return request({
    url: '/api/infra/base/starter/application/resetPwd',
    method: 'put',
    data: data
  })
}

// 用户状态修改
export function changeDatabaseStatus(DatabaseId, status) {
  const data = {
    DatabaseId,
    status
  }
  return request({
    url: '/api/infra/base/starter/application/changeStatus',
    method: 'put',
    data: data
  })
}

// 查询用户个人信息
export function getDatabaseProfile() {
  return request({
    url: '/api/infra/base/starter/application/profile',
    method: 'get'
  })
}

// 修改用户个人信息
export function updateDatabaseProfile(data) {
  return request({
    url: '/api/infra/base/starter/application/profile',
    method: 'put',
    data: data
  })
}

// 用户密码重置
export function updateDatabasePwd(oldPassword, newPassword) {
  const data = {
    oldPassword,
    newPassword
  }
  return request({
    url: '/api/infra/base/starter/application/profile/updatePwd',
    method: 'put',
    params: data
  })
}

// 用户头像上传
export function uploadAvatar(data) {
  return request({
    url: '/api/infra/base/starter/application/profile/avatar',
    method: 'post',
    data: data
  })
}

// 查询授权角色
export function getAuthRole(DatabaseId) {
  return request({
    url: '/api/infra/base/starter/application/authRole/' + DatabaseId,
    method: 'get'
  })
}

// 保存授权角色
export function updateAuthRole(data) {
  return request({
    url: '/api/infra/base/starter/application/authRole',
    method: 'put',
    params: data
  })
}

// 查询部门下拉树结构
export function deptTreeSelect() {
  return request({
    url: '/api/infra/base/starter/application/deptTree',
    method: 'get'
  })
}
