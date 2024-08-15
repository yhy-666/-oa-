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
import com.ruoyi.reimbursement.domain.Reimbursement;
import com.ruoyi.reimbursement.service.IReimbursementService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 我的申请Controller
 * 
 * @author ruoyi
 * @date 2024-03-19
 */
@RestController
@RequestMapping("/reimbursement/applicant")
public class ReimbursementController extends BaseController
{
    @Autowired
    private IReimbursementService reimbursementService;

    /**
     * 查询我的申请列表
     */
    @PreAuthorize("@ss.hasPermi('reimbursement:applicant:list')")
    @GetMapping("/list")
    public TableDataInfo list(Reimbursement reimbursement)
    {
        startPage();
        List<Reimbursement> list = reimbursementService.selectReimbursementList(reimbursement);
        return getDataTable(list);
    }

    /**
     * 导出我的申请列表
     */
    @PreAuthorize("@ss.hasPermi('reimbursement:applicant:export')")
    @Log(title = "我的申请", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Reimbursement reimbursement)
    {
        List<Reimbursement> list = reimbursementService.selectReimbursementList(reimbursement);
        ExcelUtil<Reimbursement> util = new ExcelUtil<Reimbursement>(Reimbursement.class);
        util.exportExcel(response, list, "我的申请数据");
    }

    /**
     * 获取我的申请详细信息
     */
    @PreAuthorize("@ss.hasPermi('reimbursement:applicant:query')")
    @GetMapping(value = "/{reimbursementId}")
    public AjaxResult getInfo(@PathVariable("reimbursementId") Long reimbursementId)
    {
        return success(reimbursementService.selectReimbursementByReimbursementId(reimbursementId));
    }

    /**
     * 新增我的申请
     */
    @PreAuthorize("@ss.hasPermi('reimbursement:applicant:add')")
    @Log(title = "我的申请", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Reimbursement reimbursement)
    {
        return toAjax(reimbursementService.insertReimbursement(reimbursement));
    }

    /**
     * 修改我的申请
     */
    @PreAuthorize("@ss.hasPermi('reimbursement:applicant:edit')")
    @Log(title = "我的申请", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Reimbursement reimbursement)
    {
        return toAjax(reimbursementService.updateReimbursement(reimbursement));
    }

    /**
     * 删除我的申请
     */
    @PreAuthorize("@ss.hasPermi('reimbursement:applicant:remove')")
    @Log(title = "我的申请", businessType = BusinessType.DELETE)
	@DeleteMapping("/{reimbursementIds}")
    public AjaxResult remove(@PathVariable Long[] reimbursementIds)
    {
        return toAjax(reimbursementService.deleteReimbursementByReimbursementIds(reimbursementIds));
    }
}
