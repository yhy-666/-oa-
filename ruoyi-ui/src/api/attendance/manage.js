import request from '@/utils/request'

// 查询考勤记录列表
export function listManage(query) {
  return request({
    url: '/attendance/manage/list',
    method: 'get',
    params: query
  })
}

// 查询考勤记录详细
export function getManage(recordId) {
  return request({
    url: '/attendance/manage/' + recordId,
    method: 'get'
  })
}

// 新增考勤记录
export function addManage(data) {
  return request({
    url: '/attendance/manage',
    method: 'post',
    data: data
  })
}

// 修改考勤记录
export function updateManage(data) {
  return request({
    url: '/attendance/manage',
    method: 'put',
    data: data
  })
}

// 删除考勤记录
export function delManage(recordId) {
  return request({
    url: '/attendance/manage/' + recordId,
    method: 'delete'
  })
}
