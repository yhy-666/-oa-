<template>
  <div class="app-container">
    <!--签到签退按钮的位置-->

    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="考勤记录ID" prop="recordId">
        <el-input
          v-model="queryParams.recordId"
          placeholder="请输入考勤记录ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <!--      <el-form-item label="用户ID" prop="userId">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.userId"-->
      <!--          placeholder="请输入用户ID"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
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
          v-hasPermi="['attendance:sign_in_out:add']"
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
          v-hasPermi="['attendance:sign_in_out:edit']"
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
          v-hasPermi="['attendance:sign_in_out:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['attendance:sign_in_out:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="sign_in_outList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="考勤记录ID" align="center" prop="recordId" />
      <el-table-column label="用户ID" align="center" prop="userId" />
      <el-table-column label="考勤日期" align="center" prop="attendanceDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.attendanceDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="上班时间" align="center" prop="startTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.startTime, '{h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="下班时间" align="center" prop="endTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.endTime, '{h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="考勤类型" align="center" prop="attendanceType" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['attendance:sign_in_out:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['attendance:sign_in_out:remove']"
          >删除</el-button>
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

    <!-- 添加或修改签到签退对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="用户ID" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入用户ID" />
        </el-form-item>
        <el-form-item label="考勤日期" prop="attendanceDate">
          <el-date-picker clearable
                          v-model="form.attendanceDate"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择考勤日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="上班时间" prop="startTime">
          <el-date-picker clearable
                          v-model="form.startTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择上班时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="下班时间" prop="endTime">
          <el-date-picker clearable
                          v-model="form.endTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择下班时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="考勤类型" prop="attendanceType">
          <el-input v-model="form.attendanceType" placeholder="请输入考勤类型" />
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
import {
  addSign_in_out,
  delSign_in_out,
  getSign_in_out,
  listSign_in_out,
  updateSign_in_out
} from "@/api/attendance/sign_in_out";

export default {
  name: "Sign_in_out",
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
      // 签到签退表格数据
      sign_in_outList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        recordId: null,
        userId: null,
        attendanceDate:null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },
      //查询本人的签到签退记录表单
      myForm:{
        recordId:null,
        userId:null,
        attendanceDate:null,
        startTime:null,
        endTime:null,
        attendanceType:null
      },
      //签到时获取记录的条数
      myTotal: 0,
      // 签到签退参数
      signParams:{
        recordId:null,
        userId:null,
        attendanceDate:null,
        startTime:null,
        endTime:null,
        attendanceType:null
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 获取当前年月日时分秒的方法 */
    getCurrentDateTime(value) {
      const date = new Date();
      const year = date.getFullYear();
      const month = String(date.getMonth() + 1).padStart(2, '0');
      const day = String(date.getDate()).padStart(2, '0');
      const hours = String(date.getHours()).padStart(2, '0');
      const minutes = String(date.getMinutes()).padStart(2, '0');
      const seconds = String(date.getSeconds()).padStart(2, '0');
      const currentDateTime = `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
      const currentDateTime2 = `${year}-${month}-${day} ${0}:${0}:${0}`;
      if(value===1)
        return currentDateTime;
      else{
        return currentDateTime2;
      }
    },
    /** 签到事件*/
    sign_in(){
      this.today_is_sign().then(() => {
        if (this.myTotal!== 0) {
          this.$modal.msgWarning("今日已签到");
          return;
        }
        var currentDateTime = new Date();
        var nineAM = new Date();
        nineAM.setHours(9, 0, 0, 0); // 设置小时、分钟、秒和毫秒
        if (currentDateTime > nineAM) {
          this.signParams.attendanceType='迟到';
        } else {
          this.signParams.attendanceType='正常';
        }
        this.signParams.userId=this.$store.state.user.id;
        this.signParams.attendanceDate=this.getCurrentDateTime();
        this.signParams.startTime=this.getCurrentDateTime(1);
        this.signParams.endTime=null;

        addSign_in_out(this.signParams).then(response => {
          this.$modal.msgSuccess("签到成功");
          this.getList();
        });
      })
    },
    /** 签退事件*/
    sign_out(){
      this.today_is_sign().then(() => {
        if (this.myTotal=== 0) {
          this.$modal.msgWarning("今日未签到");
          return;
        }
        if(this.myForm.endTime!==null)
        {
          this.$modal.msgWarning("今日已签退");
          return;
        }
        var currentDateTime = new Date();
        var nineAM = new Date();
        nineAM.setHours(18, 0, 0, 0); // 设置小时、分钟、秒和毫秒
        if (currentDateTime < nineAM) {
          if(this.myForm.attendanceType==='正常')
            this.signParams.attendanceType='早退';
          else
            this.signParams.attendanceType=this.myForm.attendanceType+' 早退';
        } else {
          if(this.myForm.attendanceType==='正常')
            this.signParams.attendanceType='正常';
          else{
            this.signParams.attendanceType=this.myForm.attendanceType;
          }
        }
        this.signParams.recordId=this.myForm.recordId;
        this.signParams.userId=null;
        this.signParams.attendanceDate=null;
        this.signParams.startTime=null;
        this.signParams.endTime=this.getCurrentDateTime(1);
        updateSign_in_out(this.signParams).then(response => {
          this.$modal.msgSuccess("签退成功");
          this.getList();
        });
      })
    },
    /** 查询今日的签到记录是否已签到*/
    today_is_sign(){
      this.queryParams.attendanceDate=this.getCurrentDateTime();
      return listSign_in_out(this.queryParams).then(response => {
        this.myForm=response.rows[0];
        this.myTotal=response.total;
        console.log(this.myTotal)
        this.queryParams.attendanceDate=null;
        return response.total;
      });
    },
    /** 查询签到签退列表 */
    getList() {
      this.loading = true;
      this.queryParams.userId=this.$store.state.user.id;
      listSign_in_out(this.queryParams).then(response => {
        this.sign_in_outList = response.rows;
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
        recordId: null,
        userId: null,
        attendanceDate: null,
        startTime: null,
        endTime: null,
        attendanceType: null
      };
      this.resetForm("form");
    },
   
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.recordId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加签到签退";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const recordId = row.recordId || this.ids
      getSign_in_out(recordId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改签到签退";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.recordId != null) {
            updateSign_in_out(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addSign_in_out(this.form).then(response => {
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
      const recordIds = row.recordId || this.ids;
      this.$modal.confirm('是否确认删除签到签退编号为"' + recordIds + '"的数据项？').then(function() {
        return delSign_in_out(recordIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('attendance/sign_in_out/export', {
        ...this.queryParams
      }, `sign_in_out_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
