package com.ruoyi.reimbursement.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 报销类型管理对象 ReimbursementType
 * 
 * @author ruoyi
 * @date 2024-03-19
 */
public class ReimbursementType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 报销类型ID */
    private Long typeId;

    /** 报销类型名称 */
    @Excel(name = "报销类型名称")
    private String name;

    public void setTypeId(Long typeId) 
    {
        this.typeId = typeId;
    }

    public Long getTypeId() 
    {
        return typeId;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("typeId", getTypeId())
            .append("name", getName())
            .toString();
    }
}
