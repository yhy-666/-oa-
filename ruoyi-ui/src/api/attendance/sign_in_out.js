import request from '@/utils/request'

// 查询签到签退列表
export function listSign_in_out(query) {
  return request({
    url: '/attendance/sign_in_out/list',
    method: 'get',
    params: query
  })
}

// 查询签到签退详细
export function getSign_in_out(recordId) {
  return request({
    url: '/attendance/sign_in_out/' + recordId,
    method: 'get'
  })
}

// 新增签到签退
export function addSign_in_out(data) {
  return request({
    url: '/attendance/sign_in_out',
    method: 'post',
    data: data
  })
}

// 修改签到签退
export function updateSign_in_out(data) {
  return request({
    url: '/attendance/sign_in_out',
    method: 'put',
    data: data
  })
}

// 删除签到签退
export function delSign_in_out(recordId) {
  return request({
    url: '/attendance/sign_in_out/' + recordId,
    method: 'delete'
  })
}
