package com.ruoyi.attendance.manage.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.attendance.manage.domain.AttendanceRecordmanage;
import com.ruoyi.attendance.manage.service.IAttendanceRecordmanageService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 考勤记录Controller
 * 
 * @author yhy
 * @date 2024-03-24
 */
@RestController
@RequestMapping("/attendance/manage")
public class AttendanceRecordmanageController extends BaseController
{
    @Autowired
    private IAttendanceRecordmanageService attendanceRecordmanageService;

    /**
     * 查询考勤记录列表
     */
    @PreAuthorize("@ss.hasPermi('attendance:manage:list')")
    @GetMapping("/list")
    public TableDataInfo list(AttendanceRecordmanage attendanceRecordmanage)
    {
        startPage();
        List<AttendanceRecordmanage> list = attendanceRecordmanageService.selectAttendanceRecordmanageList(attendanceRecordmanage);
        return getDataTable(list);
    }

    /**
     * 导出考勤记录列表
     */
    @PreAuthorize("@ss.hasPermi('attendance:manage:export')")
    @Log(title = "考勤记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AttendanceRecordmanage attendanceRecordmanage)
    {
        List<AttendanceRecordmanage> list = attendanceRecordmanageService.selectAttendanceRecordmanageList(attendanceRecordmanage);
        ExcelUtil<AttendanceRecordmanage> util = new ExcelUtil<AttendanceRecordmanage>(AttendanceRecordmanage.class);
        util.exportExcel(response, list, "考勤记录数据");
    }

    /**
     * 获取考勤记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('attendance:manage:query')")
    @GetMapping(value = "/{recordId}")
    public AjaxResult getInfo(@PathVariable("recordId") Long recordId)
    {
        return success(attendanceRecordmanageService.selectAttendanceRecordmanageByRecordId(recordId));
    }

    /**
     * 新增考勤记录
     */
    @PreAuthorize("@ss.hasPermi('attendance:manage:add')")
    @Log(title = "考勤记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AttendanceRecordmanage attendanceRecordmanage)
    {
        return toAjax(attendanceRecordmanageService.insertAttendanceRecordmanage(attendanceRecordmanage));
    }

    /**
     * 修改考勤记录
     */
    @PreAuthorize("@ss.hasPermi('attendance:manage:edit')")
    @Log(title = "考勤记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AttendanceRecordmanage attendanceRecordmanage)
    {
        return toAjax(attendanceRecordmanageService.updateAttendanceRecordmanage(attendanceRecordmanage));
    }

    /**
     * 删除考勤记录
     */
    @PreAuthorize("@ss.hasPermi('attendance:manage:remove')")
    @Log(title = "考勤记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{recordIds}")
    public AjaxResult remove(@PathVariable Long[] recordIds)
    {
        return toAjax(attendanceRecordmanageService.deleteAttendanceRecordmanageByRecordIds(recordIds));
    }
}
