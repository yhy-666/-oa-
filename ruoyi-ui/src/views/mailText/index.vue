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



       




      




      </el-form>








    </el-main>
  </el-container>
</template>



<style scoped>
/* 根据实际情况，可能需要调整选择器来正确隐藏按钮 */
.ql-toolbar .ql-formats:last-child,
.ql-toolbar .ql-formats:nth-last-child(2),
.ql-toolbar .ql-formats:nth-last-child(3) {
  display: none;
}
</style>

