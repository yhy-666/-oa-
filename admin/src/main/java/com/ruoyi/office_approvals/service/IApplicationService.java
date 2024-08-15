package com.ruoyi.office_approvals.service;

import java.util.List;
import com.ruoyi.office_approvals.domain.Application;

/**
 * 申请与审批Service接口
 * 
 * @author ruoyi
 * @date 2024-03-20
 */
public interface IApplicationService 
{
    /**
     * 查询申请与审批
     * 
     * @param applicationId 申请与审批主键
     * @return 申请与审批
     */
    public Application selectApplicationByApplicationId(Long applicationId);

    /**
     * 查询申请与审批列表
     * 
     * @param application 申请与审批
     * @return 申请与审批集合
     */
    public List<Application> selectApplicationList(Application application);

    /**
     * 新增申请与审批
     * 
     * @param application 申请与审批
     * @return 结果
     */
    public int insertApplication(Application application);

    /**
     * 修改申请与审批
     * 
     * @param application 申请与审批
     * @return 结果
     */
    public int updateApplication(Application application);

    /**
     * 批量删除申请与审批
     * 
     * @param applicationIds 需要删除的申请与审批主键集合
     * @return 结果
     */
    public int deleteApplicationByApplicationIds(Long[] applicationIds);

    /**
     * 删除申请与审批信息
     * 
     * @param applicationId 申请与审批主键
     * @return 结果
     */
    public int deleteApplicationByApplicationId(Long applicationId);
}
