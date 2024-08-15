package com.ruoyi.office_approvals.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 申请类型管理对象 applicationType
 * 
 * @author ruoyi
 * @date 2024-03-20
 */
public class ApplicationType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 申请类型ID */
    private Long typeId;

    /** 申请类型 */
    @Excel(name = "申请类型")
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
