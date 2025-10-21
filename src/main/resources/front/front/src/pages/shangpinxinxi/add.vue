<template>
  <div :style='{"width":"100%","padding":"30px 7% 40px","margin":"0px auto","position":"relative","background":"#edeff1"}'>
    <el-form
      :style='{"border":"0px solid #28890b30","width":"100%","padding":"30px","position":"relative","background":"none"}'
      class="add-update-preview"
      ref="ruleForm"
      :model="ruleForm"
      :rules="rules"
      label-width="150px"
    >
      <el-form-item :style='{"width":"100%","padding":"10px","margin":"0 0 10px","background":"none","display":"inline-block"}' label="Book Title" prop="shangpinmingcheng">
        <el-input
          v-model="ruleForm.shangpinmingcheng"
          placeholder="Book Title"
          clearable
          :disabled="false || ro.shangpinmingcheng">
        </el-input>
      </el-form-item>

      <el-form-item :style='{"width":"100%","padding":"10px","margin":"0 0 10px","background":"none","display":"inline-block"}' label="Store Name" prop="dianpuming">
        <el-select v-model="ruleForm.dianpuming" placeholder="Select store" :disabled="false || ro.dianpuming">
          <el-option
            v-for="(item,index) in dianpumingOptions"
            :key="index"
            :label="item"
            :value="item">
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item :style='{"width":"100%","padding":"10px","margin":"0 0 10px","background":"none","display":"inline-block"}' label="Book Category" prop="shangpinfenlei">
        <el-select v-model="ruleForm.shangpinfenlei" placeholder="Select category" :disabled="false || ro.shangpinfenlei">
          <el-option
            v-for="(item,index) in shangpinfenleiOptions"
            :key="index"
            :label="item"
            :value="item">
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item :style='{"width":"100%","padding":"10px","margin":"0 0 10px","background":"none","display":"inline-block"}' label="Brand" prop="pinpai">
        <el-input
          v-model="ruleForm.pinpai"
          placeholder="Brand"
          clearable
          :disabled="false || ro.pinpai">
        </el-input>
      </el-form-item>

      <el-form-item :style='{"width":"100%","padding":"10px","margin":"0 0 10px","background":"none","display":"inline-block"}' label="Specification" prop="guige">
        <el-input
          v-model="ruleForm.guige"
          placeholder="Specification"
          clearable
          :disabled="false || ro.guige">
        </el-input>
      </el-form-item>

      <el-form-item :style='{"width":"100%","padding":"10px","margin":"0 0 10px","background":"none","display":"inline-block"}' label="Release Date" prop="shangjiariqi">
        <el-date-picker
          :disabled="false || ro.shangjiariqi"
          format="yyyy-MM-dd"
          value-format="yyyy-MM-dd"
          v-model="ruleForm.shangjiariqi"
          type="date"
          placeholder="Select date">
        </el-date-picker>
      </el-form-item>

      <el-form-item :style='{"width":"100%","padding":"10px","margin":"0 0 10px","background":"none","display":"inline-block"}' label="Vendor Account" prop="shangjiazhanghao">
        <el-input
          v-model="ruleForm.shangjiazhanghao"
          placeholder="Vendor account"
          clearable
          :disabled="false || ro.shangjiazhanghao">
        </el-input>
      </el-form-item>

      <el-form-item :style='{"width":"100%","padding":"10px","margin":"0 0 10px","background":"none","display":"inline-block"}' label="Cover" v-if="type!='cross' || (type=='cross' && !ro.fengmian)" prop="fengmian">
        <file-upload
          tip="Click to upload cover"
          action="file/upload"
          :limit="3"
          :multiple="true"
          :fileUrls="ruleForm.fengmian ? ruleForm.fengmian : ''"
          @change="fengmianUploadChange">
        </file-upload>
      </el-form-item>

      <el-form-item :style='{"width":"100%","padding":"10px","margin":"0 0 10px","background":"none","display":"inline-block"}' class="upload" v-else label="Cover" prop="fengmian">
        <img v-if="ruleForm.fengmian.substring(0,4)=='http'" class="upload-img" style="margin-right:20px;" v-bind:key="index" :src="ruleForm.fengmian.split(',')[0]" width="100" height="100">
        <img v-else class="upload-img" style="margin-right:20px;" v-bind:key="index" v-for="(item,index) in ruleForm.fengmian.split(',')" :src="baseUrl+item" width="100" height="100">
      </el-form-item>

      <el-form-item :style='{"width":"100%","padding":"10px","margin":"0 0 10px","background":"none","display":"inline-block"}' label="Per-Order Limit" prop="onelimittimes">
        <el-input
          v-model.number="ruleForm.onelimittimes"
          placeholder="Per-order limit"
          clearable
          :disabled="false || ro.onelimittimes">
        </el-input>
      </el-form-item>

      <el-form-item :style='{"width":"100%","padding":"10px","margin":"0 0 10px","background":"none","display":"inline-block"}' label="Stock" prop="alllimittimes">
        <el-input
          v-model.number="ruleForm.alllimittimes"
          placeholder="Stock"
          clearable
          :disabled="false || ro.alllimittimes">
        </el-input>
      </el-form-item>

      <el-form-item :style='{"width":"100%","padding":"10px","margin":"0 0 10px","background":"none","display":"inline-block"}' label="Points" prop="jf">
        <el-input
          v-model.number="ruleForm.jf"
          placeholder="Points"
          clearable
          :disabled="false || ro.jf">
        </el-input>
      </el-form-item>

      <el-form-item :style='{"width":"100%","padding":"10px","margin":"0 0 10px","background":"none","display":"inline-block"}' label="Price" prop="price">
        <el-input-number v-model="ruleForm.price" placeholder="Price" :disabled="ro.price"></el-input-number>
      </el-form-item>

      <el-form-item :style='{"width":"100%","padding":"10px","margin":"0 0 10px","background":"none","display":"inline-block"}' label="Book Description" prop="shangpinjieshao">
        <editor
          :style='{"minHeight":"250px","padding":"0","margin":"0","borderColor":"#1abc9e30","backgroundColor":"#fff","borderRadius":"0","borderWidth":"0px","width":"100%","borderStyle":"solid","height":"auto"}'
          v-model="ruleForm.shangpinjieshao"
          class="editor"
          action="file/upload">
        </editor>
      </el-form-item>

      <el-form-item :style='{"padding":"0","margin":"10px 0 0"}'>
        <el-button
          :style='{"border":"0","cursor":"pointer","padding":"0","margin":"0 20px 0 0","outline":"none","color":"rgba(255, 255, 255, 1)","borderRadius":"20px","background":"#3795c4","width":"128px","lineHeight":"44px","fontSize":"16px","height":"44px"}'
          type="primary"
          @click="onSubmit">
          Submit
        </el-button>
        <el-button
          :style='{"border":"1px solid #3795c4","cursor":"pointer","padding":"0","margin":"0","outline":"none","color":"#3795c4","borderRadius":"30px","background":"none","width":"128px","lineHeight":"44px","fontSize":"16px","height":"44px"}'
          @click="back()">
          Back
        </el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  data() {
    let self = this
    return {
      id: '',
      baseUrl: '',
      ro: {
        shangpinmingcheng: false,
        dianpuming: false,
        shangpinfenlei: false,
        pinpai: false,
        guige: false,
        shangjiariqi: false,
        shangpinjieshao: false,
        shangjiazhanghao: false,
        fengmian: false,
        onelimittimes: false,
        alllimittimes: false,
        thumbsupnum: false,
        crazilynum: false,
        clicktime: false,
        clicknum: false,
        discussnum: false,
        jf: false,
        price: false,
        storeupnum: false,
      },
      type: '',
      userTableName: localStorage.getItem('UserTableName'),
      ruleForm: {
        shangpinmingcheng: '',
        dianpuming: '',
        shangpinfenlei: '',
        pinpai: '',
        guige: '',
        shangjiariqi: '',
        shangpinjieshao: '',
        shangjiazhanghao: '',
        fengmian: '',
        onelimittimes: Number('-1'),
        alllimittimes: Number('-1'),
        thumbsupnum: '',
        crazilynum: '',
        clicktime: '',
        clicknum: '',
        discussnum: '',
        jf: '',
        price: '',
        storeupnum: '',
      },
      dianpumingOptions: [],
      shangpinfenleiOptions: [],

      rules: {
        shangpinmingcheng: [{ required: true, message: 'Book title is required', trigger: 'blur' }],
        dianpuming: [],
        shangpinfenlei: [{ required: true, message: 'Book category is required', trigger: 'blur' }],
        pinpai: [{ required: true, message: 'Brand is required', trigger: 'blur' }],
        guige: [],
        shangjiariqi: [],
        shangpinjieshao: [],
        shangjiazhanghao: [],
        fengmian: [],
        onelimittimes: [{ validator: this.$validate.isIntNumer, trigger: 'blur' }],
        alllimittimes: [{ validator: this.$validate.isIntNumer, trigger: 'blur' }],
        thumbsupnum: [{ validator: this.$validate.isIntNumer, trigger: 'blur' }],
        crazilynum: [{ validator: this.$validate.isIntNumer, trigger: 'blur' }],
        clicktime: [],
        clicknum: [{ validator: this.$validate.isIntNumer, trigger: 'blur' }],
        discussnum: [{ validator: this.$validate.isIntNumer, trigger: 'blur' }],
        jf: [{ validator: this.$validate.isIntNumer, trigger: 'blur' }],
        price: [
          { required: true, message: 'Price is required', trigger: 'blur' },
          { validator: this.$validate.isNumber, trigger: 'blur' },
        ],
        storeupnum: [{ validator: this.$validate.isIntNumer, trigger: 'blur' }],
      },
      centerType: false,
    };
  },
  created() {
    if (this.$route.query.centerType) {
      this.centerType = true
    }
    const type = this.$route.query.type ? this.$route.query.type : ''
    this.init(type)
    this.baseUrl = this.$config.baseUrl
    this.ruleForm.shangjiariqi = this.getCurDate()
  },
  methods: {
    getMakeZero(s) {
      return s < 10 ? '0' + s : s;
    },
    // download
    download(file) {
      window.open(`${file}`)
    },
    // init
    init(type) {
      this.type = type;
      if (type == 'cross') {
        var obj = JSON.parse(localStorage.getItem('crossObj'));
        for (var o in obj) {
          if (o == 'shangpinmingcheng') { this.ruleForm.shangpinmingcheng = obj[o]; this.ro.shangpinmingcheng = true; continue; }
          if (o == 'dianpuming')        { this.ruleForm.dianpuming = obj[o]; this.ro.dianpuming = true; continue; }
          if (o == 'shangpinfenlei')     { this.ruleForm.shangpinfenlei = obj[o]; this.ro.shangpinfenlei = true; continue; }
          if (o == 'pinpai')             { this.ruleForm.pinpai = obj[o]; this.ro.pinpai = true; continue; }
          if (o == 'guige')              { this.ruleForm.guige = obj[o]; this.ro.guige = true; continue; }
          if (o == 'shangjiariqi')       { this.ruleForm.shangjiariqi = obj[o]; this.ro.shangjiariqi = true; continue; }
          if (o == 'shangpinjieshao')    { this.ruleForm.shangpinjieshao = obj[o]; this.ro.shangpinjieshao = true; continue; }
          if (o == 'shangjiazhanghao')   { this.ruleForm.shangjiazhanghao = obj[o]; this.ro.shangjiazhanghao = true; continue; }
          if (o == 'fengmian')           { this.ruleForm.fengmian = obj[o].split(",")[0]; this.ro.fengmian = true; continue; }
          if (o == 'onelimittimes')      { this.ruleForm.onelimittimes = obj[o]; this.ro.onelimittimes = true; continue; }
          if (o == 'alllimittimes')      { this.ruleForm.alllimittimes = obj[o]; this.ro.alllimittimes = true; continue; }
          if (o == 'thumbsupnum')        { this.ruleForm.thumbsupnum = obj[o]; this.ro.thumbsupnum = true; continue; }
          if (o == 'crazilynum')         { this.ruleForm.crazilynum = obj[o]; this.ro.crazilynum = true; continue; }
          if (o == 'clicktime')          { this.ruleForm.clicktime = obj[o]; this.ro.clicktime = true; continue; }
          if (o == 'clicknum')           { this.ruleForm.clicknum = obj[o]; this.ro.clicknum = true; continue; }
          if (o == 'discussnum')         { this.ruleForm.discussnum = obj[o]; this.ro.discussnum = true; continue; }
          if (o == 'jf')                 { this.ruleForm.jf = obj[o]; this.ro.jf = true; continue; }
          if (o == 'price')              { this.ruleForm.price = obj[o]; this.ro.price = true; continue; }
          if (o == 'storeupnum')         { this.ruleForm.storeupnum = obj[o]; this.ro.storeupnum = true; continue; }
        }
      } else if (type == 'edit') {
        this.info()
      }

      // user session -> prefill
      this.$http.get(this.userTableName + '/session', { emulateJSON: true }).then(res => {
        if (res.data.code == 0) {
          var json = res.data.data;
          if ((json.dianpuming !== '' && json.dianpuming) || json.dianpuming == 0) {
            this.ruleForm.dianpuming = json.dianpuming;
            this.ro.dianpuming = true;
          }
          if ((json.shangjiazhanghao !== '' && json.shangjiazhanghao) || json.shangjiazhanghao == 0) {
            this.ruleForm.shangjiazhanghao = json.shangjiazhanghao;
            this.ro.shangjiazhanghao = true;
          }
        }
      });
      this.$http.get('option/shangjia/dianpuming', { emulateJSON: true }).then(res => {
        if (res.data.code == 0) this.dianpumingOptions = res.data.data;
      });
      this.$http.get('option/shangpinfenlei/shangpinfenlei', { emulateJSON: true }).then(res => {
        if (res.data.code == 0) this.shangpinfenleiOptions = res.data.data;
      });

      if (localStorage.getItem('raffleType')) {
        localStorage.removeItem('raffleType')
        setTimeout(() => this.onSubmit(), 300)
      }
    },

    // detail (edit)
    info() {
      this.$http.get(`shangpinxinxi/detail/${this.$route.query.id}`, { emulateJSON: true }).then(res => {
        if (res.data.code == 0) {
          this.ruleForm = res.data.data;
        }
      });
    },

    // submit
    onSubmit() {
      if (this.ruleForm.price < 0) {
        this.$message.error("Price cannot be negative");
        return;
      }
      if (this.ruleForm.alllimittimes < 0) {
        this.$message.error("Stock cannot be negative");
        return;
      }
      if (this.ruleForm.onelimittimes < 0) {
        this.$message.error("Per-order limit cannot be negative");
        return;
      }

      // cross-table updates
      var crossuserid;
      var crossrefid;
      var crossoptnum;

      this.$refs["ruleForm"].validate(valid => {
        if (!valid) return;

        if (this.type == 'cross') {
          var statusColumnName = localStorage.getItem('statusColumnName');
          var statusColumnValue = localStorage.getItem('statusColumnValue');
          if (statusColumnName && statusColumnName != '') {
            var obj = JSON.parse(localStorage.getItem('crossObj'));
            if (!statusColumnName.startsWith("[")) {
              for (var o in obj) {
                if (o == statusColumnName) obj[o] = statusColumnValue;
              }
              var table = localStorage.getItem('crossTable');
              this.$http.post(table + '/update', obj).then(res => {});
            } else {
              crossuserid = Number(localStorage.getItem('frontUserid'));
              crossrefid = obj['id'];
              crossoptnum = localStorage.getItem('statusColumnName');
              crossoptnum = crossoptnum.replace(/\[/, "").replace(/\]/, "");
            }
          }
        }

        if (crossrefid && crossuserid) {
          this.ruleForm.crossuserid = crossuserid;
          this.ruleForm.crossrefid = crossrefid;
          var params = { page: 1, limit: 10, crossuserid: crossuserid, crossrefid: crossrefid }
          this.$http.get('shangpinxinxi/list', { params }).then(res => {
            if (res.data.data.total >= crossoptnum) {
              this.$message({
                message: localStorage.getItem('tips'),
                type: 'error',
                duration: 1500,
              });
              return false;
            } else {
              this.$http.post(`shangpinxinxi/${this.ruleForm.id ? 'update' : this.centerType ? 'save' : 'add'}`, this.ruleForm).then(res => {
                if (res.data.code == 0) {
                  this.$message({
                    message: 'Operation successful',
                    type: 'success',
                    duration: 1500,
                    onClose: () => { this.$router.go(-1); }
                  });
                } else {
                  this.$message({ message: res.data.msg, type: 'error', duration: 1500 });
                }
              });
            }
          });
        } else {
          this.$http.post(`shangpinxinxi/${this.ruleForm.id ? 'update' : this.centerType ? 'save' : 'add'}`, this.ruleForm).then(res => {
            if (res.data.code == 0) {
              this.$message({
                message: 'Operation successful',
                type: 'success',
                duration: 1500,
                onClose: () => { this.$router.go(-1); }
              });
            } else {
              this.$message({ message: res.data.msg, type: 'error', duration: 1500 });
            }
          });
        }
      });
    },

    // uuid
    getUUID() {
      return new Date().getTime();
    },

    // back
    back() {
      this.$router.go(-1);
    },

    fengmianUploadChange(fileUrls) {
      this.ruleForm.fengmian = fileUrls.replace(new RegExp(this.$config.baseUrl, "g"), "");
    },

    // util: current date yyyy-MM-dd (你原组件里调用了 getCurDate，这里假设存在；若无可添加)
    getCurDate() {
      const d = new Date();
      const mm = (d.getMonth() + 1).toString().padStart(2, '0');
      const dd = d.getDate().toString().padStart(2, '0');
      return `${d.getFullYear()}-${mm}-${dd}`;
    }
  }
};
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.el-date-editor.el-input {
  width: auto;
}

.add-update-preview .el-form-item /deep/ .el-form-item__label {
  padding: 0 10px 0 0;
  color: #666;
  font-weight: 500;
  width: 150px;
  font-size: 14px;
  line-height: 40px;
  text-align: right;
}

.add-update-preview .el-form-item /deep/ .el-form-item__content {
  margin-left: 150px;
}

.add-update-preview .el-input /deep/ .el-input__inner {
  padding: 0 12px;
  color: #666;
  font-size: 14px;
  border-color: #ddd;
  border-radius: 4px;
  box-shadow: 0 0 0px rgba(64, 158, 255, .5);
  outline: none;
  background: #fff;
  width: auto;
  border-width: 0px;
  border-style: solid;
  min-width: 500px;
  height: 40px;
}

.add-update-preview .el-input-number /deep/ .el-input__inner {
  text-align: left;
  padding: 0 12px;
  color: #666;
  font-size: 14px;
  border-color: #ddd;
  border-radius: 4px;
  box-shadow: 0 0 0px rgba(64, 158, 255, .5);
  outline: none;
  background: #fff;
  width: auto;
  border-width: 0px;
  border-style: solid;
  min-width: 500px;
  height: 40px;
}
.add-update-preview .el-input-number /deep/ .el-input-number__decrease { display: none; }
.add-update-preview .el-input-number /deep/ .el-input-number__increase { display: none; }

.add-update-preview .el-select /deep/ .el-input__inner {
  border-radius: 4px;
  padding: 0 10px;
  color: #666;
  background: #fff;
  width: auto;
  font-size: 14px;
  border-color: #28890b30;
  border-width: 0px;
  border-style: solid;
  min-width: 500px;
  height: 40px;
}

.add-update-preview .el-date-editor /deep/ .el-input__inner {
  border-radius: 4px;
  padding: 0 10px 0 30px;
  color: #666;
  background: #fff;
  width: auto;
  font-size: 14px;
  border-color: #28890b30;
  border-width: 0px;
  border-style: solid;
  min-width: 500px;
  height: 40px;
}

.add-update-preview /deep/ .el-upload--picture-card {
  background: transparent;
  border: 0;
  border-radius: 0;
  width: auto;
  height: auto;
  line-height: initial;
  vertical-align: middle;
}

.add-update-preview /deep/ .upload .upload-img,
.add-update-preview /deep/ .el-upload-list .el-upload-list__item,
.add-update-preview /deep/ .el-upload .el-icon-plus {
  cursor: pointer;
  color: #333;
  font-weight: 600;
  font-size: 24px;
  border-color: #28890b30;
  line-height: 80px;
  border-radius: 4px;
  background: #fff;
  width: 80px;
  border-width: 0px;
  border-style: solid;
  text-align: center;
  height: 80px;
}

.add-update-preview .el-textarea /deep/ .el-textarea__inner {
  border: 0px solid #eee;
  border-radius: 0px;
  padding: 12px;
  box-shadow: 0 0 0px rgba(64, 158, 255, .5);
  outline: none;
  color: #666;
  background: #fff;
  width: auto;
  font-size: 14px;
  min-width: 800px;
  height: 120px;
}
</style>
