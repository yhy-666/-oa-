package com.ruoyi.office_approvals.controller;

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
import com.ruoyi.office_approvals.domain.Application;
import com.ruoyi.office_approvals.service.IApplicationService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 申请与审批Controller
 * 
 * @author ruoyi
 * @date 2024-03-20
 */
@RestController
@RequestMapping("/office_approvals/approvals")
public class ApplicationController extends BaseController
{
    @Autowired
    private IApplicationService applicationService;

    /**
     * 查询申请与审批列表
     */
    @PreAuthorize("@ss.hasPermi('office_approvals:approvals:list')")
    @GetMapping("/list")
    public TableDataInfo list(Application application)
    {
        startPage();
        List<Application> list = applicationService.selectApplicationList(application);
        return getDataTable(list);
    }

    /**
     * 导出申请与审批列表
     */
    @PreAuthorize("@ss.hasPermi('office_approvals:approvals:export')")
    @Log(title = "申请与审批", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Application application)
    {
        List<Application> list = applicationService.selectApplicationList(application);
        ExcelUtil<Application> util = new ExcelUtil<Application>(Application.class);
        util.exportExcel(response, list, "申请与审批数据");
    }

    /**
     * 获取申请与审批详细信息
     */
    @PreAuthorize("@ss.hasPermi('office_approvals:approvals:query')")
    @GetMapping(value = "/{applicationId}")
    public AjaxResult getInfo(@PathVariable("applicationId") Long applicationId)
    {
        return success(applicationService.selectApplicationByApplicationId(applicationId));
    }

    /**
     * 新增申请与审批
     */
    @PreAuthorize("@ss.hasPermi('office_approvals:approvals:add')")
    @Log(title = "申请与审批", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Application application) {

        return toAjax(applicationService.insertApplication(application));
    }

    /**
     * 修改申请与审批
     */
    @PreAuthorize("@ss.hasPermi('office_approvals:approvals:edit')")
    @Log(title = "申请与审批", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Application application)
    {
        return toAjax(applicationService.updateApplication(application));
    }

    /**
     * 删除申请与审批
     */
    @PreAuthorize("@ss.hasPermi('office_approvals:approvals:remove')")
    @Log(title = "申请与审批", businessType = BusinessType.DELETE)
	@DeleteMapping("/{applicationIds}")
    public AjaxResult remove(@PathVariable Long[] applicationIds)
    {
        return toAjax(applicationService.deleteApplicationByApplicationIds(applicationIds));
    }
}
