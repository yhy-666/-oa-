package com.ruoyi.office_approvals.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.office_approvals.mapper.ApplicationTypeMapper;
import com.ruoyi.office_approvals.domain.ApplicationType;
import com.ruoyi.office_approvals.service.IApplicationTypeService;

/**
 * 申请类型管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-03-20
 */
@Service
public class ApplicationTypeServiceImpl implements IApplicationTypeService 
{
    @Autowired
    private ApplicationTypeMapper applicationTypeMapper;

    /**
     * 查询申请类型管理
     * 
     * @param typeId 申请类型管理主键
     * @return 申请类型管理
     */
    @Override
    public ApplicationType selectApplicationTypeByTypeId(Long typeId)
    {
        return applicationTypeMapper.selectApplicationTypeByTypeId(typeId);
    }

    /**
     * 查询申请类型管理列表
     * 
     * @param applicationType 申请类型管理
     * @return 申请类型管理
     */
    @Override
    public List<ApplicationType> selectApplicationTypeList(ApplicationType applicationType)
    {
        return applicationTypeMapper.selectApplicationTypeList(applicationType);
    }

    /**
     * 新增申请类型管理
     * 
     * @param applicationType 申请类型管理
     * @return 结果
     */
    @Override
    public int insertApplicationType(ApplicationType applicationType)
    {
        return applicationTypeMapper.insertApplicationType(applicationType);
    }

    /**
     * 修改申请类型管理
     * 
     * @param applicationType 申请类型管理
     * @return 结果
     */
    @Override
    public int updateApplicationType(ApplicationType applicationType)
    {
        return applicationTypeMapper.updateApplicationType(applicationType);
    }

    /**
     * 批量删除申请类型管理
     * 
     * @param typeIds 需要删除的申请类型管理主键
     * @return 结果
     */
    @Override
    public int deleteApplicationTypeByTypeIds(Long[] typeIds)
    {
        return applicationTypeMapper.deleteApplicationTypeByTypeIds(typeIds);
    }

    /**
     * 删除申请类型管理信息
     * 
     * @param typeId 申请类型管理主键
     * @return 结果
     */
    @Override
    public int deleteApplicationTypeByTypeId(Long typeId)
    {
        return applicationTypeMapper.deleteApplicationTypeByTypeId(typeId);
    }
}
