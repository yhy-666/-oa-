package com.ruoyi.attendance.sign_in_out.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.attendance.sign_in_out.mapper.AttendanceRecordSignMapper;
import com.ruoyi.attendance.sign_in_out.domain.AttendanceRecordSign;
import com.ruoyi.attendance.sign_in_out.service.IAttendanceRecordSignService;

/**
 * 签到签退Service业务层处理
 * 
 * @author yhy
 * @date 2024-03-24
 */
@Service
public class AttendanceRecordSignServiceImpl implements IAttendanceRecordSignService 
{
    @Autowired
    private AttendanceRecordSignMapper attendanceRecordSignMapper;

    /**
     * 查询签到签退
     * 
     * @param recordId 签到签退主键
     * @return 签到签退
     */
    @Override
    public AttendanceRecordSign selectAttendanceRecordSignByRecordId(Long recordId)
    {
        return attendanceRecordSignMapper.selectAttendanceRecordSignByRecordId(recordId);
    }

    /**
     * 查询签到签退列表
     * 
     * @param attendanceRecordSign 签到签退
     * @return 签到签退
     */
    @Override
    public List<AttendanceRecordSign> selectAttendanceRecordSignList(AttendanceRecordSign attendanceRecordSign)
    {
        return attendanceRecordSignMapper.selectAttendanceRecordSignList(attendanceRecordSign);
    }

    /**
     * 新增签到签退
     * 
     * @param attendanceRecordSign 签到签退
     * @return 结果
     */
    @Override
    public int insertAttendanceRecordSign(AttendanceRecordSign attendanceRecordSign)
    {
        return attendanceRecordSignMapper.insertAttendanceRecordSign(attendanceRecordSign);
    }

    /**
     * 修改签到签退
     * 
     * @param attendanceRecordSign 签到签退
     * @return 结果
     */
    @Override
    public int updateAttendanceRecordSign(AttendanceRecordSign attendanceRecordSign)
    {
        return attendanceRecordSignMapper.updateAttendanceRecordSign(attendanceRecordSign);
    }

    /**
     * 批量删除签到签退
     * 
     * @param recordIds 需要删除的签到签退主键
     * @return 结果
     */
    @Override
    public int deleteAttendanceRecordSignByRecordIds(Long[] recordIds)
    {
        return attendanceRecordSignMapper.deleteAttendanceRecordSignByRecordIds(recordIds);
    }

    /**
     * 删除签到签退信息
     * 
     * @param recordId 签到签退主键
     * @return 结果
     */
    @Override
    public int deleteAttendanceRecordSignByRecordId(Long recordId)
    {
        return attendanceRecordSignMapper.deleteAttendanceRecordSignByRecordId(recordId);
    }
}
