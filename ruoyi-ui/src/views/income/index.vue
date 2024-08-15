<template>
  <div>
    <div ref="chart" style="width: 600px; height: 400px;"></div>
    <!-- 调整总金额按钮 -->
    <el-button @click="showAdjustTotalAmountDialog = true">调整总金额</el-button>
    <!-- 调整已花费按钮 -->
    <el-button @click="showAdjustTotalSpentDialog = true">调整已花费</el-button>

    <!-- 对话框：调整总金额 -->
    <el-dialog
      title="调整总金额"
      :visible.sync="showAdjustTotalAmountDialog"
      width="30%"
    >
      <el-input v-model="newTotalAmount" placeholder="请输入新的总金额"></el-input>
      <span slot="footer" class="dialog-footer">
        <el-button @click="showAdjustTotalAmountDialog = false">取 消</el-button>
        <el-button type="primary" @click="adjustTotalAmount">确 定</el-button>
      </span>
    </el-dialog>

    <!-- 对话框：调整已花费 -->
    <el-dialog
      title="调整已花费"
      :visible.sync="showAdjustTotalSpentDialog"
      width="30%"
    >
      <el-input v-model="newTotalSpent" placeholder="请输入新的总花费"></el-input>
      <span slot="footer" class="dialog-footer">
        <el-button @click="showAdjustTotalSpentDialog = false">取 消</el-button>
        <el-button type="primary" @click="adjustTotalSpent">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import * as echarts from 'echarts';
import axios from 'axios';

export default {
  name: 'FinanceOverview',
  data() {
    return {
      chartInstance: null,
    };
  },
  mounted() {
    this.initChart();
    this.fetchData();
  },
  methods: {
    initChart() {
      this.chartInstance = echarts.init(this.$refs.chart);
      this.chartInstance.setOption({
        title: {
          text: '财务概览',
          left: 'center',
        },
        tooltip: {
          trigger: 'item',
          formatter: '{a} <br/>{b} : {c} ({d}%)',
        },
        legend: {
          orient: 'vertical',
          left: 'left',
          data: ['已花费', '剩余'],
        },
        series: [
          {
            name: '财务情况',
            type: 'pie',
            radius: '55%',
            center: ['50%', '60%'],
            data: [],
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)',
              },
            },
          },
        ],
      });
    },
    fetchData() {
      axios.get('/api/OA_api/GetMoneyAll')
        .then((response) => {
          const data = response.data;
          if (data.status && data.result) {
            const remaining = data.result.all - data.result.speet;
            console.log("还有剩余", remaining);
            this.chartInstance.setOption({
              series: [
                {
                  data: [
                    { value: data.result.speet, name: '已花费' },
                    { value: remaining, name: '剩余' },
                  ],
                },
              ],
            });
          }
        })
        .catch((error) => {
          console.error('Fetching data failed:', error);
        });
    },
    adjustTotalAmount() {
      const newTotal = prompt('请输入新的总金额:');
      if (newTotal !== null && newTotal.trim() !== '') {
        axios.get(`/api/OA_api/FixMoneyAll?moneyAll=${encodeURIComponent(newTotal)}`)
          .then((response) => {
            if (response.data.status) {
              this.fetchData(); // 重新获取数据以更新图表
            } else {
              alert('更新失败，请重试');
            }
          })
          .catch((error) => {
            console.error('Updating total amount failed:', error);
            alert('更新过程中出现错误，请检查控制台');
          });
      }
    },
    adjustTotalSpent() {
      const newSpent = prompt('请输入新的总花费:');
      if (newSpent !== null && newSpent.trim() !== '') {
        // 假设更新总花费的API是 `/api/OA_api/FixMoneySpent?moneySpent=`
        axios.get(`/api/OA_api/FixMoneySpeet?moneySpeet=${encodeURIComponent(newSpent)}`)
          .then((response) => {
            if (response.data.status) {
              this.fetchData(); // 重新获取数据以更新图表
            } else {
              alert('更新失败，请重试');
            }
          })
          .catch((error) => {
            console.error('Updating total spent failed:', error);
            alert('更新过程中出现错误，请检查控制台');
          });
      }
    },
  },
  beforeDestroy() {
    if (this.chartInstance) {
      this.chartInstance.dispose();
    }
  },
};
</script>


<style>
.btn {
  padding: 10px 20px;
  font-size: 16px;
  color: white;
  border: none;
  cursor: pointer;
  border-radius: 5px;
  margin-right: 10px; /* 为按钮添加右边距 */
  transition: background-color 0.3s ease;
}

.btn-primary {
  background-color: #007bff;
}

.btn-primary:hover {
  background-color: #0056b3;
}

.btn-secondary {
  background-color: #6c757d;
}

.btn-secondary:hover {
  background-color: #545b62;
}
</style>
