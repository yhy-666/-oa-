package com.ruoyi.reimbursement.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.reimbursement.mapper.ReimbursementMapper;
import com.ruoyi.reimbursement.domain.Reimbursement;
import com.ruoyi.reimbursement.service.IReimbursementService;

/**
 * 我的申请Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-03-19
 */
@Service
public class ReimbursementServiceImpl implements IReimbursementService 
{
    @Autowired
    private ReimbursementMapper reimbursementMapper;

    /**
     * 查询我的申请
     * 
     * @param reimbursementId 我的申请主键
     * @return 我的申请
     */
    @Override
    public Reimbursement selectReimbursementByReimbursementId(Long reimbursementId)
    {
        return reimbursementMapper.selectReimbursementByReimbursementId(reimbursementId);
    }

    /**
     * 查询我的申请列表
     * 
     * @param reimbursement 我的申请
     * @return 我的申请
     */
    @Override
    public List<Reimbursement> selectReimbursementList(Reimbursement reimbursement)
    {
        return reimbursementMapper.selectReimbursementList(reimbursement);
    }

    /**
     * 新增我的申请
     * 
     * @param reimbursement 我的申请
     * @return 结果
     */
    @Override
    public int insertReimbursement(Reimbursement reimbursement)
    {
        return reimbursementMapper.insertReimbursement(reimbursement);
    }

    /**
     * 修改我的申请
     * 
     * @param reimbursement 我的申请
     * @return 结果
     */
    @Override
    public int updateReimbursement(Reimbursement reimbursement)
    {
        return reimbursementMapper.updateReimbursement(reimbursement);
    }

    /**
     * 批量删除我的申请
     * 
     * @param reimbursementIds 需要删除的我的申请主键
     * @return 结果
     */
    @Override
    public int deleteReimbursementByReimbursementIds(Long[] reimbursementIds)
    {
        return reimbursementMapper.deleteReimbursementByReimbursementIds(reimbursementIds);
    }

    /**
     * 删除我的申请信息
     * 
     * @param reimbursementId 我的申请主键
     * @return 结果
     */
    @Override
    public int deleteReimbursementByReimbursementId(Long reimbursementId)
    {
        return reimbursementMapper.deleteReimbursementByReimbursementId(reimbursementId);
    }
}
