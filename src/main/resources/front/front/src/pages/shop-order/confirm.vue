<template>
  <div :style='{"width":"100%","padding":"30px 7% 40px","margin":"0px auto","position":"relative","background":"#edeff1"}'>
    <div class="section-title" :style='{"padding":"12px 0 0","margin":"0px auto","color":"#333","textAlign":"center","background":"none","width":"100%","fontSize":"24px","fontWeight":"600","height":"60px"}'>
      Confirm {{ totalPrice > 0 ? 'Order' : 'Reservation' }}
    </div>

    <!-- Address -->
    <el-descriptions v-if="seat != 1" title="Select Shipping Address"></el-descriptions>
    <el-table v-if="seat != 1" :data="addressList" style="width: 100%;padding: 0 10px;">
      <el-table-column label="Select" width="55">
        <template slot-scope="scope">
          <el-radio
            v-model="radio"
            :label="scope.$index"
            @change.native="getCurrentRow(scope.$index)"
          >&nbsp;</el-radio>
        </template>
      </el-table-column>
      <el-table-column label="Consignee" prop="name"></el-table-column>
      <el-table-column label="Phone" prop="phone"></el-table-column>
      <el-table-column label="Address" prop="address"></el-table-column>
    </el-table>
    <div style="width:100%;display:flex;align-items:center;justify-content:center;margin:10px 0">
      <el-button type="success" @click="addAddress">Add Address</el-button>
    </div>

    <!-- Items -->
    <el-descriptions title="Item List"></el-descriptions>
    <el-table :data="tableData" style="width: 100%">
      <el-table-column label="Book">
        <template slot-scope="scope">
          <div class="shangpin">
            <el-image
              style="width: 100px; height: 100px"
              :src="baseUrl + scope.row.picture"
              fit="fill"
            ></el-image>
            <span style="margin-left: 10px">{{ scope.row.goodname }}</span>
          </div>
        </template>
      </el-table-column>

      <el-table-column v-if="totalPrice > 0" label="Price">
        <template slot-scope="scope">
          <span v-if="type == 2">{{ scope.row.price }} pts</span>
          <span v-else><span :style='{"fontSize":"12px"}'>$</span>{{ scope.row.price }}</span>
        </template>
      </el-table-column>

      <el-table-column v-if="seat == 1" label="Seat">
        <template slot-scope="scope">
          <span>{{ scope.row.activeSeat }}</span>
        </template>
      </el-table-column>

      <el-table-column v-if="seat != 1" label="Qty">
        <template slot-scope="scope">
          <span>{{ scope.row.buynumber }}</span>
        </template>
      </el-table-column>

      <el-table-column v-if="totalPrice > 0" label="Subtotal">
        <template slot-scope="scope">
          <span v-if="type == 2">{{ scope.row.price * scope.row.buynumber }} pts</span>
          <span v-else><span :style='{"fontSize":"12px"}'>$</span>{{ changePrice(scope.row) }}</span>
        </template>
      </el-table-column>
    </el-table>

    <!-- Remark -->
    <div style="width: 100%;padding: 12px;box-sizing: border-box;display: flex;align-items: center;">
      <span style="width: 80px; font-size: 14px;">Remark:</span>
      <el-input type="textarea" v-model="remark" placeholder="Remark" style="width: calc(100% - 80px);"></el-input>
    </div>

    <!-- Footer (no seat) -->
    <div class="buy" v-if="seat != 1 && tableData.length > 0">
      <template v-if="type == 2">
        <span style="color: red;margin-right: 10px;font-weight: 700;">Total: {{ totalPrice }} pts</span>
        <el-button type="warning" @click="jfClick">Redeem</el-button>
      </template>
      <template v-else>
        <span style="color: red;margin-right: 10px;font-weight: 700;">Total:
          <span :style='{"fontSize":"12px"}'>$</span>{{ totalPrice }}
        </span>
        <el-button type="success" @click="payClick">Pay</el-button>
      </template>
    </div>

    <!-- Footer (seat/reservation) -->
    <div class="buy" v-if="seat == 1 && tableData.length > 0">
      <template v-if="type == 2">
        <span style="color: red;margin-right: 10px;font-weight: 700;">Total: {{ totalPrice }} pts</span>
        <el-button type="success" @click="xzJfClick">Redeem</el-button>
      </template>
      <template v-else>
        <span style="color: red;margin-right: 10px;font-weight: 700;" v-if="totalPrice > 0">Total:
          <span :style='{"fontSize":"12px"}'>$</span>{{ totalPrice }}
        </span>
        <el-button type="success" @click="xzPayClick">{{ totalPrice > 0 ? 'Pay' : 'Reserve' }}</el-button>
      </template>
    </div>
  </div>
</template>

<script>
import config from '@/config/config'
export default {
  data() {
    return {
      baseUrl: config.baseUrl,
      userTableName: localStorage.getItem('UserTableName'),
      addressList: [],
      tableData: [],
      currentRow: null,
      radio: -1,
      order: {
        address: "",
        buynumber: 0,
        consignee: '',
        discountprice: 0,
        discounttotal: 0,
        goodid: 0,
        goodname: "",
        shangjiazhanghao: "",
        orderid: "",
        picture: "",
        price: 0,
        status: "未支付",
        tablename: "",
        tel: "",
        total: 0,
        type: 1,
        userid: localStorage.getItem('frontUserid')
      },
      user: {},
      type: 0,   // 1: cash, 2: points, others kept
      seat: 0,   // 1: seat/reservation mode
      buynumber: 0,
      remark: '',
      orderIdList: {},
    }
  },
  created() {
    if (this.$route.query.type != undefined) this.type = this.$route.query.type;
    if (this.$route.query.seat != undefined) this.seat = this.$route.query.seat;
    if (this.$route.query.tablename != undefined) this.order.tablename = this.$route.query.tablename;

    this.getSession();
    this.getAddrList();

    // cart buffer
    const orderGoods = localStorage.getItem('orderGoods');
    this.tableData = JSON.parse(orderGoods || '[]');
    this.type = this.$route.query.type;
  },
  methods: {
    changePrice(row) {
      const price = row.price * row.buynumber
      row.total = price.toFixed(2)
      return row.total
    },
    getSession() {
      if (!this.userTableName) {
        this.$router.push('/login');
        return false
      }
      this.$http.get(`${this.userTableName}/session`, { emulateJSON: true }).then(res => {
        if (res.data.code == 0) this.user = res.data.data;
      });
    },
    getCurrentRow(index) { this.radio = index; },
    getAddrList() {
      this.$http.get('address/list', { params: { userid: localStorage.getItem('frontUserid') } }).then(res => {
        if (res.data.code == 0) {
          this.addressList = res.data.data.list;
          this.addressList.forEach((item, index) => {
            // keep original backend value '是'
            if (item.isdefault == '是') this.radio = index;
          });
        }
      });
    },
    addAddress() { this.$router.push('/index/shop-address/addOrUpdate'); },

    getCartList() {
      this.$http.get('cart/list', { params: { page: 1, limit: 1000, userid: localStorage.getItem('frontUserid') } }).then(res => {
        if (res.data.code == 0) {
          this.tableData = res.data.data.list;
          if (this.$route.query.id != undefined || this.$route.query.type != undefined) {
            this.tableData = [];
            this.buynumber = this.$route.query.buynumber;
            const detailObj = JSON.parse(this.$route.query.detailObj);
            this.order.tablename = this.$route.query.tablename;

            if (this.$route.query.type == 1) {
              this.tableData.push({
                buynumber: this.buynumber,
                goodid: detailObj.id,
                picture: this.$route.query.picture,
                goodname: this.$route.query.goodname,
                tablename: this.$route.query.tablename,
                userid: localStorage.getItem('frontUserid'),
                price: this.$route.query.price
              });
            }
            if (this.$route.query.type == 2) {
              this.tableData.push({
                buynumber: this.buynumber,
                goodid: detailObj.id,
                picture: this.$route.query.picture,
                goodname: this.$route.query.goodname,
                tablename: this.$route.query.tablename,
                userid: localStorage.getItem('frontUserid'),
                jf: detailObj.jf
              });
            }
          }
        }
      });
    },

    handleChange(value, index, goodid) {
      this.$http.get(`${this.order.tablename}/detail/${goodid}`).then(res => {
        if (res.data.code == 0 && value > res.data.data.onelimittimes) {
          this.tableData[index].buynumber = --value;
          this.$message.error(`You can buy at most ${res.data.data.onelimittimes} per order.`);
        }
      });
    },

    // Pay with balance (normal order)
    payClick() {
      const that = this
      if (this.radio == -1) {
        this.$message({ message: 'Please select a shipping address', type: 'error', duration: 1500 });
        return;
      }
      for (let index in this.tableData) {
        const item = this.tableData[index]
        setTimeout(() => {
          this.$http.get(`${item.tablename}/info/${item.goodid}`).then(res => {
            this.orderIdList['orderId' + Number(index)] = String(this.createOrder())
            const data = res.data.data;
            // reduce stock
            data.alllimittimes = data.alllimittimes - item.buynumber;

            this.$http.post(`${item.tablename}/update`, data).then(() => {
              // create order
              const order = {
                orderid: this.orderIdList['orderId' + Number(index)],
                tablename: item.tablename,
                userid: this.user.id,
                goodid: item.goodid,
                goodname: item.goodname,
                shangjiazhanghao: item.shangjiazhanghao,
                picture: item.picture,
                buynumber: item.buynumber,
                discountprice: item.price,
                price: item.price,
                total: Number(item.total),
                discounttotal: Number(item.total),
                type: this.type ? Number(this.type) : 1,
                address: this.addressList[this.radio].address,
                tel: this.addressList[this.radio].phone,
                consignee: this.addressList[this.radio].name,
                remark: this.remark,
                status: '未支付',
                sfsh: '',
                role: this.userTableName,
              }
              this.$http.post('orders/add', order).then(() => {
                // balance check
                if (Number(this.user.money) < Number(item.total)) {
                  this.$message({
                    message: 'Insufficient balance, please recharge',
                    type: 'error',
                    duration: 1500,
                    onClose() { that.$router.push('/index/center') }
                  });
                  return
                }
                // reward points if defined
                if (data.jf) {
                  this.user.jf = Number((Number(this.user.jf) + Number(item.total)).toFixed(2))
                }
                this.user.money = Number((Number(this.user.money) - Number(item.total)).toFixed(2))
                this.$http.post(`${this.userTableName}/update`, this.user).then(() => {
                  localStorage.setItem('sessionForm', JSON.stringify(this.user))
                  order.status = '已支付'
                  const params = { orderid: this.orderIdList['orderId' + Number(index)], page: 1, limit: 1 }
                  this.$http.get('orders/list', { params }).then(r => {
                    order.id = r.data.data.list[0].id;
                    this.$http.post(`orders/update`, order).then(() => {
                      if (item.id) this.$http.post('cart/delete', [item.id])
                      this.$message({
                        message: 'Purchase successful',
                        type: 'success',
                        duration: 1500,
                        onClose: () => { this.$router.push('/index/shop-order/order'); }
                      });
                    });
                  });
                });
              });
            });
          });
        }, 500)
      }
    },

    // Redeem with points
    jfClick() {
      if (this.radio == -1) {
        this.$message({ message: 'Please select a shipping address', type: 'error', duration: 1500 });
        return;
      }
      for (let index in this.tableData) {
        const item = this.tableData[index]
        setTimeout(() => {
          this.orderIdList['orderId' + Number(index)] = String(this.createOrder())
          this.$http.get(`${item.tablename}/info/${item.goodid}`).then(res => {
            const data = res.data.data;
            data.alllimittimes = data.alllimittimes - item.buynumber;
            this.$http.post(`${item.tablename}/update`, data).then(() => {
              // points check
              if (this.user.jf < this.totalPrice) {
                this.$message({ message: 'Not enough points to redeem', type: 'error', duration: 1500 });
                return
              }
              const singleTotal = Number((item.price * item.buynumber).toFixed(2));
              const order = {
                orderid: this.orderIdList['orderId' + Number(index)],
                tablename: item.tablename,
                userid: this.user.id,
                goodid: item.goodid,
                goodname: item.goodname,
                shangjiazhanghao: item.shangjiazhanghao,
                picture: item.picture,
                buynumber: item.buynumber,
                discountprice: item.price,
                price: item.price,
                total: singleTotal,
                discounttotal: singleTotal,
                type: 2,
                total: this.totalPrice,
                address: this.addressList[this.radio].address,
                tel: this.addressList[this.radio].phone,
                consignee: this.addressList[this.radio].name,
                status: '已支付',
                sfsh: '',
                role: this.userTableName,
              }
              this.$http.post('orders/add', order).then(() => {
                this.user.jf = Number((Number(this.user.jf) - Number(item.price * item.buynumber)).toFixed(2))
                this.$http.post(`${this.userTableName}/update`, this.user).then(() => {
                  localStorage.setItem('sessionForm', JSON.stringify(this.user))
                  this.$message({
                    message: 'Redeemed successfully',
                    type: 'success',
                    duration: 1500,
                    onClose: () => { this.$router.push('/index/shop-order/order'); }
                  });
                });
              });
            });
          });
        }, 500)
      }
    },

    // Reservation-specific handlers (left as-is; you can wire them later)
    xzPayClick() {
      // TODO: implement if needed (kept to match your original flow)
      this.$message.success('Reserved / Paid successfully (stub)');
    },
    xzJfClick() {
      // TODO: implement if needed (kept to match your original flow)
      this.$message.success('Reservation redeemed (stub)');
    },

    createOrder() {
      let order = '';
      const now = new Date();
      order += now.getFullYear();
      order += now.getMonth() + 1;
      order += now.getDate();
      order += now.getHours();
      order += now.getMinutes();
      order += now.getSeconds();
      order += now.getMilliseconds();
      return order;
    }
  },
  computed: {
    totalPrice() {
      let totalPrice = 0;
      this.tableData.forEach(item => { totalPrice += item.price * item.buynumber; });
      return totalPrice.toFixed(2);
    },
    totalJifen() {
      let totalJifen = 0;
      this.tableData.forEach(item => { totalJifen += parseInt(item.jf * this.buynumber); });
      return totalJifen;
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.section { width: 900px; margin: 0 auto; }
.shangpin { display: flex; }
.buy { text-align: right; padding-right: 82px; }
</style>
