<template>
  <div id="app">
    <!-- 使用Element UI的Card组件展示邮件列表 -->
    <el-row :gutter="20">
      <el-col :span="8" v-for="email in emails" :key="email.emailID">
        <el-card
          @click.native="fetchEmailDetails(email.emailID)"
<<<<<<< HEAD
          :style="{ backgroundColor: email.isRead ? '#FFFFFF' : '#90EE90' }"
        >
          <div class="email-info">
            <p>{{ email.userName }} - {{ email.emailTitle || '无标题' }}</p>
            <p>发送时间：{{ formatDateTime(email.sentDate) }}</p>
            <el-tag :type="email.isRead ? 'success' : 'info'">{{ email.isRead ? '已读' : '未读' }}</el-tag>
          </div>
=======
        >
>>>>>>> 07efba57c36be65aaa779c8378d84afb3a30e216
        </el-card>
      </el-col>
      <el-col v-if="!emails.length" :span="8">
        <div class="no-email">没有邮件</div>
      </el-col>
    </el-row>

<<<<<<< HEAD
    <!-- 使用Element UI的Dialog组件展示选中的邮件详情 -->
    <el-dialog :visible.sync="dialogVisible" title="邮件详情" width="60%">
      <div v-if="selectedEmail">
        <h3>{{ selectedEmail.emailTitle || '无标题' }}</h3>
        <p>发件人：{{ selectedEmail.userName }}</p>
        <p>发送时间：{{ formatDateTime(selectedEmail.sentDate) }}</p>
        <div v-html="selectedEmail.emailBody" class="email-body"></div>
      </div>
    </el-dialog>
=======
>>>>>>> 07efba57c36be65aaa779c8378d84afb3a30e216
  </div>
</template>

<style scoped>

</style>


<script>
<<<<<<< HEAD
import { listUser, getUserProfile } from "@/api/system/user";
// 导入API方法...

export default {
  name: "EmailList",
  data() {
    return {
      user: {}, // 当前用户
      emails: [], // 邮件列表
      selectedEmail: null, // 选中的邮件详情
      dialogVisible: false, // 控制详情对话框的显示
    };
  },
  created() {
    this.getUser();
  },
  methods: {
    getUser() {
      getUserProfile().then(response => {
        this.user = response.data;
        // 确保获取到user信息后再去获取邮件列表
        this.fetchEmails(); // 现在在这里调用，确保了user.userId不会是undefined
      });
    },

  }
}
=======

>>>>>>> 07efba57c36be65aaa779c8378d84afb3a30e216
</script>

<style scoped>

</style>
