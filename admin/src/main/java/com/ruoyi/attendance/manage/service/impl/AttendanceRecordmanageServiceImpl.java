package com.ruoyi.attendance.manage.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.attendance.manage.mapper.AttendanceRecordmanageMapper;
import com.ruoyi.attendance.manage.domain.AttendanceRecordmanage;
import com.ruoyi.attendance.manage.service.IAttendanceRecordmanageService;

/**
 * 考勤记录Service业务层处理
 * 
 * @author yhy
 * @date 2024-03-24
 */
@Service
public class AttendanceRecordmanageServiceImpl implements IAttendanceRecordmanageService 
{
    @Autowired
    private AttendanceRecordmanageMapper attendanceRecordmanageMapper;

    /**
     * 查询考勤记录
     * 
     * @param recordId 考勤记录主键
     * @return 考勤记录
     */
    @Override
    public AttendanceRecordmanage selectAttendanceRecordmanageByRecordId(Long recordId)
    {
        return attendanceRecordmanageMapper.selectAttendanceRecordmanageByRecordId(recordId);
    }

    /**
     * 查询考勤记录列表
     * 
     * @param attendanceRecordmanage 考勤记录
     * @return 考勤记录
     */
    @Override
    public List<AttendanceRecordmanage> selectAttendanceRecordmanageList(AttendanceRecordmanage attendanceRecordmanage)
    {
        return attendanceRecordmanageMapper.selectAttendanceRecordmanageList(attendanceRecordmanage);
    }

    /**
     * 新增考勤记录
     * 
     * @param attendanceRecordmanage 考勤记录
     * @return 结果
     */
    @Override
    public int insertAttendanceRecordmanage(AttendanceRecordmanage attendanceRecordmanage)
    {
        return attendanceRecordmanageMapper.insertAttendanceRecordmanage(attendanceRecordmanage);
    }

    /**
     * 修改考勤记录
     * 
     * @param attendanceRecordmanage 考勤记录
     * @return 结果
     */
    @Override
    public int updateAttendanceRecordmanage(AttendanceRecordmanage attendanceRecordmanage)
    {
        return attendanceRecordmanageMapper.updateAttendanceRecordmanage(attendanceRecordmanage);
    }

    /**
     * 批量删除考勤记录
     * 
     * @param recordIds 需要删除的考勤记录主键
     * @return 结果
     */
    @Override
    public int deleteAttendanceRecordmanageByRecordIds(Long[] recordIds)
    {
        return attendanceRecordmanageMapper.deleteAttendanceRecordmanageByRecordIds(recordIds);
    }

    /**
     * 删除考勤记录信息
     * 
     * @param recordId 考勤记录主键
     * @return 结果
     */
    @Override
    public int deleteAttendanceRecordmanageByRecordId(Long recordId)
    {
        return attendanceRecordmanageMapper.deleteAttendanceRecordmanageByRecordId(recordId);
    }
}
