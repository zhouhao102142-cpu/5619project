<template>
  <div
    class="center-preview"
    :style='{"padding":"20px 12% 40px","margin":"0 auto","color":"#999","flexWrap":"wrap","background":"#edeff1","display":"block","width":"100%","fontSize":"14px","position":"relative","justifyContent":"space-between"}'
  >
    <div
      class="title"
      :style='{"padding":"12px 0 0","margin":"0px auto","color":"#333","textAlign":"center","background":"none","width":"100%","fontSize":"24px","fontWeight":"600","height":"60px"}'
    >{{ title }}</div>

    <!-- Profile card -->
    <div
      class="info"
      :style='{"padding":"80px 60px 80px 200px","margin":"20px 0 0 0","borderColor":"#28890b20","alignItems":"center","display":"flex","borderRadius":"0px","flexWrap":"wrap","background":"#fff","borderWidth":"0px","width":"calc(100% - 0px)","position":"relative","borderStyle":"solid","height":"auto","order":"2"}'
    >
      <div
        :style='{"padding":"8px 30px","borderColor":"#eee","margin":"0 20px 0 0","color":"#333","letterSpacing":"0px","borderRadius":"0px","top":"20px","left":"170px","borderWidth":"0px","background":"none","width":"auto","lineHeight":"1.5","fontSize":"18px","position":"absolute","borderStyle":"solid","fontWeight":"600","height":"auto"}'
      >Profile</div>

      <!-- Avatar (user) -->
      <div
        v-if="userTableName=='yonghu'"
        :style='{"borderColor":"#1abc9e20","left":"20px","borderWidth":"0 0 0px 0","width":"auto","fontSize":0,"position":"absolute","borderStyle":"solid","height":"auto"}'
      >
        <el-image
          :style='{"margin":"10px auto","borderColor":"#efefef","objectFit":"cover","borderRadius":"100%","borderWidth":"0 0 1px 0","display":"block","width":"150px","borderStyle":"solid","height":"150px"}'
          :src="sessionForm.touxiang?baseUrl + sessionForm.touxiang:require('@/assets/avator.png')"
          fit="cover"
        />
      </div>

      <!-- Avatar (shop) -->
      <div
        v-if="userTableName=='shangjia'"
        :style='{"borderColor":"#1abc9e20","left":"20px","borderWidth":"0 0 0px 0","width":"auto","fontSize":0,"position":"absolute","borderStyle":"solid","height":"auto"}'
      >
        <el-image
          :style='{"margin":"10px auto","borderColor":"#efefef","objectFit":"cover","borderRadius":"100%","borderWidth":"0 0 1px 0","display":"block","width":"150px","borderStyle":"solid","height":"150px"}'
          :src="sessionForm.touxiang?baseUrl + sessionForm.touxiang:require('@/assets/avator.png')"
          fit="cover"
        />
      </div>

      <!-- User quick fields -->
      <div
        v-if="userTableName=='yonghu'"
        :style='{"padding":"0 0px","borderColor":"#1abc9e20","margin":"0 5% 0 0","borderWidth":"0 0 0px 0","display":"block","width":"auto","lineHeight":"1.5","borderStyle":"solid","height":"auto"}'
      >
        <div :style='{"width":"100%","margin":"0 10px 0 0","fontSize":"inherit","color":"inherit"}'>Account</div>
        <div :style='{"fontSize":"inherit","color":"inherit","textAlign":"left","flex":1}'>{{sessionForm.zhanghao}}</div>
      </div>

      <div
        v-if="userTableName=='yonghu'"
        :style='{"padding":"0 0px","borderColor":"#1abc9e20","margin":"0 5% 0 0","borderWidth":"0 0 0px 0","display":"block","width":"auto","lineHeight":"1.5","borderStyle":"solid","height":"auto"}'
      >
        <div :style='{"color":"inherit","margin":"0 10px 0 0","fontSize":"inherit"}'>Name</div>
        <div :style='{"fontSize":"inherit","color":"inherit","textAlign":"left","flex":1}'>{{sessionForm.xingming}}</div>
      </div>

      <div
        v-if="userTableName=='yonghu'"
        :style='{"padding":"0 0px","borderColor":"#1abc9e20","margin":"0 5% 0 0","borderWidth":"0 0 0px 0","display":"block","width":"auto","lineHeight":"1.5","borderStyle":"solid","height":"auto"}'
      >
        <div :style='{"color":"inherit","margin":"0 10px 0 0","fontSize":"inherit"}'>Gender</div>
        <div :style='{"fontSize":"inherit","color":"inherit","textAlign":"left","flex":1}'>{{sessionForm.xingbie}}</div>
      </div>

      <div
        v-if="userTableName=='yonghu'"
        :style='{"padding":"0 0px","borderColor":"#1abc9e20","margin":"0 5% 0 0","borderWidth":"0 0 0px 0","display":"block","width":"auto","lineHeight":"1.5","borderStyle":"solid","height":"auto"}'
      >
        <div :style='{"color":"inherit","margin":"0 10px 0 0","fontSize":"inherit"}'>Phone</div>
        <div :style='{"fontSize":"inherit","color":"inherit","textAlign":"left","flex":1}'>{{sessionForm.shouji}}</div>
      </div>

      <div
        v-if="userTableName=='yonghu'"
        :style='{"padding":"0 0px","borderColor":"#1abc9e20","margin":"0 5% 0 0","borderWidth":"0 0 0px 0","display":"block","width":"auto","lineHeight":"1.5","borderStyle":"solid","height":"auto"}'
      >
        <div :style='{"color":"inherit","margin":"0 10px 0 0","fontSize":"inherit"}'>Points</div>
        <div :style='{"fontSize":"inherit","color":"inherit","textAlign":"left","flex":1}'>{{sessionForm.jf}}</div>
      </div>

      <div
        v-if="userTableName=='yonghu'"
        :style='{"padding":"0 0px","borderColor":"#1abc9e20","margin":"0","borderWidth":"0 0 0px 0","display":"block","width":"auto","lineHeight":"1.5","borderStyle":"solid","height":"auto"}'
      >
        <div :style='{"color":"inherit","margin":"0 10px 0 0","fontSize":"inherit"}'>Balance</div>
        <div :style='{"fontSize":"inherit","color":"inherit","textAlign":"left","flex":1}'>{{sessionForm.money}}</div>
      </div>

      <!-- Shop quick fields -->
      <div
        v-if="userTableName=='shangjia'"
        :style='{"padding":"0 0px","borderColor":"#1abc9e20","margin":"0 5% 0 0","borderWidth":"0 0 0px 0","display":"block","width":"auto","lineHeight":"1.5","borderStyle":"solid","height":"auto"}'
      >
        <div :style='{"width":"100%","margin":"0 10px 0 0","fontSize":"inherit","color":"inherit"}'>Shop Account</div>
        <div :style='{"fontSize":"inherit","color":"inherit","textAlign":"left","flex":1}'>{{sessionForm.shangjiazhanghao}}</div>
      </div>

      <div
        v-if="userTableName=='shangjia'"
        :style='{"padding":"0 0px","borderColor":"#1abc9e20","margin":"0 5% 0 0","borderWidth":"0 0 0px 0","display":"block","width":"auto","lineHeight":"1.5","borderStyle":"solid","height":"auto"}'
      >
        <div :style='{"color":"inherit","margin":"0 10px 0 0","fontSize":"inherit"}'>Shop Name</div>
        <div :style='{"fontSize":"inherit","color":"inherit","textAlign":"left","flex":1}'>{{sessionForm.dianpuming}}</div>
      </div>

      <div
        v-if="userTableName=='shangjia'"
        :style='{"padding":"0 0px","borderColor":"#1abc9e20","margin":"0 5% 0 0","borderWidth":"0 0 0px 0","display":"block","width":"auto","lineHeight":"1.5","borderStyle":"solid","height":"auto"}'
      >
        <div :style='{"color":"inherit","margin":"0 10px 0 0","fontSize":"inherit"}'>Shop Address</div>
        <div :style='{"fontSize":"inherit","color":"inherit","textAlign":"left","flex":1}'>{{sessionForm.dianpudizhi}}</div>
      </div>

      <div
        v-if="userTableName=='shangjia'"
        :style='{"padding":"0 0px","borderColor":"#1abc9e20","margin":"0 5% 0 0","borderWidth":"0 0 0px 0","display":"block","width":"auto","lineHeight":"1.5","borderStyle":"solid","height":"auto"}'
      >
        <div :style='{"color":"inherit","margin":"0 10px 0 0","fontSize":"inherit"}'>Manager</div>
        <div :style='{"fontSize":"inherit","color":"inherit","textAlign":"left","flex":1}'>{{sessionForm.fuzeren}}</div>
      </div>

      <div
        v-if="userTableName=='shangjia'"
        :style='{"padding":"0 0px","borderColor":"#1abc9e20","margin":"0 5% 0 0","borderWidth":"0 0 0px 0","display":"block","width":"auto","lineHeight":"1.5","borderStyle":"solid","height":"auto"}'
      >
        <div :style='{"color":"inherit","margin":"0 10px 0 0","fontSize":"inherit"}'>Phone</div>
        <div :style='{"fontSize":"inherit","color":"inherit","textAlign":"left","flex":1}'>{{sessionForm.lianxidianhua}}</div>
      </div>

      <div
        v-if="userTableName=='shangjia'"
        :style='{"padding":"0 0px","borderColor":"#1abc9e20","margin":"0","borderWidth":"0 0 0px 0","display":"block","width":"auto","lineHeight":"1.5","borderStyle":"solid","height":"auto"}'
      >
        <div :style='{"color":"inherit","margin":"0 10px 0 0","fontSize":"inherit"}'>Email</div>
        <div :style='{"fontSize":"inherit","color":"inherit","textAlign":"left","flex":1}'>{{sessionForm.youxiang}}</div>
      </div>
    </div>

    <!-- Tabs -->
    <el-tabs
      tab-position="left"
      :style='{"width":"100%","margin":"20px 0 0","alignItems":"flex-start","flexWrap":"wrap","background":"none","display":"flex"}'
      @tab-click="handleClick"
    >
      <!-- Profile Center -->
      <el-tab-pane label="Profile Center">
        <el-form
          class="center-preview-pv"
          ref="sessionForm"
          :model="sessionForm"
          :rules="rules"
          label-width="100px"
        >
          <!-- User fields -->
          <el-form-item
            v-if="userTableName=='yonghu'"
            label="Account"
            prop="zhanghao"
            :style='{"width":"100%","padding":"0px","margin":"0 0 20px","background":"none","display":"inline-block"}'
          >
            <el-input v-model="sessionForm.zhanghao" placeholder="Account" readonly />
          </el-form-item>

          <el-form-item
            v-if="userTableName=='yonghu'"
            label="Name"
            prop="xingming"
            :style='{"width":"100%","padding":"0px","margin":"0 0 20px","background":"none","display":"inline-block"}'
          >
            <el-input v-model="sessionForm.xingming" placeholder="Name" />
          </el-form-item>

          <el-form-item
            v-if="userTableName=='yonghu'"
            label="Gender"
            prop="xingbie"
            :style='{"width":"100%","padding":"0px","margin":"0 0 20px","background":"none","display":"inline-block"}'
          >
            <el-select v-model="sessionForm.xingbie" placeholder="Select gender">
              <el-option
                v-for="(item, index) in dynamicProp.xingbie"
                :key="index"
                :label="item"
                :value="item"
              />
            </el-select>
          </el-form-item>

          <el-form-item
            v-if="userTableName=='yonghu'"
            label="Phone"
            prop="shouji"
            :style='{"width":"100%","padding":"0px","margin":"0 0 20px","background":"none","display":"inline-block"}'
          >
            <el-input v-model="sessionForm.shouji" placeholder="Phone" />
          </el-form-item>

          <el-form-item
            v-if="userTableName=='yonghu'"
            label="Avatar"
            prop="touxiang"
            :style='{"width":"100%","padding":"0px","margin":"0 0 20px","background":"none","display":"inline-block"}'
          >
            <file-upload
              tip="Click to upload avatar"
              action="file/upload"
              :limit="1"
              :multiple="true"
              :fileUrls="sessionForm.touxiang?sessionForm.touxiang:''"
              @change="yonghutouxiangHandleAvatarSuccess"
            />
          </el-form-item>

          <el-form-item
            v-if="userTableName=='yonghu'"
            label="Points"
            :style='{"width":"100%","padding":"0px","margin":"0 0 20px","background":"none","display":"inline-block"}'
          >
            <el-input v-model="sessionForm.jf" placeholder="Points" readonly />
          </el-form-item>

          <el-form-item
            v-if="userTableName=='yonghu'"
            label="Balance"
            class="balance"
            :style='{"width":"100%","padding":"0px","margin":"0 0 20px","background":"none","display":"inline-block"}'
          >
            <div :style='{"flexWrap":"wrap","display":"flex"}'>
              <el-input v-model="sessionForm.money" placeholder="Balance" readonly />
              <div
                @click="dialogFormVisibleMoney = true"
                :style='{"cursor":"pointer","padding":"0 15px","margin":"0 20px 0 10px","borderColor":"#eee","color":"#fff","display":"inline-block","outline":"none","borderRadius":"4px","background":"#3795c4","borderWidth":"0px","width":"auto","lineHeight":"40px","fontSize":"14px","borderStyle":"solid","height":"40px"}'
              >Top up</div>
            </div>
          </el-form-item>

          <!-- Shop fields -->
          <el-form-item
            v-if="userTableName=='shangjia'"
            label="Shop Account"
            prop="shangjiazhanghao"
            :style='{"width":"100%","padding":"0px","margin":"0 0 20px","background":"none","display":"inline-block"}'
          >
            <el-input v-model="sessionForm.shangjiazhanghao" placeholder="Shop Account" readonly />
          </el-form-item>

          <el-form-item
            v-if="userTableName=='shangjia'"
            label="Shop Name"
            prop="dianpuming"
            :style='{"width":"100%","padding":"0px","margin":"0 0 20px","background":"none","display":"inline-block"}'
          >
            <el-input v-model="sessionForm.dianpuming" placeholder="Shop Name" />
          </el-form-item>

          <el-form-item
            v-if="userTableName=='shangjia'"
            label="Shop Address"
            prop="dianpudizhi"
            :style='{"width":"100%","padding":"0px","margin":"0 0 20px","background":"none","display":"inline-block"}'
          >
            <el-input v-model="sessionForm.dianpudizhi" placeholder="Shop Address" />
          </el-form-item>

          <el-form-item
            v-if="userTableName=='shangjia'"
            label="Manager"
            prop="fuzeren"
            :style='{"width":"100%","padding":"0px","margin":"0 0 20px","background":"none","display":"inline-block"}'
          >
            <el-input v-model="sessionForm.fuzeren" placeholder="Manager" />
          </el-form-item>

          <el-form-item
            v-if="userTableName=='shangjia'"
            label="Phone"
            prop="lianxidianhua"
            :style='{"width":"100%","padding":"0px","margin":"0 0 20px","background":"none","display":"inline-block"}'
          >
            <el-input v-model="sessionForm.lianxidianhua" placeholder="Phone" />
          </el-form-item>

          <el-form-item
            v-if="userTableName=='shangjia'"
            label="Email"
            prop="youxiang"
            :style='{"width":"100%","padding":"0px","margin":"0 0 20px","background":"none","display":"inline-block"}'
          >
            <el-input v-model="sessionForm.youxiang" placeholder="Email" />
          </el-form-item>

          <el-form-item
            v-if="userTableName=='shangjia'"
            label="Cover"
            prop="touxiang"
            :style='{"width":"100%","padding":"0px","margin":"0 0 20px","background":"none","display":"inline-block"}'
          >
            <file-upload
              tip="Click to upload cover"
              action="file/upload"
              :limit="1"
              :multiple="true"
              :fileUrls="sessionForm.touxiang?sessionForm.touxiang:''"
              @change="shangjiatouxiangHandleAvatarSuccess"
            />
          </el-form-item>

          <el-form-item
            v-if="userTableName=='shangjia'"
            label="Business License"
            prop="yingyezhizhao"
            :style='{"width":"100%","padding":"0px","margin":"0 0 20px","background":"none","display":"inline-block"}'
          >
            <file-upload
              tip="Click to upload business license"
              action="file/upload"
              :limit="1"
              :type="3"
              :multiple="true"
              :fileUrls="sessionForm.yingyezhizhao?sessionForm.yingyezhizhao:''"
              @change="shangjiayingyezhizhaoHandleAvatarSuccess"
            />
          </el-form-item>

          <el-form-item
            v-if="userTableName=='shangjia'"
            label="Points"
            :style='{"width":"100%","padding":"0px","margin":"0 0 20px","background":"none","display":"inline-block"}'
          >
            <el-input v-model="sessionForm.jf" placeholder="Points" readonly />
          </el-form-item>

          <el-form-item
            v-if="userTableName=='shangjia'"
            label="Balance"
            class="balance"
            :style='{"width":"100%","padding":"0px","margin":"0 0 20px","background":"none","display":"inline-block"}'
          >
            <div :style='{"flexWrap":"wrap","display":"flex"}'>
              <el-input v-model="sessionForm.money" placeholder="Balance" readonly />
              <div
                @click="dialogFormVisibleMoney = true"
                :style='{"cursor":"pointer","padding":"0 15px","margin":"0 20px 0 10px","borderColor":"#eee","color":"#fff","display":"inline-block","outline":"none","borderRadius":"4px","background":"#3795c4","borderWidth":"0px","width":"auto","lineHeight":"40px","fontSize":"14px","borderStyle":"solid","height":"40px"}'
              >Top up</div>
            </div>
          </el-form-item>

          <el-form-item :style='{"padding":"0","margin":"0"}'>
            <el-button
              type="primary"
              @click="onSubmit('sessionForm')"
              :style='{"border":"0","cursor":"pointer","padding":"0","margin":"0 20px 0 0","outline":"none","color":"rgba(255, 255, 255, 1)","borderRadius":"30px","background":"#3795c4","width":"120px","lineHeight":"50px","fontSize":"14px","height":"50px"}'
            >Update</el-button>
            <el-button
              type="danger"
              @click="logout"
              :style='{"border":"1px solid #3795c4","cursor":"pointer","padding":"0","margin":"0","outline":"none","color":"#3795c4","borderRadius":"30px","background":"#fff","width":"110px","lineHeight":"50px","fontSize":"14px","height":"50px"}'
            >Log out</el-button>
          </el-form-item>
        </el-form>
      </el-tab-pane>

      <!-- Change Password -->
      <el-tab-pane label="Change Password">
        <el-form
          class="center-preview-pv"
          ref="passwordForm"
          :model="passwordForm"
          :rules="passwordRules"
          label-width="100px"
        >
          <el-form-item
            label="Current Password"
            prop="password"
            :style='{"width":"100%","padding":"0px","margin":"0 0 20px","background":"none","display":"inline-block"}'
          >
            <el-input type="password" v-model="passwordForm.password" placeholder="Current Password" />
          </el-form-item>

          <el-form-item
            label="New Password"
            prop="newpassword"
            :style='{"width":"100%","padding":"0px","margin":"0 0 20px","background":"none","display":"inline-block"}'
          >
            <el-input type="password" v-model="passwordForm.newpassword" placeholder="New Password" />
          </el-form-item>

          <el-form-item
            label="Confirm Password"
            prop="repassword"
            :style='{"width":"100%","padding":"0px","margin":"0 0 20px","background":"none","display":"inline-block"}'
          >
            <el-input type="password" v-model="passwordForm.repassword" placeholder="Confirm Password" />
          </el-form-item>

          <el-form-item :style='{"padding":"0","margin":"0"}'>
            <el-button
              type="primary"
              @click="updatePassword"
              :style='{"border":"0","cursor":"pointer","padding":"0","margin":"0 20px 0 0","outline":"none","color":"rgba(255, 255, 255, 1)","borderRadius":"30px","background":"#3795c4","width":"120px","lineHeight":"50px","fontSize":"14px","height":"50px"}'
            >Change Password</el-button>
          </el-form-item>
        </el-form>
      </el-tab-pane>

      <!-- Back menus -->
      <el-tab-pane
        v-for="(item,index) in menuList"
        :key="index"
        v-if="hasBack(item.menu)"
        :label="item.child[0].menu"
        :name="item.child[0].tableName"
      />

      <!-- Removed: My Posts -->
      <el-tab-pane label="My Orders" />
      <el-tab-pane label="My Address" name="MyAddress">
        <router-view></router-view>
      </el-tab-pane>
      <el-tab-pane label="My Favorites" />
    </el-tabs>

    <!-- Top-up dialog -->
    <el-dialog title="Top Up" :visible.sync="dialogFormVisibleMoney" width="726px" center>
      <el-form :model="chongzhiForm">
        <el-form-item label="Amount" label-width="120px">
          <el-input type="number" v-model="chongzhiForm.money" autocomplete="off" placeholder="Amount" />
        </el-form-item>
        <el-form-item label-width="120px">
          <el-radio-group v-model="chongzhiForm.radio">
            <el-radio style="margin-bottom: 30px" label="WeChat Pay">
              <el-image
                style="width: 60px; height: 60px;vertical-align: middle;"
                :src="require('@/assets/weixin.png')"
                fit="fill"
              />
              <span style="display: inline-block;margin-left: 10px">WeChat Pay</span>
            </el-radio>
            <el-radio label="Alipay">
              <el-image
                style="width: 60px; height: 60px;vertical-align: middle;"
                :src="require('@/assets/zhifubao.png')"
                fit="fill"
              />
              <span style="display: inline-block;margin-left: 10px">Alipay</span>
            </el-radio>
            <el-radio label="CCB">
              <el-image
                style="width: 120px; height: 60px;vertical-align: middle;"
                :src="require('@/assets/jianshe.png')"
                fit="fill"
              />
            </el-radio>
            <el-radio label="ABC">
              <el-image
                style="width: 126px; height: 60px;vertical-align: middle;"
                :src="require('@/assets/nongye.png')"
                fit="fill"
              />
            </el-radio>
            <el-radio label="BOC">
              <el-image
                style="width: 140px; height: 60px;vertical-align: middle;"
                :src="require('@/assets/zhongguo.png')"
                fit="fill"
              />
            </el-radio>
            <el-radio label="BCM">
              <el-image
                style="width: 120px; height: 60px;vertical-align: middle;"
                :src="require('@/assets/jiaotong.png')"
                fit="fill"
              />
            </el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisibleMoney = false">Cancel</el-button>
        <el-button type="primary" @click="chongzhi">Confirm</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import config from '@/config/config'
import menu from '@/config/menu'
import Vue from 'vue'

export default {
  data() {
    return {
      title: 'Profile Center',
      baseUrl: config.baseUrl,
      sessionForm: {},
      passwordForm: {},
      passwordRules: {
        password: [{ required: true, message: 'Required', trigger: 'blur' }],
        newpassword: [{ required: true, message: 'Required', trigger: 'blur' }],
        repassword: [{ required: true, message: 'Required', trigger: 'blur' }]
      },
      rules: {},
      chongzhiForm: {
        money: '',
        radio: ''
      },
      menuList: [],
      disabled: false,
      dialogFormVisibleMoney: false,
      uploadUrl: config.baseUrl + 'file/upload',
      imageUrl: '',
      headers: { Token: localStorage.getItem('frontToken') },
      userTableName: localStorage.getItem('UserTableName'),
      dynamicProp: {}
    }
  },
  created() {
    const menus = menu.list()
    for (let x in menus) {
      if (menus[x].tableName == this.userTableName) {
        this.menuList = menus[x].backMenu
      }
    }

    if ('yonghu' == this.userTableName) {
      this.$set(this.sessionForm, 'zhanghao', null)
      this.$set(this.sessionForm, 'mima', null)
      this.$set(this.sessionForm, 'xingming', null)
      this.$set(this.sessionForm, 'xingbie', null)
      this.$set(this.sessionForm, 'shouji', null)
      this.$set(this.sessionForm, 'touxiang', null)
      this.$set(this.sessionForm, 'jf', null)
      this.$set(this.sessionForm, 'money', null)
    }
    if ('shangjia' == this.userTableName) {
      this.$set(this.sessionForm, 'shangjiazhanghao', null)
      this.$set(this.sessionForm, 'mima', null)
      this.$set(this.sessionForm, 'dianpuming', null)
      this.$set(this.sessionForm, 'dianpudizhi', null)
      this.$set(this.sessionForm, 'fuzeren', null)
      this.$set(this.sessionForm, 'lianxidianhua', null)
      this.$set(this.sessionForm, 'youxiang', null)
      this.$set(this.sessionForm, 'touxiang', null)
      this.$set(this.sessionForm, 'yingyezhizhao', null)
      this.$set(this.sessionForm, 'jf', null)
      this.$set(this.sessionForm, 'money', null)
    }

    if ('yonghu' == this.userTableName) {
      this.$set(this.rules, 'zhanghao', [{ required: true, message: 'Please enter account', trigger: 'blur' }])
      this.$set(this.rules, 'mima', [{ required: true, message: 'Please enter password', trigger: 'blur' }])
      this.$set(this.rules, 'xingming', [{ required: true, message: 'Please enter name', trigger: 'blur' }])
      this.$set(this.rules, 'shouji', [{ required: false, validator: this.$validate.isMobile, trigger: 'blur' }])
      this.$set(this.rules, 'jf', [{ required: false, validator: this.$validate.isNumber, trigger: 'blur' }])
      this.$set(this.rules, 'money', [{ required: false, validator: this.$validate.isNumber, trigger: 'blur' }])
    }
    if ('shangjia' == this.userTableName) {
      this.$set(this.rules, 'shangjiazhanghao', [{ required: true, message: 'Please enter shop account', trigger: 'blur' }])
      this.$set(this.rules, 'mima', [{ required: true, message: 'Please enter password', trigger: 'blur' }])
      this.$set(this.rules, 'dianpuming', [{ required: true, message: 'Please enter shop name', trigger: 'blur' }])
      this.$set(this.rules, 'lianxidianhua', [{ required: false, validator: this.$validate.isMobile, trigger: 'blur' }])
      this.$set(this.rules, 'youxiang', [{ required: false, validator: this.$validate.isEmail, trigger: 'blur' }])
      this.$set(this.rules, 'jf', [{ required: false, validator: this.$validate.isNumber, trigger: 'blur' }])
      this.$set(this.rules, 'money', [{ required: false, validator: this.$validate.isNumber, trigger: 'blur' }])
    }

    this.init()
    this.sessionForm = JSON.parse(localStorage.getItem('sessionForm'))
  },
  methods: {
    init() {
      if ('yonghu' == this.userTableName) {
        this.dynamicProp.xingbie = '男,女'.split(',')
      }
    },
    setSession() {
      localStorage.setItem('sessionForm', JSON.stringify(this.sessionForm))
    },
    onSubmit(formName) {
      if (`yonghu` == this.userTableName && this.sessionForm.touxiang != null) {
        this.sessionForm.touxiang = this.sessionForm.touxiang.replace(new RegExp(this.$config.baseUrl, 'g'), '')
      }
      if (`shangjia` == this.userTableName && this.sessionForm.touxiang != null) {
        this.sessionForm.touxiang = this.sessionForm.touxiang.replace(new RegExp(this.$config.baseUrl, 'g'), '')
      }
      if (`shangjia` == this.userTableName && this.sessionForm.yingyezhizhao != null) {
        this.sessionForm.yingyezhizhao = this.sessionForm.yingyezhizhao.replace(new RegExp(this.$config.baseUrl, 'g'), '')
      }

      this.$refs[formName].validate(valid => {
        if (valid) {
          this.$http.post(this.userTableName + '/update', this.sessionForm).then(res => {
            if (res.data.code == 0) {
              this.setSession()
              this.$message({
                message: 'Updated',
                type: 'success',
                duration: 1500
              })
            }
          })
        }
      })
    },
    yonghutouxiangHandleAvatarSuccess(fileUrls) {
      this.sessionForm.touxiang = fileUrls
    },
    shangjiatouxiangHandleAvatarSuccess(fileUrls) {
      this.sessionForm.touxiang = fileUrls
    },
    shangjiayingyezhizhaoHandleAvatarSuccess(fileUrls) {
      this.sessionForm.yingyezhizhao = fileUrls
    },
    chongzhi() {
      if (this.chongzhiForm.money === '') {
        this.$message({ message: 'Please enter the amount', type: 'error', duration: 1500 })
        return
      }
      if (this.chongzhiForm.money <= 0) {
        this.$message({ message: 'Please enter a valid amount', type: 'error', duration: 1500 })
        return
      }
      if (this.chongzhiForm.radio === '') {
        this.$message({ message: 'Please select a payment method', type: 'error', duration: 1500 })
        return
      }
      if (!this.sessionForm.money) {
        this.sessionForm.money = parseFloat(this.chongzhiForm.money)
      } else {
        this.sessionForm.money = parseFloat(this.sessionForm.money) + parseFloat(this.chongzhiForm.money)
      }

      this.$http.post(this.userTableName + '/update', this.sessionForm).then(res => {
        if (res.data.code == 0) {
          this.setSession()
          this.$message({
            message: 'Top up succeeded',
            type: 'success',
            duration: 1500,
            onClose: () => {
              this.dialogFormVisibleMoney = false
            }
          })
        }
      })
    },
    handleClick(tab, event) {
      switch (event.target.outerText) {
        case 'Profile Center':
          tab.$router.push('/index/center')
          break
        case 'Change Password':
          this.passwordForm = { password: '', newpassword: '', repassword: '' }
          this.$forceUpdate()
          break
        // Removed: 'My Posts'
        case 'My Orders':
          tab.$router.push('/index/shop-order/order')
          break
        case 'My Address':
          tab.$router.push('/index/shop-address/list')
          break
        case 'My Favorites':
          localStorage.setItem('storeupType', 1)
          tab.$router.push('/index/storeup')
          break
        default:
          tab.$router.push(`/index/${tab.name}?centerType=1`)
      }
      this.title = event.target.outerText
    },
    async updatePassword() {
      this.$refs['passwordForm'].validate(async valid => {
        if (valid) {
          let password = ''
          if (this.sessionForm.mima) password = this.sessionForm.mima
          else if (this.sessionForm.password) password = this.sessionForm.password

          if (this.passwordForm.password != password) {
            this.$message.error('Current password is incorrect')
            return
          }
          if (this.passwordForm.newpassword != this.passwordForm.repassword) {
            this.$message.error('Passwords do not match')
            return
          }
          if (this.passwordForm.newpassword == this.passwordForm.password) {
            this.$message.error('New password equals current password')
            return
          }

          this.sessionForm.password = this.passwordForm.newpassword
          this.sessionForm.mima = this.passwordForm.newpassword

          this.$http.post(`${this.userTableName}/update`, this.sessionForm).then(({ data }) => {
            if (data && data.code === 0) {
              this.$message({
                message: 'Password updated. Takes effect on next login.',
                type: 'success',
                duration: 1500
              })
              this.setSession()
            } else {
              this.$message.error(data.msg)
            }
          })
        }
      })
    },
    logout() {
      localStorage.clear()
      Vue.http && (Vue.http.headers.common['Token'] = '')
      this.$router.push('/index/home')
      this.activeIndex = '0'
      localStorage.setItem('keyPath', this.activeIndex)
      this.$forceUpdate()
      this.$message({ message: 'Logged out', type: 'success', duration: 1500 })
    },
    hasBack(name) {
      switch (name) {
        case '订单管理':
          return false
        case '我的收藏管理':
          return false
        default:
          return true
      }
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.center-preview {
  .el-tabs {
    & /deep/ .el-tabs__header {
      .el-tabs__nav {
        overflow: auto;
      }
      ::-webkit-scrollbar {
        -webkit-appearance: none;
        width: 6px;
        height: 6px;
      }
      ::-webkit-scrollbar-track {
        background: rgba(0, 0, 0, 0.1);
        border-radius: 0;
      }
      ::-webkit-scrollbar-thumb {
        cursor: pointer;
        border-radius: 5px;
        background: rgba(0, 0, 0, 0.15);
        transition: color 0.2s ease;
      }
      ::-webkit-scrollbar-thumb:hover {
        background: rgba(0, 0, 0, 0.3);
      }
      .el-tabs__nav-wrap {
        margin: 0;
        &::after {
          content: none;
        }
      }
      .el-tabs__active-bar {
        display: none !important;
      }
    }

    .center-preview-pv {
      .el-date-editor.el-input {
        width: auto;
      }
      .balance {
        .el-input {
          width: auto;
        }
      }
    }
  }
}

.center-preview .el-tabs /deep/ .el-tabs__header {
  padding: 0 60px;
  margin: 20px 0 30px;
  background: #fff;
  width: 100%;
  border-color: #28890b20;
  border-width: 0 0 0px;
  position: relative;
  border-style: solid;
  text-align: center;
  height: auto;
}

.center-preview .el-tabs /deep/ .el-tabs__header .el-tabs__item {
  padding: 0 10px;
  margin: 0 10px;
  color: #666;
  font-weight: 500;
  display: block;
  font-size: 16px;
  line-height: 60px;
  float: left;
  background: none;
  width: auto;
  position: relative;
  text-align: center;
  min-width: 100px;
  height: 60px;
}

.center-preview .el-tabs /deep/ .el-tabs__header .el-tabs__item:hover {
  padding: 0 10px;
  color: #fff;
  background: #3795c4;
  font-weight: 600;
  font-size: 16px;
  line-height: 60px;
  position: relative;
  text-align: center;
  height: 60px;
}

.center-preview .el-tabs /deep/ .el-tabs__header .el-tabs__item.is-active {
  padding: 0 10px;
  margin: 0 10px 0 0;
  color: #fff;
  font-weight: 600;
  display: block;
  font-size: 16px;
  line-height: 60px;
  float: left;
  background: #3795c4;
  width: auto;
  position: relative;
  text-align: center;
  min-width: 100px;
  height: 60px;
}

.center-preview .el-tabs /deep/ .el-tabs__content {
  padding: 0px;
  margin: 0px 0 0;
  background: none;
  width: calc(100% - 20px);
}
.center-preview .el-tabs /deep/ .el-tabs__content .el-tab-pane {
  width: 100%;
}

.center-preview-pv .el-form-item /deep/ .el-form-item__label {
  padding: 0 10px 0 0;
  color: #666;
  font-weight: 500;
  width: 100px;
  font-size: 14px;
  line-height: 40px;
  text-align: right;
}

.center-preview-pv .el-form-item .el-form-item__content {
  margin-left: 100px;
}

.center-preview-pv .el-input /deep/ .el-input__inner {
  padding: 0 12px;
  color: #666;
  font-size: 14px;
  border-color: #eee;
  border-radius: 4px;
  box-shadow: 0 0 0px rgba(64, 158, 255, 0.5);
  outline: none;
  background: #fff;
  width: auto;
  border-width: 0px;
  border-style: solid;
  min-width: 300px;
  height: 40px;
}

.center-preview-pv .el-select /deep/ .el-input__inner {
  padding: 0 12px;
  color: #666;
  font-size: 14px;
  border-color: #eee;
  border-radius: 4px;
  box-shadow: 0 0 0px rgba(64, 158, 255, 0.5);
  outline: none;
  background: #fff;
  width: auto;
  border-width: 0px;
  border-style: solid;
  min-width: 300px;
  height: 40px;
}

.center-preview-pv .el-date-editor /deep/ .el-input__inner {
  padding: 0 10px 0 30px;
  color: #666;
  font-size: 14px;
  border-color: #eee;
  border-radius: 4px;
  box-shadow: 0 0 0px rgba(64, 158, 255, 0.5);
  outline: none;
  background: #fff;
  width: auto;
  border-width: 0px;
  border-style: solid;
  min-width: 300px;
  height: 40px;
}

.center-preview-pv /deep/ .avatar-uploader-icon {
  cursor: pointer;
  color: #666;
  font-weight: 600;
  font-size: 24px;
  border-color: #eee;
  line-height: 80px;
  border-radius: 4px;
  background: #fff;
  width: 120px;
  border-width: 0px;
  border-style: solid;
  text-align: center;
  height: 80px;
}

.center-preview-pv .el-form-item.balance /deep/ .el-input__inner {
  border-radius: 4px;
  padding: 0 12px;
  color: #666;
  background: #fff;
  display: inline-block;
  width: auto;
  font-size: 14px;
  border-color: #eee;
  border-width: 0px;
  border-style: solid;
  min-width: 300px;
  height: 40px;
}
</style>
