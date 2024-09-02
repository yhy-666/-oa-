<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
<!--      <el-form-item label="报销金额" prop="amount">-->
<!--        <el-input-->
<!--          v-model="queryParams.amount"-->
<!--          placeholder="请输入报销金额"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item label="报销类型" prop="type">
<!--        <el-input-->
<!--          v-model="queryParams.type"-->
<!--          placeholder="请输入报销类型"-->
<!--          -->
<!--        />-->
        <el-select v-model="queryParams.type" placeholder="请输入报销类型" clearable           @keyup.enter.native="handleQuery">
          <el-option
            v-for="type in typeOptions"
            :key="type.typeId"
            :label="type.name"
            :value="type.name"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="申请人ID" prop="applicantId">
        <el-input
          v-model="queryParams.applicantId"
          placeholder="请输入申请人ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="审核人ID" prop="approverId">
        <el-input
          v-model="queryParams.approverId"
          placeholder="请输入审核人ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
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
          v-hasPermi="['reimbursement:applicant:add']"
        >申请报销</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['reimbursement:applicant:edit']"
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
          v-hasPermi="['reimbursement:applicant:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['reimbursement:applicant:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="applicantList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="报销记录ID" align="center" prop="reimbursementId" />
      <el-table-column label="报销金额" align="center" prop="amount" />
      <el-table-column label="报销状态" align="center" prop="status" />
      <el-table-column label="报销类型" align="center" prop="type" />
      <el-table-column label="申请人ID" align="center" prop="applicantId" />
      <el-table-column label="审核人ID" align="center" prop="approverId" />
      <el-table-column label="备注" align="center" prop="note" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['reimbursement:applicant:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['reimbursement:applicant:remove']"
          >删除</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handlePass(scope.row)"
            v-hasPermi="['reimbursement:applicant:pass']"
            v-if="scope.row.status==='审批中'"
          >通过</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleRefuse(scope.row)"
            v-hasPermi="['reimbursement:applicant:refuse']"
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

    <!-- 添加或修改我的申请对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="报销金额" prop="amount">
          <el-input v-model="form.amount" placeholder="请输入报销金额" />
        </el-form-item>
        <el-form-item label="报销类型" prop="type">
<!--          <el-input v-model="form.type" placeholder="请输入报销类型" />-->
          <el-select v-model="form.type" placeholder="请输入报销类型">
            <el-option
              v-for="type in typeOptions"
              :key="type.typeId"
              :label="type.name"
              :value="type.name"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="备注" prop="note">
          <el-input v-model="form.note" placeholder="请输入备注" />
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
import { listApplicant, getApplicant, delApplicant, addApplicant, updateApplicant } from "@/api/reimbursement/applicant";
import { listType } from "@/api/reimbursement/type";

export default {
  name: "Applicant",
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
      // 我的申请表格数据
      applicantList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        amount: null,
        type: null,
        applicantId: null,
        approverId: null,
      },
      // 表单参数
      form: {},
      //报销类型数据
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
     * 查询报销类型列表
     */
    getTypeList(){
      listType().then(response =>{
        this.typeOptions = response.rows;
      })
    },
    /** 查询我的申请列表 */
    getList() {
      this.loading = true;
      listApplicant(this.queryParams).then(response => {
        this.applicantList = response.rows;
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
        reimbursementId: null,
        amount: null,
        status: null,
        type: null,
        applicantId: null,
        approverId: null,
        note: null
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
      this.ids = selection.map(item => item.reimbursementId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加我的申请";
      this.getTypeList();
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const reimbursementId = row.reimbursementId || this.ids
      this.getTypeList();
      getApplicant(reimbursementId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改我的申请";
      });
    },
    /** 通过按钮操作 */
    handlePass(row){
      row.status='已通过'
      row.approverId=this.$store.state.user.id
      updateApplicant(row).then(response=>{
        this.getList();
      })
    },
    /** 拒绝按钮操作 */
    handleRefuse(row){
      row.status='已拒绝'
      row.approverId=this.$store.state.user.id
      updateApplicant(row).then(response=>{
        this.getList();
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.reimbursementId != null) {
            updateApplicant(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            this.form.status='审批中'
            this.form.applicantId=this.$store.state.user.id
            addApplicant(this.form).then(response => {
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
      const reimbursementIds = row.reimbursementId || this.ids;
      this.$modal.confirm('是否确认删除我的申请编号为"' + reimbursementIds + '"的数据项？').then(function() {
        return delApplicant(reimbursementIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('reimbursement/applicant/export', {
        ...this.queryParams
      }, `applicant_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
