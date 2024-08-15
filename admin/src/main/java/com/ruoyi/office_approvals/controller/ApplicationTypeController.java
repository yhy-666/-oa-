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
import com.ruoyi.office_approvals.domain.ApplicationType;
import com.ruoyi.office_approvals.service.IApplicationTypeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 申请类型管理Controller
 * 
 * @author ruoyi
 * @date 2024-03-20
 */
@RestController
@RequestMapping("/office_approvals/type")
public class ApplicationTypeController extends BaseController
{
    @Autowired
    private IApplicationTypeService applicationTypeService;

    /**
     * 查询申请类型管理列表
     */
    @PreAuthorize("@ss.hasPermi('office_approvals:type:list')")
    @GetMapping("/list")
    public TableDataInfo list(ApplicationType applicationType)
    {
        startPage();
        List<ApplicationType> list = applicationTypeService.selectApplicationTypeList(applicationType);
        return getDataTable(list);
    }

    /**
     * 导出申请类型管理列表
     */
    @PreAuthorize("@ss.hasPermi('office_approvals:type:export')")
    @Log(title = "申请类型管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ApplicationType applicationType)
    {
        List<ApplicationType> list = applicationTypeService.selectApplicationTypeList(applicationType);
        ExcelUtil<ApplicationType> util = new ExcelUtil<ApplicationType>(ApplicationType.class);
        util.exportExcel(response, list, "申请类型管理数据");
    }

    /**
     * 获取申请类型管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('office_approvals:type:query')")
    @GetMapping(value = "/{typeId}")
    public AjaxResult getInfo(@PathVariable("typeId") Long typeId)
    {
        return success(applicationTypeService.selectApplicationTypeByTypeId(typeId));
    }

    /**
     * 新增申请类型管理
     */
    @PreAuthorize("@ss.hasPermi('office_approvals:type:add')")
    @Log(title = "申请类型管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ApplicationType applicationType)
    {
        return toAjax(applicationTypeService.insertApplicationType(applicationType));
    }

    /**
     * 修改申请类型管理
     */
    @PreAuthorize("@ss.hasPermi('office_approvals:type:edit')")
    @Log(title = "申请类型管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ApplicationType applicationType)
    {
        return toAjax(applicationTypeService.updateApplicationType(applicationType));
    }

    /**
     * 删除申请类型管理
     */
    @PreAuthorize("@ss.hasPermi('office_approvals:type:remove')")
    @Log(title = "申请类型管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{typeIds}")
    public AjaxResult remove(@PathVariable Long[] typeIds)
    {
        return toAjax(applicationTypeService.deleteApplicationTypeByTypeIds(typeIds));
    }
}
