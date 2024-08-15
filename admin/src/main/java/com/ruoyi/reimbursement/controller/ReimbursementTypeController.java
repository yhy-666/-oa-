package com.ruoyi.reimbursement.controller;

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
import com.ruoyi.reimbursement.domain.ReimbursementType;
import com.ruoyi.reimbursement.service.IReimbursementTypeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 报销类型管理Controller
 * 
 * @author ruoyi
 * @date 2024-03-19
 */
@RestController
@RequestMapping("/reimbursement/type")
public class ReimbursementTypeController extends BaseController
{
    @Autowired
    private IReimbursementTypeService reimbursementTypeService;

    /**
     * 查询报销类型管理列表
     */
    @PreAuthorize("@ss.hasPermi('reimbursement:type:list')")
    @GetMapping("/list")
    public TableDataInfo list(ReimbursementType reimbursementType)
    {
        startPage();
        List<ReimbursementType> list = reimbursementTypeService.selectReimbursementTypeList(reimbursementType);
        return getDataTable(list);
    }

    /**
     * 导出报销类型管理列表
     */
    @PreAuthorize("@ss.hasPermi('reimbursement:type:export')")
    @Log(title = "报销类型管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ReimbursementType reimbursementType)
    {
        List<ReimbursementType> list = reimbursementTypeService.selectReimbursementTypeList(reimbursementType);
        ExcelUtil<ReimbursementType> util = new ExcelUtil<ReimbursementType>(ReimbursementType.class);
        util.exportExcel(response, list, "报销类型管理数据");
    }

    /**
     * 获取报销类型管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('reimbursement:type:query')")
    @GetMapping(value = "/{typeId}")
    public AjaxResult getInfo(@PathVariable("typeId") Long typeId)
    {
        return success(reimbursementTypeService.selectReimbursementTypeByTypeId(typeId));
    }

    /**
     * 新增报销类型管理
     */
    @PreAuthorize("@ss.hasPermi('reimbursement:type:add')")
    @Log(title = "报销类型管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ReimbursementType reimbursementType)
    {
        return toAjax(reimbursementTypeService.insertReimbursementType(reimbursementType));
    }

    /**
     * 修改报销类型管理
     */
    @PreAuthorize("@ss.hasPermi('reimbursement:type:edit')")
    @Log(title = "报销类型管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ReimbursementType reimbursementType)
    {
        return toAjax(reimbursementTypeService.updateReimbursementType(reimbursementType));
    }

    /**
     * 删除报销类型管理
     */
    @PreAuthorize("@ss.hasPermi('reimbursement:type:remove')")
    @Log(title = "报销类型管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{typeIds}")
    public AjaxResult remove(@PathVariable Long[] typeIds)
    {
        return toAjax(reimbursementTypeService.deleteReimbursementTypeByTypeIds(typeIds));
    }
}
