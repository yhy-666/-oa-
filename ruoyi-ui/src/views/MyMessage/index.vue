<template>
  <div id="app">
    <!-- 使用Element UI的Card组件展示邮件列表 -->
    <el-row :gutter="20">
      <el-col :span="8" v-for="email in emails" :key="email.emailID">
        <el-card
          @click.native="fetchEmailDetails(email.emailID)"
          :style="{ backgroundColor: email.isRead ? '#FFFFFF' : '#90EE90' }"
        >
          <div class="email-info">
            <p>{{ email.userName }} - {{ email.emailTitle || '无标题' }}</p>
            <p>发送时间：{{ formatDateTime(email.sentDate) }}</p>
            <el-tag :type="email.isRead ? 'success' : 'info'">{{ email.isRead ? '已读' : '未读' }}</el-tag>
          </div>
        </el-card>
      </el-col>
      <el-col v-if="!emails.length" :span="8">
        <div class="no-email">没有邮件</div>
      </el-col>
    </el-row>

    <!-- 使用Element UI的Dialog组件展示选中的邮件详情 -->
    <el-dialog :visible.sync="dialogVisible" title="邮件详情" width="60%">
      <div v-if="selectedEmail">
        <h3>{{ selectedEmail.emailTitle || '无标题' }}</h3>
        <p>发件人：{{ selectedEmail.userName }}</p>
        <p>发送时间：{{ formatDateTime(selectedEmail.sentDate) }}</p>
        <div v-html="selectedEmail.emailBody" class="email-body"></div>
        <p v-if="selectedEmail.fileName">
          附件：
          <el-button type="primary" size="small" @click.stop="downloadFile(selectedEmail.fileName)">下载附件</el-button>
        </p>
      </div>
    </el-dialog>
  </div>
</template>

<style scoped>
.email-info {
  cursor: pointer;
}
.email-body {
  margin-top: 20px;
}
.no-email {
  text-align: center;
  color: #999;
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  font-size: 24px;
}
</style>


<script>
import axios from 'axios';
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
      }).catch(error => {
        console.error("获取当前用户信息失败:", error);
      });
    },
    fetchEmails() {
      axios.get(`/api/EmailSend/GetEmailsForUser?toUserId=${this.user.userId}`)
        .then(response => {
          this.emails = response.data.$values.sort((a, b) => {
            // 将日期字符串转换为日期对象进行比较
            return new Date(b.sentDate) - new Date(a.sentDate);
          });
        })
        .catch(error => {
          console.error("获取邮件列表失败:", error);
        });
    },

    fetchEmailDetails(emailId) {
      axios.get(`/api/EmailSend/GetEmail?emailId=${emailId}`)
        .then(response => {
          this.selectedEmail = response.data;
          this.dialogVisible = true; // 显示对话框
          this.markEmailAsRead(emailId); // 标记邮件为已读
        })
        .catch(error => {
          console.error("获取邮件详情失败:", error);
        });
    },
    markEmailAsRead(emailId) {
      axios.get(`/api/EmailSend/FixReaded?emailId=${emailId}`)
        .then(() => {
          const email = this.emails.find(e => e.emailID === emailId);
          if (email) {
            email.isRead = true; // 更新本地状态
          }
        })
        .catch(error => {
          console.error("标记邮件为已读失败:", error);
        });
    },
    downloadFile(fileName) {
      const downloadUrl = `/api/FileUpload/download/${fileName}`;
      window.open(downloadUrl);
    },
    // 时间显示格式化
    formatDateTime(dateTimeStr) {
      const date = new Date(dateTimeStr);
      return date.toLocaleString('zh-CN', { hour12: false }).replace(/\//g, '-').slice(0, -3);
    }
  }
}
</script>

<style scoped>
.email-info {
  cursor: pointer;
}
.email-body {
  margin-top: 20px;
}
</style>
