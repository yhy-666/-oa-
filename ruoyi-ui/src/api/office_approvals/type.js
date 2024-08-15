import request from '@/utils/request'

// 查询申请类型管理列表
export function listType(query) {
  return request({
    url: '/office_approvals/type/list',
    method: 'get',
    params: query
  })
}

// 查询申请类型管理详细
export function getType(typeId) {
  return request({
    url: '/office_approvals/type/' + typeId,
    method: 'get'
  })
}

// 新增申请类型管理
export function addType(data) {
  return request({
    url: '/office_approvals/type',
    method: 'post',
    data: data
  })
}

// 修改申请类型管理
export function updateType(data) {
  return request({
    url: '/office_approvals/type',
    method: 'put',
    data: data
  })
}

// 删除申请类型管理
export function delType(typeId) {
  return request({
    url: '/office_approvals/type/' + typeId,
    method: 'delete'
  })
}
