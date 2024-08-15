import request from '@/utils/request'

// 查询报销类型管理列表
export function listType(query) {
  return request({
    url: '/reimbursement/type/list',
    method: 'get',
    params: query
  })
}

// 查询报销类型管理详细
export function getType(typeId) {
  return request({
    url: '/reimbursement/type/' + typeId,
    method: 'get'
  })
}

// 新增报销类型管理
export function addType(data) {
  return request({
    url: '/reimbursement/type',
    method: 'post',
    data: data
  })
}

// 修改报销类型管理
export function updateType(data) {
  return request({
    url: '/reimbursement/type',
    method: 'put',
    data: data
  })
}

// 删除报销类型管理
export function delType(typeId) {
  return request({
    url: '/reimbursement/type/' + typeId,
    method: 'delete'
  })
}
