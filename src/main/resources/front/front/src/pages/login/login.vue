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
        ref="loginForm"
        :model="loginForm"
        :style="{
          minHeight: '800px',
          padding: '20px 2vw 0 5vw',
          margin: '40px auto 40px',
          borderRadius: '0px',
          textAlign: 'center',
          background: '#fff',
          width: '40vw',
          position: 'relative',
          height: 'auto'
        }"
        :rules="rules"
      >
        <div
          :style="{
            margin: '50px auto 40px',
            color: '#2a8ec0',
            textAlign: 'center',
            width: '90%',
            letterSpacing: '2px',
            lineHeight: '44px',
            fontSize: '32px',
            fontWeight: '600'
          }"
        >
          BookShop
        </div>

        <el-form-item
          v-if="loginType === 1"
          class="list-item"
          :style="{ width: '25vw', margin: '0 auto 60px', order: '2' }"
          prop="username"
        >
          <div
            :style="{
              color: '#333',
              textAlign: 'right',
              left: '-100px',
              background: 'none',
              display: 'inline-block',
              width: '100px',
              lineHeight: '36px',
              fontSize: '14px',
              position: 'absolute'
            }"
          >
            Username:
          </div>
          <input
            :style="{
              padding: '0 10px',
              borderColor: '#c6c6c6',
              color: '#666',
              borderRadius: '8px',
              borderWidth: '2px',
              width: '100%',
              fontSize: '14px',
              borderStyle: 'solid',
              height: '50px'
            }"
            v-model="loginForm.username"
            placeholder="Enter username"
          >
        </el-form-item>

        <el-form-item
          v-if="loginType === 1"
          class="list-item"
          :style="{ width: '25vw', margin: '0 auto 60px', order: '2' }"
          prop="password"
        >
          <div
            :style="{
              color: '#333',
              textAlign: 'right',
              left: '-100px',
              background: 'none',
              display: 'inline-block',
              width: '100px',
              lineHeight: '36px',
              fontSize: '14px',
              position: 'absolute'
            }"
          >
            Password:
          </div>
          <input
            :style="{
              padding: '0 10px',
              borderColor: '#c6c6c6',
              color: '#666',
              borderRadius: '8px',
              borderWidth: '2px',
              width: '100%',
              fontSize: '14px',
              borderStyle: 'solid',
              height: '50px'
            }"
            v-model="loginForm.password"
            placeholder="Enter password"
            type="password"
          >
        </el-form-item>

        <!-- Role selector (only when there are multiple roles) -->
        <el-form-item
          class="list-type select"
          :style="{ width: '25vw', margin: '0 auto 60px', order: '2' }"
          v-if="roles.length > 1"
        >
          <el-select
            v-model="loginForm.tableName"
            placeholder="Select role"
            @change="selectChange"
          >
            <!-- Fix: Vue2 should use (item, index) and paired tags, not self-closing -->
            <el-option
              v-for="(item, index) in roles"
              :key="index"
              :label="item.roleName"
              :value="item.tableName">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item
          class="list-btn"
          :style="{ width: 'auto', margin: '0px auto', background: 'none', display: 'inline-block', order: '5' }"
        >
          <el-button
            v-if="loginType === 1"
            :style="{
              border: '0',
              cursor: 'pointer',
              padding: '0 20px',
              margin: '0 0 0px',
              color: '#fff',
              letterSpacing: '0px',
              minWidth: '100px',
              outline: 'none',
              borderRadius: '8px',
              background: '#3795c4',
              width: 'auto',
              fontSize: '18px',
              height: '50px'
            }"
            @click="submitForm('loginForm')"
          >
            Login
          </el-button>

          <el-button
            v-if="loginType === 1"
            :style="{
              border: '0',
              cursor: 'pointer',
              padding: '0 0px',
              margin: '0 5px 5px',
              outline: 'none',
              color: '#999',
              borderRadius: '4px',
              textAlign: 'right',
              background: 'none',
              width: 'auto',
              fontSize: '14px',
              height: '40px'
            }"
            @click="resetForm('loginForm')"
          >
            Reset
          </el-button>
        </el-form-item>

        <div
          :style="{
            width: 'auto',
            margin: '0',
            flexWrap: 'wrap',
            background: 'none',
            display: 'inline-block',
            order: '6'
          }"
        >
          <!-- Register links (visible only when the role supports front registration) -->
          <router-link
            :style="{
              cursor: 'pointer',
              border: '1px solid #ffffff50',
              padding: '0px',
              margin: '0 5px 5px',
              color: '#999',
              borderRadius: '4px',
              background: 'none',
              width: 'auto',
              fontSize: '14px',
              lineHeight: '50px'
            }"
            :to="{ path: '/register', query: { role: item.tableName, pageFlag: 'register' } }"
            v-if="item.hasFrontRegister === '是'"
            v-for="(item, index) in roles"
            :key="index"
          >
            <!-- If roleName is Chinese, the visible 'Register' is English; the role name is whatever menu config provides -->
            Register 
          </router-link>
        </div>

        <div class="idea1" :style="{ width: '100%', background: 'red', display: 'none', height: '40px' }"></div>
        <div class="idea2" :style="{ width: '100%', background: 'blue', display: 'none', height: '40px' }"></div>
      </el-form>
    </div>
  </div>
</template>

<script>
import menu from '@/config/menu'

export default {
  data() {
    return {
      baseUrl: this.$config.baseUrl,
      loginType: 1,
      roleMenus: [],
      loginForm: {
        username: '',
        password: '',
        tableName: '',
        code: ''
      },
      role: '',
      roles: [],
      rules: {
        username: [{ required: true, message: 'Please enter username', trigger: 'blur' }],
        password: [{ required: true, message: 'Please enter password', trigger: 'blur' }]
      },
      // simple captcha style config (unused visually here but kept)
      codes: [
        { num: 1, color: '#000', rotate: '10deg', size: '16px' },
        { num: 2, color: '#000', rotate: '10deg', size: '16px' },
        { num: 3, color: '#000', rotate: '10deg', size: '16px' },
        { num: 4, color: '#000', rotate: '10deg', size: '16px' }
      ],
      flag: false,
      verifyCheck2: false
    }
  },

  created() {
    // Build role list from menu config (only roles that allow front login)
    this.roleMenus = menu.list()
    for (let item in this.roleMenus) {
      if (this.roleMenus[item].hasFrontLogin === '是') {
        this.roles.push(this.roleMenus[item])
      }
    }
    // If only one role, preselect it
    if (this.roles.length === 1) {
      this.role = this.roles[0].roleName
      this.loginForm.tableName = this.roles[0].tableName
    }
  },

  methods: {
    randomString() {
      // Generates a random 4-char captcha config (kept from original)
      const len = 4
      const chars =
        'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789'.split('')
      const colors = '0123456789abcdef'.split('')
      const sizes = ['14', '15', '16', '17', '18']

      for (let i = 0; i < len; i++) {
        const key = Math.floor(Math.random() * chars.length)
        this.codes[i].num = chars[key]

        let code = '#'
        for (let j = 0; j < 6; j++) {
          const k = Math.floor(Math.random() * colors.length)
          code += colors[k]
        }
        this.codes[i].color = code

        let rotate = Math.floor(Math.random() * 45)
        if (Math.floor(Math.random() * 2) === 1) rotate = '-' + rotate
        this.codes[i].rotate = 'rotate(' + rotate + 'deg)'

        const sz = Math.floor(Math.random() * sizes.length)
        this.codes[i].size = sizes[sz] + 'px'
      }
    },

    selectChange(value) {
      // update readable role name for storage/UX
      for (let x in this.roles) {
        if (this.roles[x].tableName === value) {
          this.role = this.roles[x].roleName
        }
      }
    },

    submitForm(formName) {
      if (this.roles.length !== 1) {
        if (!this.role || !this.loginForm.tableName) {
          this.$message.error('Please select a user role')
          return false
        }
      }
      this.loginPost(formName)
    },

    resetForm(formName) {
      this.$refs[formName].resetFields()
      // keep selected role if only one
      if (this.roles.length === 1) {
        this.role = this.roles[0].roleName
        this.loginForm.tableName = this.roles[0].tableName
      }
    },

    loginPost(formName) {
      this.$refs[formName].validate(valid => {
        if (!valid) return
        this.$http
          .get(`${this.loginForm.tableName}/login`, { params: this.loginForm })
          .then(res => {
            if (res.data.code === 0) {
              localStorage.setItem('frontToken', res.data.token)
              localStorage.setItem('UserTableName', this.loginForm.tableName)
              localStorage.setItem('username', this.loginForm.username)
              localStorage.setItem('adminName', this.loginForm.username)
              localStorage.setItem('frontSessionTable', this.loginForm.tableName)
              localStorage.setItem('frontRole', this.role)
              localStorage.setItem('keyPath', 0)
              this.$router.push('/')
              this.$message({
                message: 'Login successful',
                type: 'success',
                duration: 1500
              })
            } else {
              this.$message.error(res.data.msg)
            }
          })
      })
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.container {
  position: relative;
  background: url(http://codegen.caihongy.cn/20240305/c46698d0e9e14ad1b5729fb8a33a6d90.jpg);

  .el-form-item {
    & /deep/ .el-form-item__content {
      width: 100%;
    }
  }

  .list-item /deep/ .el-form-item__content {
    display: flex;
    width: 100%;
  }

  .list-code /deep/ .el-form-item__content {
    display: flex;
    width: 100%;
    justify-content: space-between;
  }

  .list-type /deep/ .el-form-item__content {
    padding: 0px;
    margin: 0;
    display: flex;
    width: 25vw;
  }

  .list-btn /deep/ .el-form-item__content {
    display: inline-block;
    justify-content: center;
    flex-wrap: wrap;
  }

  .list-item /deep/ .el-input .el-input__inner {
    border-radius: 8px;
    padding: 0 10px;
    color: #666;
    width: 100%;
    font-size: 14px;
    border-color: #c6c6c6;
    border-width: 2px;
    border-style: solid;
    height: 50px;
  }

  .list-code /deep/ .el-input .el-input__inner {
    border-radius: 8px;
    padding: 0 10px;
    outline: none;
    color: #666;
    display: inline-block;
    vertical-align: middle;
    width: calc(100% - 110px);
    font-size: 14px;
    border-color: #c6c6c6;
    border-width: 2px;
    border-style: solid;
    height: 50px;
  }

  /* select */
  .list-type.select .el-select /deep/ .el-input__inner {
    border-radius: 8px;
    padding: 0 10px;
    color: #666;
    width: 25vw;
    font-size: 14px;
    border-color: #c6c6c6;
    border-width: 2px;
    border-style: solid;
    height: 50px;
  }
}
</style>
