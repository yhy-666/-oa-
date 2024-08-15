package com.ruoyi.attendance.manage.service;

import java.util.List;
import com.ruoyi.attendance.manage.domain.AttendanceRecordmanage;

/**
 * 考勤记录Service接口
 * 
 * @author yhy
 * @date 2024-03-24
 */
public interface IAttendanceRecordmanageService 
{
    /**
     * 查询考勤记录
     * 
     * @param recordId 考勤记录主键
     * @return 考勤记录
     */
    public AttendanceRecordmanage selectAttendanceRecordmanageByRecordId(Long recordId);

    /**
     * 查询考勤记录列表
     * 
     * @param attendanceRecordmanage 考勤记录
     * @return 考勤记录集合
     */
    public List<AttendanceRecordmanage> selectAttendanceRecordmanageList(AttendanceRecordmanage attendanceRecordmanage);

    /**
     * 新增考勤记录
     * 
     * @param attendanceRecordmanage 考勤记录
     * @return 结果
     */
    public int insertAttendanceRecordmanage(AttendanceRecordmanage attendanceRecordmanage);

    /**
     * 修改考勤记录
     * 
     * @param attendanceRecordmanage 考勤记录
     * @return 结果
     */
    public int updateAttendanceRecordmanage(AttendanceRecordmanage attendanceRecordmanage);

    /**
     * 批量删除考勤记录
     * 
     * @param recordIds 需要删除的考勤记录主键集合
     * @return 结果
     */
    public int deleteAttendanceRecordmanageByRecordIds(Long[] recordIds);

    /**
     * 删除考勤记录信息
     * 
     * @param recordId 考勤记录主键
     * @return 结果
     */
    public int deleteAttendanceRecordmanageByRecordId(Long recordId);
}
