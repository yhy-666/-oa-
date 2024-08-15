package com.ruoyi.reimbursement.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 我的申请对象 Reimbursement
 * 
 * @author ruoyi
 * @date 2024-03-19
 */
public class Reimbursement extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 报销记录ID */
    private Long reimbursementId;

    /** 报销金额 */
    @Excel(name = "报销金额")
    private BigDecimal amount;

    /** 报销状态 */
    @Excel(name = "报销状态")
    private String status;

    /** 报销类型 */
    @Excel(name = "报销类型")
    private String type;

    /** 申请人ID */
    @Excel(name = "申请人ID")
    private Long applicantId;

    /** 审核人ID */
    @Excel(name = "审核人ID")
    private Long approverId;

    /** 备注 */
    @Excel(name = "备注")
    private String note;

    public void setReimbursementId(Long reimbursementId) 
    {
        this.reimbursementId = reimbursementId;
    }

    public Long getReimbursementId() 
    {
        return reimbursementId;
    }
    public void setAmount(BigDecimal amount) 
    {
        this.amount = amount;
    }

    public BigDecimal getAmount() 
    {
        return amount;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
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
    public void setNote(String note) 
    {
        this.note = note;
    }

    public String getNote() 
    {
        return note;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("reimbursementId", getReimbursementId())
            .append("amount", getAmount())
            .append("status", getStatus())
            .append("type", getType())
            .append("applicantId", getApplicantId())
            .append("approverId", getApproverId())
            .append("note", getNote())
            .toString();
    }
}
