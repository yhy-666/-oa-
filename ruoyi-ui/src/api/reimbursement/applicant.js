import request from '@/utils/request'

// 查询我的申请列表
export function listApplicant(query) {
  return request({
    url: '/reimbursement/applicant/list',
    method: 'get',
    params: query
  })
}

// 查询我的申请详细
export function getApplicant(reimbursementId) {
  return request({
    url: '/reimbursement/applicant/' + reimbursementId,
    method: 'get'
  })
}

// 新增我的申请
export function addApplicant(data) {
  return request({
    url: '/reimbursement/applicant',
    method: 'post',
    data: data
  })
}

// 修改我的申请
export function updateApplicant(data) {
  return request({
    url: '/reimbursement/applicant',
    method: 'put',
    data: data
  })
}

// 删除我的申请
export function delApplicant(reimbursementId) {
  return request({
    url: '/reimbursement/applicant/' + reimbursementId,
    method: 'delete'
  })
}
