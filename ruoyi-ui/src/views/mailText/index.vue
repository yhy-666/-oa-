<template>
  <el-container>
    <!--    <el-header><h1>邮箱测试</h1></el-header>-->
    <el-main>

      <el-form ref="emailForm" label-width="100px">
        <!-- 文章标题输入框 -->
        <el-form-item label="文章标题:">
          <el-input v-model="emailTitle" placeholder="请输入文章标题"></el-input>
        </el-form-item>

        <!-- 收件人选择框 -->
        <el-form-item label="收件人:">
          <!--          <el-select v-model="toUserID" placeholder="请选择">-->
          <!--            <el-option-->
          <!--              v-for="user in userList.filter(user => user.userId !== this.user.userId && user.deptId == this.user.deptId)"-->
          <!--              :key="user.userId"-->
          <!--              :label="user.userName"-->
          <!--              :value="user.userId">-->
          <!--            </el-option>-->
          <!--          </el-select>-->
          <el-select v-model="toUserID" placeholder="请选择">
            <el-option
              v-for="user in userList.filter(user => user.userId !== this.user.userId && (roleGroup === '下级角色' ? user.deptId == this.user.deptId : true))"
              :key="user.userId"
              :label="user.userName"
              :value="user.userId">
            </el-option>
          </el-select>


        </el-form-item>

        <!-- 邮件内容编辑器 -->
        <el-form-item label="邮件内容:">
          <quill-editor v-model="emailBody" :options="editorOption" theme="snow"></quill-editor>
        </el-form-item>



        <!--这个是附件的-->
        <el-form-item label="附件内容:">
          <el-upload
            class="upload-demo"
            ref="upload"
            :action="'/api/FileUpload/upload'"
            :http-request="customUpload"
            :auto-upload="false"
            :limit="1"
            :on-exceed="handleExceed"
            :on-change="handleFileChange"
            :before-upload="beforeUpload"
          >
            <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
            <el-button size="small" type="danger" @click="cancelUpload">移除发送文件</el-button>
            <div>{{ fileName }}</div>
          </el-upload>
        </el-form-item>




        <!-- 发送邮件按钮 -->
        <el-form-item>
          <el-button type="primary" @click="sendEmail">发送邮件</el-button>

          <!-- 发送邮件给整个部门按钮 -->
          <el-button
            type="primary"
            v-if="roleGroup !== '下级角色'"
            @click="sendToDepartment">
            发送给部门所有用户
          </el-button>
        </el-form-item>




      </el-form>








    </el-main>
  </el-container>
</template>


<script>
// 富文本编辑
import { quillEditor } from 'vue-quill-editor'
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'


import axios from 'axios';
import {listUser, getUserProfile, listUser1} from "@/api/system/user";

export default {
  name: "index",
  // 这些是定义变量
  data() {
    return {
      user: {}, // 当前用户
      roleGroup: {},  // 当前用户角色
      postGroup: '',  // 所在的部门
      userList: [], // 所有用户
      emailBody: '', // 邮件正文
      toUserID: '', // 选择的用户ID
      emailTitle: '',

      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 100,
        userName: undefined,
        phonenumber: undefined,
        status: undefined,
        deptId: undefined
      },
      // 日期范围
      dateRange: [],
      // 调节使用文本编辑框什么功能
      editorOption: {
        modules: {
          toolbar: [
            ['bold', 'italic', 'underline'], // 仅显示加粗、斜体和下划线
            [{'list': 'ordered'}, {'list': 'bullet'}],
            ['clean'] // 清除格式
          ]
        }
      },

      // 上传附件相关
      fileName: '', // 用于显示选中的文件名称
    };
  },

  // 页面初始调用的方法
  created() {
    this.getUser();
  },
  // 富文本编辑
  components: {
    quillEditor,
  },

  // 获取当前用户信息
  methods: {
    getUser() {
      getUserProfile().then(response => {
        this.user = response.data;
        this.roleGroup = response.roleGroup;
        console.log("yhy"+this.roleGroup)

        if (this.roleGroup == '上级角色'){
          console.log('上级角色')
          // 普通用户的，只能遍历整个部门
          listUser1(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
            if (response && Array.isArray(response.rows)) {
              this.userList = response.rows;
              console.log("---"+response.rows)
              // 遍历一个所有的用户的id
              console.log("遍历上级角色所有用户");
              this.userList.forEach((user) => {
                console.log("用户id", user.userId);
                console.log("用户名称", user.userName);
                console.log("这个用户的部门id", user.deptId);
              });


              // 需要当前用户的上下级角色判断邮箱能不能发给整个部门转发
              console.log("roleGroup", this.roleGroup);


              // 需要当前用户的所属部门和部门Id
              console.log("所属部门Id", this.user.dept.deptId);
              console.log("所属部门", this.user.dept.deptName);
              console.log("当前用户ID", this.user.userId);

              // 需要当前部门的所有人
            } else {
              console.error("响应结构不符合预期，response.rows 未找到");
            }
          }).catch(error => {
            console.error("获取用户列表失败:", error);
          });
        } else {
          console.log('下级角色')
          // 普通用户的，只能遍历整个部门
          listUser1(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
            if (response && Array.isArray(response.rows)) {
              this.userList = response.rows;
              console.log("---"+response.rows)
              // 遍历一个所有的用户的id
              console.log("遍历所有用户");
              this.userList.forEach((user) => {
                console.log("用户id", user.userId);
                console.log("这个用户的部门id", user.deptId);
              });


              // 需要当前用户的上下级角色判断邮箱能不能发给整个部门转发
              console.log("roleGroup", this.roleGroup);


              // 需要当前用户的所属部门和部门Id
              console.log("所属部门Id", this.user.dept.deptId);
              console.log("所属部门", this.user.dept.deptName);
              console.log("当前用户ID", this.user.userId);

              // 需要当前部门的所有人
            } else {
              console.error("响应结构不符合预期，response.rows 未找到");
            }
          }).catch(error => {
            console.error("获取用户列表失败:", error);
          });
        }



      });
    },



    sendEmail() {
      // 使用axios或你的HTTP客户端发送POST请求
      axios.post('/api/EmailSend/Send', {
        fromUserID: this.user.userId, // 当前用户的ID
        emailBody: this.emailBody, // 输入的邮件正文
        toUserID: this.toUserID, // 下拉框选择的用户ID
        fileName: this.fileName,
        emailTitle: this.emailTitle, // 文章标题
      })
        .then(() => {
          this.$message({
            message: '邮件发送成功',
            type: 'success'
          });
          // 清空表单或其他成功逻辑
        })
        .catch(error => {
          console.error("邮件发送失败", error);
          this.$message.error('邮件发送失败');
        });

      // 这个是附件的，有文件才发送l
      if (this.fileName)
        this.$refs.upload.submit(); // 触发上传
    },




    // 这个是发送给整个部门的
    sendToDepartment() {
      const departmentId = this.user.dept.deptId;
      const usersInDepartment = this.userList.filter(user => user.deptId === departmentId);

      usersInDepartment.forEach(user => {
        if (user.userId !== this.user.userId) { // 避免给当前用户自己发送
          this.sendEmailToUser(user.userId);
        }
      });

      this.$alert('发送给整个部门成功！', '成功', {
        confirmButtonText: '确定',
      });

    },
    sendEmailToUser(userId) {
      // 假设您有一个用于发送邮件的方法，可以在这里调用它
      // 这里只是一个示例，您需要根据实际情况调整
      axios.post('/api/EmailSend/Send', {
        fromUserID: this.user.userId,
        toUserID: userId,
        emailBody: this.emailBody,
        fileName: this.fileName,
        emailTitle: this.emailTitle,
      })
        .then(() => {
          console.log(`邮件成功发送给用户ID: ${userId}`);
        })
        .catch(error => {
          console.error("邮件发送失败", error);
        });
    },




    // 下面都是和文件相关的
    handleExceed(files, fileList) {
      this.$message.warning('只能上传一个文件，请先移除已选择的文件。');
    },

    handleFileChange(file, fileList) {
      // 如果用户选择了新的文件，清除之前的文件
      if (fileList.length > 1) {
        const newFileList = fileList.slice(-1); // 保留最后一个文件
        this.$refs.upload.clearFiles(); // 清除之前的文件
        this.$refs.upload.fileList = newFileList; // 更新为最后选择的文件
        this.fileName = newFileList[0].name; // 更新文件名显示
      } else {
        this.fileName = file.name; // 更新文件名显示
      }
    },

    beforeUpload(file) {
      this.selectedFile = file; // 更新选中的文件
      return true; // 在这里可以进行文件校验，返回 false 则停止上传
    },
    customUpload(file) {
      const formData = new FormData();
      formData.append('file', this.selectedFile);

      axios.post('/api/FileUpload/upload', formData, {
        headers: {
          'Content-Type': 'multipart/form-data',
        },
      })
        .then((response) => {
          console.log('File uploaded successfully', response.data);
          // this.$alert('文件上传成功！', '成功', {
          //   confirmButtonText: '确定',
          // });
        })
        .catch((error) => {
          console.error('Error uploading file', error);
          this.$alert('文件上传失败！', '错误', {
            confirmButtonText: '确定',
          });
        });
    },
    submitUpload() {
      this.$refs.upload.submit(); // 触发上传
    },
    // 取消发送附件
    cancelUpload() {
      this.$refs.upload.clearFiles(); // 清除已选文件
      this.fileName = ''; // 重置文件名显示
    },


  }
}
</script>

<style scoped>
/* 根据实际情况，可能需要调整选择器来正确隐藏按钮 */
.ql-toolbar .ql-formats:last-child,
.ql-toolbar .ql-formats:nth-last-child(2),
.ql-toolbar .ql-formats:nth-last-child(3) {
  display: none;
}
</style>

