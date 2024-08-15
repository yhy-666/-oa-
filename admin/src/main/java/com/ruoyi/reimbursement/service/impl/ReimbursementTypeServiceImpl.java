package com.ruoyi.reimbursement.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.reimbursement.mapper.ReimbursementTypeMapper;
import com.ruoyi.reimbursement.domain.ReimbursementType;
import com.ruoyi.reimbursement.service.IReimbursementTypeService;

/**
 * 报销类型管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-03-19
 */
@Service
public class ReimbursementTypeServiceImpl implements IReimbursementTypeService 
{
    @Autowired
    private ReimbursementTypeMapper reimbursementTypeMapper;

    /**
     * 查询报销类型管理
     * 
     * @param typeId 报销类型管理主键
     * @return 报销类型管理
     */
    @Override
    public ReimbursementType selectReimbursementTypeByTypeId(Long typeId)
    {
        return reimbursementTypeMapper.selectReimbursementTypeByTypeId(typeId);
    }

    /**
     * 查询报销类型管理列表
     * 
     * @param reimbursementType 报销类型管理
     * @return 报销类型管理
     */
    @Override
    public List<ReimbursementType> selectReimbursementTypeList(ReimbursementType reimbursementType)
    {
        return reimbursementTypeMapper.selectReimbursementTypeList(reimbursementType);
    }

    /**
     * 新增报销类型管理
     * 
     * @param reimbursementType 报销类型管理
     * @return 结果
     */
    @Override
    public int insertReimbursementType(ReimbursementType reimbursementType)
    {
        return reimbursementTypeMapper.insertReimbursementType(reimbursementType);
    }

    /**
     * 修改报销类型管理
     * 
     * @param reimbursementType 报销类型管理
     * @return 结果
     */
    @Override
    public int updateReimbursementType(ReimbursementType reimbursementType)
    {
        return reimbursementTypeMapper.updateReimbursementType(reimbursementType);
    }

    /**
     * 批量删除报销类型管理
     * 
     * @param typeIds 需要删除的报销类型管理主键
     * @return 结果
     */
    @Override
    public int deleteReimbursementTypeByTypeIds(Long[] typeIds)
    {
        return reimbursementTypeMapper.deleteReimbursementTypeByTypeIds(typeIds);
    }

    /**
     * 删除报销类型管理信息
     * 
     * @param typeId 报销类型管理主键
     * @return 结果
     */
    @Override
    public int deleteReimbursementTypeByTypeId(Long typeId)
    {
        return reimbursementTypeMapper.deleteReimbursementTypeByTypeId(typeId);
    }
}
