package com.ruoyi.attendance.sign_in_out.controller;

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
import com.ruoyi.attendance.sign_in_out.domain.AttendanceRecordSign;
import com.ruoyi.attendance.sign_in_out.service.IAttendanceRecordSignService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 签到签退Controller
 * 
 * @author yhy
 * @date 2024-03-24
 */
@RestController
@RequestMapping("/attendance/sign_in_out")
public class AttendanceRecordSignController extends BaseController
{
    @Autowired
    private IAttendanceRecordSignService attendanceRecordSignService;

    /**
     * 查询签到签退列表
     */
    @PreAuthorize("@ss.hasPermi('attendance:sign_in_out:list')")
    @GetMapping("/list")
    public TableDataInfo list(AttendanceRecordSign attendanceRecordSign)
    {
        startPage();
        List<AttendanceRecordSign> list = attendanceRecordSignService.selectAttendanceRecordSignList(attendanceRecordSign);
        return getDataTable(list);
    }

    /**
     * 导出签到签退列表
     */
    @PreAuthorize("@ss.hasPermi('attendance:sign_in_out:export')")
    @Log(title = "签到签退", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AttendanceRecordSign attendanceRecordSign)
    {
        List<AttendanceRecordSign> list = attendanceRecordSignService.selectAttendanceRecordSignList(attendanceRecordSign);
        ExcelUtil<AttendanceRecordSign> util = new ExcelUtil<AttendanceRecordSign>(AttendanceRecordSign.class);
        util.exportExcel(response, list, "签到签退数据");
    }

    /**
     * 获取签到签退详细信息
     */
    @PreAuthorize("@ss.hasPermi('attendance:sign_in_out:query')")
    @GetMapping(value = "/{recordId}")
    public AjaxResult getInfo(@PathVariable("recordId") Long recordId)
    {
        return success(attendanceRecordSignService.selectAttendanceRecordSignByRecordId(recordId));
    }

    /**
     * 新增签到签退
     */
    @PreAuthorize("@ss.hasPermi('attendance:sign_in_out:add')")
    @Log(title = "签到签退", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AttendanceRecordSign attendanceRecordSign)
    {
        return toAjax(attendanceRecordSignService.insertAttendanceRecordSign(attendanceRecordSign));
    }

    /**
     * 修改签到签退
     */
    @PreAuthorize("@ss.hasPermi('attendance:sign_in_out:edit')")
    @Log(title = "签到签退", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AttendanceRecordSign attendanceRecordSign)
    {
        return toAjax(attendanceRecordSignService.updateAttendanceRecordSign(attendanceRecordSign));
    }

    /**
     * 删除签到签退
     */
    @PreAuthorize("@ss.hasPermi('attendance:sign_in_out:remove')")
    @Log(title = "签到签退", businessType = BusinessType.DELETE)
	@DeleteMapping("/{recordIds}")
    public AjaxResult remove(@PathVariable Long[] recordIds)
    {
        return toAjax(attendanceRecordSignService.deleteAttendanceRecordSignByRecordIds(recordIds));
    }
}
