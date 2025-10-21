<template>
  <div :style='{"width":"100%","padding":"30px 7% 40px","margin":"0px auto","position":"relative","background":"#edeff1"}'>
    <div class="section-title" :style='{"padding":"12px 0 0","margin":"0px auto","color":"#333","textAlign":"center","background":"none","width":"100%","fontSize":"24px","fontWeight":"600","height":"60px"}'>Cart</div>
    <el-button size="small" type="danger" @click="delAllClick" :disabled="selRows.length?false:true">Delete</el-button>
    <el-table @selection-change="handleSelectionChange" :data="tableData" style="width: 100%">
      <el-table-column type="selection" width="50" align="center" />
      <el-table-column label="Book Name">
        <template slot-scope="scope">
          <div class="shangpin">
            <el-image style="width: 100px; height: 100px"
              :src="baseUrl + scope.row.picture" fit="fill"></el-image>
            <span style="margin-left: 10px">{{ scope.row.goodname }}</span>
          </div>
        </template>
      </el-table-column>
      <el-table-column label="Price">
        <template slot-scope="scope">
          <span :style='{"fontSize":"12px"}'>$</span>{{ scope.row.price }}
        </template>
      </el-table-column>
      <el-table-column label="Quantity">
        <template slot-scope="scope">
          <el-input-number :min="1" v-model="scope.row.buynumber" @change="(value) => handleChange(value, scope.$index, scope.row)" size="small"></el-input-number>
        </template>
      </el-table-column>
      <el-table-column label="Sum">
        <template slot-scope="scope">
          <span :style='{"fontSize":"12px"}'>$</span>{{ (scope.row.price * scope.row.buynumber).toFixed(2) }}
        </template>
      </el-table-column>
      <el-table-column label="Action" width="150">
        <template slot-scope="scope">
          <el-button size="mini" type="danger"
            @click="handleDelete(scope.$index, scope.row)">Delete</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div class="buy" v-if="tableData.length > 0">
      <span style="color: red;margin-right: 10px;font-weight: 700;">Sum: <span :style='{"fontSize":"12px"}'>$</span>{{totalPrice}}</span>
      <el-button type="warning" @click="buyClick">Buy</el-button>
    </div>
  </div>
</template>

<script>
import config from '@/config/config'
export default {
  data() {
    return {
      baseUrl: config.baseUrl,
      tableData: [],
      selRows:[]
    }
  },
  created() {
    this.getCartList();
  },
  methods: {
    handleSelectionChange(e){
      this.selRows = e
    },
    getCartList() {
      this.$http.get('cart/list', {params: {page: 1, limit: 1000, userid: localStorage.getItem('frontUserid')}}).then(res => {
        if (res.data.code == 0) {
          this.tableData = res.data.data.list;
        }
      });
    },
    handleChange(value, index, row) {
      this.$http.get(`${row.tablename}/detail/${row.goodid}`).then(res => {
        if (res.data.code == 0) {
          if (res.data.data.onelimittimes&&res.data.data.onelimittimes > 0) {
            if (value > 5) {
              row.buynumber=5;
              this.$message.error(`You can only buy 5 books at most`);
            } else {
              row.buynumber = value;
              this.$http.post('cart/update', row).then(res => {});
            }
          } else {
            row.buynumber = value;
            this.$http.post('cart/update', row).then(res => {});
          }
        }
      });
    },
    handleDelete(index, row) {
      this.$confirm('Do you want to delete the item?', 'Notice', {
        confirmButtonText: 'Confirm',
        cancelButtonText: 'Cancel',
        type: 'warning'
      }).then(() => {
        let delIds = new Array();
        delIds.push(row.id);
        this.$http.post('cart/delete', delIds).then(res => {
          if (res.data.code == 0) {
            this.$message({
              type: 'success',
              message: 'Delete Successfully!',
              duration: 1500,
              onClose: () => {
                this.getCartList();
              }
            });
          }
        });
      });
    },
    delAllClick(){
      this.$confirm('Are you sure you want to delete the selected books?', 'Notice', {
        confirmButtonText: 'Confirm',
        cancelButtonText: 'Cancel',
        type: 'warning'
      }).then(() => {
        let delIds = new Array();
        for(let x in this.selRows){
          delIds.push(this.selRows[x].id)
        }
        this.$http.post('cart/delete', delIds).then(res => {
          if (res.data.code == 0) {
            this.$message({
              type: 'success',
              message: 'Deleted successfully!',
              duration: 1500,
              onClose: () => {
                this.getCartList();
              }
            });
          }
        });
      });
    },
    buyClick() {
      if(this.selRows.length==0) {
        this.$message({
          type: 'warning',
          message: 'Please select book(s).',
          duration: 1500,
        });
        return
      }
      var gn = "";
      for(let x in this.selRows){
        var g = this.selRows[x];
        console.log(g.goodname)
        let that = this;
        this.$http.get(`${g.tablename}/detail/${g.goodid}`).then(res => {
          if (res.data.code == 0 && g.buynumber > res.data.data.alllimittimes) {
            gn = g.goodname;
          }
        });
      }
    
      setTimeout(() => {
        if(gn=="") {
          localStorage.setItem('orderGoods', JSON.stringify(this.selRows));
          this.$router.push('/index/shop-order/orderConfirm');
        } else {
          this.$message.error(gn+` is out of stock`);
          return;
        }
      }, 200)
    }
  },
  computed: {
    totalPrice() {
      let totalPrice = 0;
      for(let x in this.selRows){
          totalPrice += Number(this.selRows[x].price) * Number(this.selRows[x].buynumber)
      }
      return totalPrice.toFixed(2);
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
  .section {
    width: 900px;
    margin: 0 auto;
  }
  
  .shangpin {
    display: flex;
  }
  .buy {
    text-align: right;
    padding-right: 82px;
  }
</style>
