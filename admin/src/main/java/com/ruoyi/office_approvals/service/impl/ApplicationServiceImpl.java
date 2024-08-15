package com.ruoyi.office_approvals.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.office_approvals.mapper.ApplicationMapper;
import com.ruoyi.office_approvals.domain.Application;
import com.ruoyi.office_approvals.service.IApplicationService;

/**
 * 申请与审批Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-03-20
 */
@Service
public class ApplicationServiceImpl implements IApplicationService 
{
    @Autowired
    private ApplicationMapper applicationMapper;

    /**
     * 查询申请与审批
     * 
     * @param applicationId 申请与审批主键
     * @return 申请与审批
     */
    @Override
    public Application selectApplicationByApplicationId(Long applicationId)
    {
        return applicationMapper.selectApplicationByApplicationId(applicationId);
    }

    /**
     * 查询申请与审批列表
     * 
     * @param application 申请与审批
     * @return 申请与审批
     */
    @Override
    public List<Application> selectApplicationList(Application application)
    {
        return applicationMapper.selectApplicationList(application);
    }

    /**
     * 新增申请与审批
     * 
     * @param application 申请与审批
     * @return 结果
     */
    @Override
    public int insertApplication(Application application)
    {
        application.setCreateTime(DateUtils.getNowDate());
        return applicationMapper.insertApplication(application);
    }

    /**
     * 修改申请与审批
     * 
     * @param application 申请与审批
     * @return 结果
     */
    @Override
    public int updateApplication(Application application)
    {
        return applicationMapper.updateApplication(application);
    }

    /**
     * 批量删除申请与审批
     * 
     * @param applicationIds 需要删除的申请与审批主键
     * @return 结果
     */
    @Override
    public int deleteApplicationByApplicationIds(Long[] applicationIds)
    {
        return applicationMapper.deleteApplicationByApplicationIds(applicationIds);
    }

    /**
     * 删除申请与审批信息
     * 
     * @param applicationId 申请与审批主键
     * @return 结果
     */
    @Override
    public int deleteApplicationByApplicationId(Long applicationId)
    {
        return applicationMapper.deleteApplicationByApplicationId(applicationId);
    }
}
