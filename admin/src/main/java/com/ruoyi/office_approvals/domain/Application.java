package com.ruoyi.office_approvals.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 申请与审批对象 application
 * 
 * @author ruoyi
 * @date 2024-03-20
 */
public class Application extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 申请ID */
    @Excel(name = "申请ID")
    private Long applicationId;

    /** 申请类型 */
    @Excel(name = "申请类型")
    private String type;

    /** 申请人ID */
    @Excel(name = "申请人ID")
    private Long applicantId;

    /** 审核人ID */
    @Excel(name = "审核人ID")
    private Long approverId;

    /** 申请状态 */
    @Excel(name = "申请状态")
    private String status;

    /** 申请内容 */
    @Excel(name = "申请内容")
    private String content;

    public void setApplicationId(Long applicationId) 
    {
        this.applicationId = applicationId;
    }

    public Long getApplicationId() 
    {
        return applicationId;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setApplicantId(Long applicantId) 
    {
        this.applicantId = applicantId;
    }

    public Long getApplicantId() 
    {
        return applicantId;
    }
    public void setApproverId(Long approverId) 
    {
        this.approverId = approverId;
    }

    public Long getApproverId() 
    {
        return approverId;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("applicationId", getApplicationId())
            .append("type", getType())
            .append("applicantId", getApplicantId())
            .append("approverId", getApproverId())
            .append("createTime", getCreateTime())
            .append("status", getStatus())
            .append("content", getContent())
            .toString();
    }
}
