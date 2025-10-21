<template>
  <div>
    <div
      class="container"
      :style="{
        minHeight: '100vh',
        alignItems: 'center',
        background:
          'url(http://codegen.caihongy.cn/20240305/c46698d0e9e14ad1b5729fb8a33a6d90.jpg)',
        display: 'flex',
        width: '100%',
        backgroundSize: 'cover',
        backgroundPosition: 'center center',
        backgroundRepeat: 'no-repeat',
        justifyContent: 'center'
      }"
    >
      <el-form
        class="rgs-form"
        v-if="pageFlag === 'register'"
        :style="{
          minHeight: '800px',
          padding: '20px 2vw 0 5vw',
          margin: '40px auto 40px',
          borderRadius: '0px',
          flexWrap: 'wrap',
          background: '#fff',
          display: 'flex',
          width: '40vw',
          position: 'relative',
          height: 'auto'
        }"
        ref="registerForm"
        :model="registerForm"
        :rules="rules"
      >
        <div
          :style="{
            margin: '20px auto 20px',
            color: '#2a8ec0',
            textAlign: 'center',
            width: '90%',
            letterSpacing: '2px',
            lineHeight: '44px',
            fontSize: '32px',
            fontWeight: '600'
          }"
        >
          Sign Up
        </div>

        <!-- User registration -->
        <el-form-item :style="itemStyle" v-if="tableName === 'yonghu'" prop="zhanghao">
          <div :style="labelStyle" :class="isRequired('zhanghao') ? 'required' : ''">Username:</div>
          <el-input v-model="registerForm.zhanghao" placeholder="Enter username" />
        </el-form-item>

        <el-form-item :style="itemStyle" v-if="tableName === 'yonghu'" prop="mima">
          <div :style="labelStyle" :class="isRequired('mima') ? 'required' : ''">Password:</div>
          <el-input v-model="registerForm.mima" type="password" placeholder="Enter password" />
        </el-form-item>

        <el-form-item :style="itemStyle" v-if="tableName === 'yonghu'" prop="mima2">
          <div :style="labelStyle" :class="isRequired('mima') ? 'required' : ''">Confirm Password:</div>
          <el-input v-model="registerForm.mima2" type="password" placeholder="Re-enter password" />
        </el-form-item>

        <el-form-item :style="itemStyle" v-if="tableName === 'yonghu'" prop="xingming">
          <div :style="labelStyle" :class="isRequired('xingming') ? 'required' : ''">Full Name:</div>
          <el-input v-model="registerForm.xingming" placeholder="Enter full name" />
        </el-form-item>

        <el-form-item :style="itemStyle" v-if="tableName === 'yonghu'" prop="xingbie">
          <div :style="labelStyle" :class="isRequired('xingbie') ? 'required' : ''">Gender:</div>
          <el-select v-model="registerForm.xingbie" placeholder="Select gender">
            <el-option
              v-for="(item, index) in yonghuxingbieOptions"
              :key="index"
              :label="item"
              :value="item">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item :style="itemStyle" v-if="tableName === 'yonghu'" prop="shouji">
          <div :style="labelStyle" :class="isRequired('shouji') ? 'required' : ''">Mobile Number:</div>
          <el-input v-model="registerForm.shouji" placeholder="+61..." />
        </el-form-item>

        <el-form-item :style="itemStyle" v-if="tableName === 'yonghu'" prop="touxiang">
          <div :style="labelStyle" :class="isRequired('touxiang') ? 'required' : ''">Avatar:</div>
          <file-upload
            tip="Click to upload avatar"
            action="file/upload"
            :limit="1"
            :multiple="true"
            :fileUrls="registerForm.touxiang ? registerForm.touxiang : ''"
            @change="yonghutouxiangUploadChange"
          />
        </el-form-item>

        <!-- Vendor registration -->
        <el-form-item :style="itemStyle" v-if="tableName === 'shangjia'" prop="shangjiazhanghao">
          <div :style="labelStyle" :class="isRequired('shangjiazhanghao') ? 'required' : ''">Vendor Account:</div>
          <el-input v-model="registerForm.shangjiazhanghao" placeholder="Enter vendor account" />
        </el-form-item>

        <el-form-item :style="itemStyle" v-if="tableName === 'shangjia'" prop="mima">
          <div :style="labelStyle" :class="isRequired('mima') ? 'required' : ''">Password:</div>
          <el-input v-model="registerForm.mima" type="password" placeholder="Enter password" />
        </el-form-item>

        <el-form-item :style="itemStyle" v-if="tableName === 'shangjia'" prop="mima2">
          <div :style="labelStyle" :class="isRequired('mima') ? 'required' : ''">Confirm Password:</div>
          <el-input v-model="registerForm.mima2" type="password" placeholder="Re-enter password" />
        </el-form-item>

        <el-form-item :style="itemStyle" v-if="tableName === 'shangjia'" prop="dianpuming">
          <div :style="labelStyle" :class="isRequired('dianpuming') ? 'required' : ''">Shop Name:</div>
          <el-input v-model="registerForm.dianpuming" placeholder="Enter shop name" />
        </el-form-item>

        <el-form-item :style="itemStyle" v-if="tableName === 'shangjia'" prop="lianxidianhua">
          <div :style="labelStyle" :class="isRequired('lianxidianhua') ? 'required' : ''">Contact Number:</div>
          <el-input v-model="registerForm.lianxidianhua" placeholder="+61..." />
        </el-form-item>

        <el-form-item :style="itemStyle" v-if="tableName === 'shangjia'" prop="youxiang">
          <div :style="labelStyle" :class="isRequired('youxiang') ? 'required' : ''">Email:</div>
          <el-input v-model="registerForm.youxiang" placeholder="Enter email" />
        </el-form-item>

        <el-button
          :style="{
            border: '0',
            cursor: 'pointer',
            padding: '0 24px',
            margin: '0 auto',
            color: '#fff',
            display: 'block',
            outline: 'none',
            borderRadius: '4px',
            background: '#3795c4',
            width: '400px',
            fontSize: '16px',
            height: '50px',
            order: '30'
          }"
          type="primary"
          @click="submitForm('registerForm')"
        >
          Sign Up
        </el-button>

        <router-link
          :style="{
            cursor: 'pointer',
            margin: '12px auto 20px',
            color: '#3795c4',
            textAlign: 'center',
            display: 'block',
            width: '400px',
            lineHeight: '1',
            fontSize: '14px',
            textDecoration: 'none',
            order: '50'
          }"
          to="/login"
        >
          Already have an account? Log In
        </router-link>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      pageFlag: "",
      tableName: "",
      registerForm: {},
      rules: {},
      yonghuxingbieOptions: [], // ['male', 'female'] 也可以直接写死
      itemStyle: {
        width: "400px",
        padding: "0",
        margin: "0 auto 10px",
        height: "auto"
      },
      labelStyle: {
        padding: "0 10px",
        color: "#333",
        textAlign: "right",
        left: "-150px",
        background: "none",
        display: "inline-block",
        width: "150px",
        lineHeight: "36px",
        fontSize: "14px",
        position: "absolute"
      }
    };
  },

  // 先拿到 tableName，再初始化 rules 和下拉选项（created 早于 mounted）
  created() {
    this.pageFlag = this.$route.query.pageFlag;
    this.tableName = this.$route.query.role;

    if (this.pageFlag === "register") {
      if (this.tableName === "yonghu") {
        this.rules = {
          zhanghao: [{ required: true, message: "Please enter username", trigger: "blur" }],
          mima: [{ required: true, message: "Please enter password", trigger: "blur" }],
          xingming: [{ required: true, message: "Please enter full name", trigger: "blur" }],
          shouji: [
            { required: true, message: "Please enter mobile number", trigger: "blur" },
            {
              validator: (rule, value, callback) => {
                const regex = /^(\+?\d{1,3}[- ]?)?\d{8,15}$/; // supports +61 and local numbers
                if (!regex.test(value)) callback(new Error("Invalid phone number format"));
                else callback();
              },
              trigger: "blur"
            }
          ],
          // 可选：如果性别必选，可以给个 required 规则，这样 * 也会显示
          xingbie: [{ required: true, message: "Please select gender", trigger: "change" }]
        };
        this.yonghuxingbieOptions = ["male", "female"];
      } else if (this.tableName === "shangjia") {
        this.rules = {
          shangjiazhanghao: [{ required: true, message: "Please enter vendor account", trigger: "blur" }],
          mima: [{ required: true, message: "Please enter password", trigger: "blur" }],
          dianpuming: [{ required: true, message: "Please enter shop name", trigger: "blur" }],
          lianxidianhua: [
            { required: true, message: "Please enter contact number", trigger: "blur" },
            {
              validator: (rule, value, callback) => {
                const regex = /^(\+?\d{1,3}[- ]?)?\d{8,15}$/;
                if (!regex.test(value)) callback(new Error("Invalid phone number format"));
                else callback();
              },
              trigger: "blur"
            }
          ],
          youxiang: [
            { required: true, message: "Please enter email", trigger: "blur" },
            {
              validator: (rule, value, callback) => {
                const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
                if (!regex.test(value)) callback(new Error("Invalid email format"));
                else callback();
              },
              trigger: "blur"
            }
          ]
        };
      }
    }
  },

  // 再初始化具体的表单字段（与 created 解耦，保留你的原结构）
  mounted() {
    if (this.tableName === "yonghu") {
      this.registerForm = {
        zhanghao: "",
        mima: "",
        mima2: "",
        xingming: "",
        xingbie: "",
        shouji: "",
        touxiang: ""
      };
    } else if (this.tableName === "shangjia") {
      this.registerForm = {
        shangjiazhanghao: "",
        mima: "",
        mima2: "",
        dianpuming: "",
        lianxidianhua: "",
        youxiang: "",
        touxiang: ""
      };
    }
  },

  methods: {
    isRequired(name) {
      return !!this.rules[name];
    },
    yonghutouxiangUploadChange(fileUrls) {
      this.registerForm.touxiang = fileUrls.replace(new RegExp(this.$config.baseUrl, "g"), "");
    },
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (!valid) return;
        const url = this.tableName + "/register";

        if (this.registerForm.mima !== this.registerForm.mima2) {
          this.$message.error("Passwords do not match");
          return;
        }

        this.$http.post(url, this.registerForm).then(res => {
          if (res.data.code === 0) {
            this.$message({
              message: "Registration successful",
              type: "success",
              duration: 1500,
              onClose: () => this.$router.push("/login")
            });
          } else {
            this.$message.error(res.data.msg);
          }
        });
      });
    }
  }
};
</script>

<style scoped>
.container {
  position: relative;
}
.required {
  position: relative;
}
.required::after {
  color: red;
  left: 140px;
  position: absolute;
  content: "*";
  order: 2;
}
</style>
