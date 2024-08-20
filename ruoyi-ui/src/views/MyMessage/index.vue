<template>
  <div id="app">
    <!-- 使用Element UI的Card组件展示邮件列表 -->
    <el-row :gutter="20">
      <el-col :span="8" v-for="email in emails" :key="email.emailID">
        <el-card
          @click.native="fetchEmailDetails(email.emailID)"
        >
        </el-card>
      </el-col>
      <el-col v-if="!emails.length" :span="8">
        <div class="no-email">没有邮件</div>
      </el-col>
    </el-row>

  </div>
</template>

<style scoped>

</style>


<script>

</script>

<style scoped>

</style>
