package com.ruoyi.reimbursement.service;

import java.util.List;
import com.ruoyi.reimbursement.domain.Reimbursement;

/**
 * 我的申请Service接口
 * 
 * @author ruoyi
 * @date 2024-03-19
 */
public interface IReimbursementService 
{
    /**
     * 查询我的申请
     * 
     * @param reimbursementId 我的申请主键
     * @return 我的申请
     */
    public Reimbursement selectReimbursementByReimbursementId(Long reimbursementId);

    /**
     * 查询我的申请列表
     * 
     * @param reimbursement 我的申请
     * @return 我的申请集合
     */
    public List<Reimbursement> selectReimbursementList(Reimbursement reimbursement);

    /**
     * 新增我的申请
     * 
     * @param reimbursement 我的申请
     * @return 结果
     */
    public int insertReimbursement(Reimbursement reimbursement);

    /**
     * 修改我的申请
     * 
     * @param reimbursement 我的申请
     * @return 结果
     */
    public int updateReimbursement(Reimbursement reimbursement);

    /**
     * 批量删除我的申请
     * 
     * @param reimbursementIds 需要删除的我的申请主键集合
     * @return 结果
     */
    public int deleteReimbursementByReimbursementIds(Long[] reimbursementIds);

    /**
     * 删除我的申请信息
     * 
     * @param reimbursementId 我的申请主键
     * @return 结果
     */
    public int deleteReimbursementByReimbursementId(Long reimbursementId);
}
