package com.ruoyi.reimbursement.mapper;

import java.util.List;
import com.ruoyi.reimbursement.domain.ReimbursementType;

/**
 * 报销类型管理Mapper接口
 * 
 * @author ruoyi
 * @date 2024-03-19
 */
public interface ReimbursementTypeMapper 
{
    /**
     * 查询报销类型管理
     * 
     * @param typeId 报销类型管理主键
     * @return 报销类型管理
     */
    public ReimbursementType selectReimbursementTypeByTypeId(Long typeId);

    /**
     * 查询报销类型管理列表
     * 
     * @param reimbursementType 报销类型管理
     * @return 报销类型管理集合
     */
    public List<ReimbursementType> selectReimbursementTypeList(ReimbursementType reimbursementType);

    /**
     * 新增报销类型管理
     * 
     * @param reimbursementType 报销类型管理
     * @return 结果
     */
    public int insertReimbursementType(ReimbursementType reimbursementType);

    /**
     * 修改报销类型管理
     * 
     * @param reimbursementType 报销类型管理
     * @return 结果
     */
    public int updateReimbursementType(ReimbursementType reimbursementType);

    /**
     * 删除报销类型管理
     * 
     * @param typeId 报销类型管理主键
     * @return 结果
     */
    public int deleteReimbursementTypeByTypeId(Long typeId);

    /**
     * 批量删除报销类型管理
     * 
     * @param typeIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteReimbursementTypeByTypeIds(Long[] typeIds);
}
