<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="申请类型" prop="type">
        <el-select v-model="queryParams.type" placeholder="请输入申请类型" clearable  @keyup.enter.native="handleQuery">
          <el-option
            v-for="type in typeOptions"
            :key="type.typeId"
            :label="type.name"
            :value="type.name"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="申请时间" prop="createTime">
        <el-date-picker clearable
          v-model="queryParams.createTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择申请时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['office_approvals:approvals:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['office_approvals:approvals:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['office_approvals:approvals:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['office_approvals:approvals:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="approvalsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="申请ID" align="center" prop="applicationId" />
      <el-table-column label="申请类型" align="center" prop="type" />
      <el-table-column label="申请人ID" align="center" prop="applicantId" />
      <el-table-column label="审核人ID" align="center" prop="approverId" />
      <el-table-column label="申请时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="申请状态" align="center" prop="status" />
      <el-table-column label="申请内容" align="center" prop="content" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['office_approvals:approvals:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['office_approvals:approvals:remove']"
          >删除</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handlePass(scope.row)"
            v-hasPermi="['office_approvals:approvals:pass']"
            v-if="scope.row.status==='审批中'"
          >同意</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleRefuse(scope.row)"
            v-hasPermi="['office_approvals:approvals:refuse']"
            v-if="scope.row.status==='审批中'"
          >拒绝</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改申请与审批对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="申请类型">
<!--        <el-input v-model="form.type" placeholder="请输入申请类型" />-->
          <el-select v-model="form.type" placeholder="请输入申请类型">
            <el-option
              v-for="type in typeOptions"
              :key="type.typeId"
              :label="type.name"
              :value="type.name"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="申请内容"  >
          <editor v-model="form.content" :min-height="192" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listApprovals, getApprovals, delApprovals, addApprovals, updateApprovals } from "@/api/office_approvals/approvals";
import {listType} from "@/api/office_approvals/type";

export default {
  name: "Approvals",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 申请与审批表格数据
      approvalsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        type: null,
        createTime: null,
        status: null,
      },
      // 表单参数
      form: {},
      //申请类型数据
      typeOptions:[],
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
    this.getTypeList();
  },
  methods: {
    /**
     * 查询申请类型列表
     */
    getTypeList(){
      listType().then(response =>{
        this.typeOptions = response.rows;
      })
    },
    /** 查询申请与审批列表 */
    getList() {
      this.loading = true;
      listApprovals(this.queryParams).then(response => {
        this.approvalsList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        applicationId: null,
        type: null,
        applicantId: null,
        approverId: null,
        createTime: null,
        status: null,
        content: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.applicationId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 获取当前年月日时分秒的方法 */
    getCurrentDateTime() {
      const date = new Date();
      const year = date.getFullYear();
      const month = String(date.getMonth() + 1).padStart(2, '0');
      const day = String(date.getDate()).padStart(2, '0');
      const hours = String(date.getHours()).padStart(2, '0');
      const minutes = String(date.getMinutes()).padStart(2, '0');
      const seconds = String(date.getSeconds()).padStart(2, '0');
      const currentDateTime = `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
      return currentDateTime;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加申请与审批";
      this.getTypeList();
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const applicationId = row.applicationId || this.ids
      this.getTypeList();
      getApprovals(applicationId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改申请与审批";
      });
    },
    /** 通过按钮操作 */
    handlePass(row){
      row.status='已通过'
      row.approverId=this.$store.state.user.id
      updateApprovals(row).then(response=>{
        this.getList();
      })
    },
    /** 拒绝按钮操作 */
    handleRefuse(row){
      row.status='已拒绝'
      row.approverId=this.$store.state.user.id
      updateApprovals(row).then(response=>{
        this.getList();
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.applicationId != null) {
            updateApprovals(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            this.form.status='审批中'
            this.form.applicantId=this.$store.state.user.id
            this.form.createTime=this.getCurrentDateTime()
            addApprovals(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const applicationIds = row.applicationId || this.ids;
      this.$modal.confirm('是否确认删除申请与审批编号为"' + applicationIds + '"的数据项？').then(function() {
        return delApprovals(applicationIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('office_approvals/approvals/export', {
        ...this.queryParams
      }, `approvals_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
