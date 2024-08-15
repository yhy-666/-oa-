package com.ruoyi.attendance.sign_in_out.mapper;

import java.util.List;
import com.ruoyi.attendance.sign_in_out.domain.AttendanceRecordSign;

/**
 * 签到签退Mapper接口
 * 
 * @author yhy
 * @date 2024-03-24
 */
public interface AttendanceRecordSignMapper 
{
    /**
     * 查询签到签退
     * 
     * @param recordId 签到签退主键
     * @return 签到签退
     */
    public AttendanceRecordSign selectAttendanceRecordSignByRecordId(Long recordId);

    /**
     * 查询签到签退列表
     * 
     * @param attendanceRecordSign 签到签退
     * @return 签到签退集合
     */
    public List<AttendanceRecordSign> selectAttendanceRecordSignList(AttendanceRecordSign attendanceRecordSign);

    /**
     * 新增签到签退
     * 
     * @param attendanceRecordSign 签到签退
     * @return 结果
     */
    public int insertAttendanceRecordSign(AttendanceRecordSign attendanceRecordSign);

    /**
     * 修改签到签退
     * 
     * @param attendanceRecordSign 签到签退
     * @return 结果
     */
    public int updateAttendanceRecordSign(AttendanceRecordSign attendanceRecordSign);

    /**
     * 删除签到签退
     * 
     * @param recordId 签到签退主键
     * @return 结果
     */
    public int deleteAttendanceRecordSignByRecordId(Long recordId);

    /**
     * 批量删除签到签退
     * 
     * @param recordIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAttendanceRecordSignByRecordIds(Long[] recordIds);
}
