import request from '@/utils/request'

// 查询申请与审批列表
export function listApprovals(query) {
  return request({
    url: '/office_approvals/approvals/list',
    method: 'get',
    params: query
  })
}

// 查询申请与审批详细
export function getApprovals(applicationId) {
  return request({
    url: '/office_approvals/approvals/' + applicationId,
    method: 'get'
  })
}

// 新增申请与审批
export function addApprovals(data) {
  return request({
    url: '/office_approvals/approvals',
    method: 'post',
    data: data
  })
}

// 修改申请与审批
export function updateApprovals(data) {
  return request({
    url: '/office_approvals/approvals',
    method: 'put',
    data: data
  })
}

// 删除申请与审批
export function delApprovals(applicationId) {
  return request({
    url: '/office_approvals/approvals/' + applicationId,
    method: 'delete'
  })
}
