package com.ruoyi.attendance.sign_in_out.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 签到签退对象 attendance_record
 * 
 * @author yhy
 * @date 2024-03-24
 */
public class AttendanceRecordSign extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 考勤记录ID */
    private Long recordId;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 考勤日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "考勤日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date attendanceDate;

    /** 上班时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "上班时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /** 下班时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "下班时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /** 考勤类型 */
    @Excel(name = "考勤类型")
    private String attendanceType;

    public void setRecordId(Long recordId) 
    {
        this.recordId = recordId;
    }

    public Long getRecordId() 
    {
        return recordId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setAttendanceDate(Date attendanceDate) 
    {
        this.attendanceDate = attendanceDate;
    }

    public Date getAttendanceDate() 
    {
        return attendanceDate;
    }
    public void setStartTime(Date startTime) 
    {
        this.startTime = startTime;
    }

    public Date getStartTime() 
    {
        return startTime;
    }
    public void setEndTime(Date endTime) 
    {
        this.endTime = endTime;
    }

    public Date getEndTime() 
    {
        return endTime;
    }
    public void setAttendanceType(String attendanceType) 
    {
        this.attendanceType = attendanceType;
    }

    public String getAttendanceType() 
    {
        return attendanceType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("recordId", getRecordId())
            .append("userId", getUserId())
            .append("attendanceDate", getAttendanceDate())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("attendanceType", getAttendanceType())
            .toString();
    }
}
