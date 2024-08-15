package com.ruoyi.office_approvals.mapper;

import java.util.List;
import com.ruoyi.office_approvals.domain.ApplicationType;

/**
 * 申请类型管理Mapper接口
 * 
 * @author ruoyi
 * @date 2024-03-20
 */
public interface ApplicationTypeMapper 
{
    /**
     * 查询申请类型管理
     * 
     * @param typeId 申请类型管理主键
     * @return 申请类型管理
     */
    public ApplicationType selectApplicationTypeByTypeId(Long typeId);

    /**
     * 查询申请类型管理列表
     * 
     * @param applicationType 申请类型管理
     * @return 申请类型管理集合
     */
    public List<ApplicationType> selectApplicationTypeList(ApplicationType applicationType);

    /**
     * 新增申请类型管理
     * 
     * @param applicationType 申请类型管理
     * @return 结果
     */
    public int insertApplicationType(ApplicationType applicationType);

    /**
     * 修改申请类型管理
     * 
     * @param applicationType 申请类型管理
     * @return 结果
     */
    public int updateApplicationType(ApplicationType applicationType);

    /**
     * 删除申请类型管理
     * 
     * @param typeId 申请类型管理主键
     * @return 结果
     */
    public int deleteApplicationTypeByTypeId(Long typeId);

    /**
     * 批量删除申请类型管理
     * 
     * @param typeIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteApplicationTypeByTypeIds(Long[] typeIds);
}
