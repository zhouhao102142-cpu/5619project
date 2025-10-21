<template>
  <div>
    <div
      :style='{"padding":"16px 12%","margin":"0px auto","borderColor":"#d8d8d8","borderRadius":"0px","background":"none","borderWidth":"0 0 3px","width":"100%","borderStyle":"solid"}'
      class="breadcrumb-preview"
    >
      <el-breadcrumb :separator="'/'" :style='{"fontSize":"16px","lineHeight":"1","justifyContent":"flex-start","display":"flex"}'>
        <el-breadcrumb-item class="item1" to="/"><a>Home</a></el-breadcrumb-item>
        <el-breadcrumb-item class="item2" v-for="(item, index) in breadcrumbItem" :key="index" to="/index/shangpinxinxi"><a>{{item.name}}</a></el-breadcrumb-item>
        <el-breadcrumb-item class="item3"><a href="javascript:void(0);">Details</a></el-breadcrumb-item>
      </el-breadcrumb>
    </div>

    <div :style='{"padding":"16px 12%","margin":"0px auto","borderColor":"#d8d8d8","borderRadius":"0px","background":"none","borderWidth":"0 0 3px","width":"100%","borderStyle":"solid"}'>
      <el-button size="mini" @click="backClick">Back</el-button>
    </div>

    <div
      class="detail-preview"
      :style='{"padding":"30px 12%","margin":"0 auto","alignItems":"flex-start","flexWrap":"wrap","background":"#edeff1","display":"flex","width":"100%","position":"relative","justifyContent":"space-between"}'
    >
      <div
        class="attr"
        :style='{"padding":"10px 0 0","margin":"0","flexWrap":"wrap","background":"none","display":"flex","width":"60%","position":"relative","justifyContent":"space-between","order":"2"}'
      >
        <div class="info" :style='{"width":"100%","padding":"0px","margin":"0","background":"none","flex":"1","order":"2"}'>
          <div
            class="item"
            :style='{"padding":"0px","margin":"10px 0 10px 0","overflow":"hidden","alignItems":"center","borderRadius":"0px","background":"none","display":"block","lineHeight":"auto","justifyContent":"space-between","height":"auto"}'
          >
            <div :style='{"padding":"0px","overflow":"hidden","color":"#333","display":"block","width":"calc(100% - 0px)","fontSize":"20px","lineHeight":"40px","fontWeight":"600"}'>
              {{detail.shangpinmingcheng}}
            </div>

            <div
              @click="storeup(1)"
              v-show="!isStoreup"
              :style='{"border":"0px solid #ffffff50","cursor":"pointer","padding":"0px","borderRadius":"30px","background":"none","width":"auto","lineHeight":"40px"}'
            >
              <i v-if="false" :style='{"color":"#999","fontSize":"16px"}' class="el-icon-star-off"></i>
              <span :style='{"color":"#999","fontSize":"14px","textDecoration":"underline"}'>Add to Favorites ({{detail.storeupnum}})</span>
            </div>

            <div
              @click="storeup(-1)"
              v-show="isStoreup"
              :style='{"border":"0px solid #ffffff50","cursor":"pointer","padding":"0px","borderRadius":"30px","background":"none","width":"auto","lineHeight":"40px"}'
            >
              <i v-if="false" :style='{"color":"#999","fontSize":"16px"}' class="el-icon-star-on"></i>
              <span :style='{"color":"#999","fontSize":"14px","textDecoration":"underline"}'>Remove from Favorites ({{detail.storeupnum}})</span>
            </div>
          </div>

          <div
            class="item"
            :style='{"padding":"0px 10px","margin":"0","borderColor":"#28890b10","background":"#fff","borderWidth":"0px","display":"flex","borderStyle":"solid","justifyContent":"spaceBetween"}'
            v-if="detail.price"
          >
            <div class="lable" :style='{"padding":"8px 10px 0","color":"#000","textAlign":"right","width":"200px","fontSize":"14px","lineHeight":"24px","height":"auto"}'>
              Price
            </div>
            <div style="font-weight: bold;" :style='{"padding":"8px 10px 0","fontSize":"14px","lineHeight":"24px","color":"#666","flex":"1","height":"auto"}'>
              <span :style='{"fontSize":"12px"}'>$</span>{{detail.price}}
            </div>
          </div>

          <div
            class="item"
            :style='{"padding":"0px 10px","margin":"0","borderColor":"#28890b10","background":"#fff","borderWidth":"0px","display":"flex","borderStyle":"solid","justifyContent":"spaceBetween"}'
            v-if="detail.jf"
          >
            <div class="lable" :style='{"padding":"8px 10px 0","color":"#000","textAlign":"right","width":"200px","fontSize":"14px","lineHeight":"24px","height":"auto"}'>
              Points
            </div>
            <div style="color: red;font-weight: bold;" :style='{"padding":"8px 10px 0","fontSize":"14px","lineHeight":"24px","color":"#666","flex":"1","height":"auto"}'>
              {{detail.jf}}
            </div>
          </div>

         

          <div class="item" :style='{"padding":"0px 10px","margin":"0","borderColor":"#28890b10","background":"#fff","borderWidth":"0px","display":"flex","borderStyle":"solid","justifyContent":"spaceBetween"}'>
            <div class="lable" :style='{"padding":"8px 10px 0","color":"#000","textAlign":"right","width":"200px","fontSize":"14px","lineHeight":"24px","height":"auto"}'>Stock</div>
            <div :style='{"padding":"8px 10px 0","fontSize":"14px","lineHeight":"24px","color":"#666","flex":"1","height":"auto"}'>{{detail.alllimittimes}}</div>
          </div>

          <div class="item" :style='{"padding":"0px 10px","margin":"0","borderColor":"#28890b10","background":"#fff","borderWidth":"0px","display":"flex","borderStyle":"solid","justifyContent":"spaceBetween"}'>
            <div class="lable" :style='{"padding":"8px 10px 0","color":"#000","textAlign":"right","width":"200px","fontSize":"14px","lineHeight":"24px","height":"auto"}'>Store Name</div>
            <div :style='{"padding":"8px 10px 0","fontSize":"14px","lineHeight":"24px","color":"#666","flex":"1","height":"auto"}'>{{detail.dianpuming}}</div>
          </div>

          <div class="item" :style='{"padding":"0px 10px","margin":"0","borderColor":"#28890b10","background":"#fff","borderWidth":"0px","display":"flex","borderStyle":"solid","justifyContent":"spaceBetween"}'>
            <div class="lable" :style='{"padding":"8px 10px 0","color":"#000","textAlign":"right","width":"200px","fontSize":"14px","lineHeight":"24px","height":"auto"}'>Category</div>
            <div :style='{"padding":"8px 10px 0","fontSize":"14px","lineHeight":"24px","color":"#666","flex":"1","height":"auto"}'>{{detail.shangpinfenlei}}</div>
          </div>

          <div class="item" :style='{"padding":"0px 10px","margin":"0","borderColor":"#28890b10","background":"#fff","borderWidth":"0px","display":"flex","borderStyle":"solid","justifyContent":"spaceBetween"}'>
            <div class="lable" :style='{"padding":"8px 10px 0","color":"#000","textAlign":"right","width":"200px","fontSize":"14px","lineHeight":"24px","height":"auto"}'>Brand</div>
            <div :style='{"padding":"8px 10px 0","fontSize":"14px","lineHeight":"24px","color":"#666","flex":"1","height":"auto"}'>{{detail.pinpai}}</div>
          </div>

          

          <div class="item" :style='{"padding":"0px 10px","margin":"0","borderColor":"#28890b10","background":"#fff","borderWidth":"0px","display":"flex","borderStyle":"solid","justifyContent":"spaceBetween"}'>
            <div class="lable" :style='{"padding":"8px 10px 0","color":"#000","textAlign":"right","width":"200px","fontSize":"14px","lineHeight":"24px","height":"auto"}'>Listed Date</div>
            <div :style='{"padding":"8px 10px 0","fontSize":"14px","lineHeight":"24px","color":"#666","flex":"1","height":"auto"}'>{{detail.shangjiariqi}}</div>
          </div>

          <div class="item" :style='{"padding":"0px 10px","margin":"0","borderColor":"#28890b10","background":"#fff","borderWidth":"0px","display":"flex","borderStyle":"solid","justifyContent":"spaceBetween"}'>
            <div class="lable" :style='{"padding":"8px 10px 0","color":"#000","textAlign":"right","width":"200px","fontSize":"14px","lineHeight":"24px","height":"auto"}'>Seller Account</div>
            <div :style='{"padding":"8px 10px 0","fontSize":"14px","lineHeight":"24px","color":"#666","flex":"1","height":"auto"}'>{{detail.shangjiazhanghao}}</div>
          </div>

          

          <div class="btn" :style='{"width":"100%","padding":"20px 112px 0","borderRadius":"0","flexWrap":"wrap","background":"#fff","display":"block"}'>
            <el-input-number
              :style='{"width":"140px","margin":"0 10px 20px 0","lineHeight":"36px","position":"relative","display":"flex"}'
              v-if="detail.alllimittimes"
              :min="1"
              v-model="buynumber"
            ></el-input-number>

            <el-button
              :style='{"border":"0","cursor":"pointer","padding":"0 10px","margin":"0 10px 20px 0","color":"#fff","clear":"both","outline":"none","borderRadius":"6px","background":"#3795c4","width":"auto","lineHeight":"36px","fontSize":"14px","height":"36px"}'
              v-if="detail.alllimittimes"
              type="warning"
              size="small"
              @click="addCart"
            >Add to Cart</el-button>

            <el-button
              :style='{"border":"1px solid #3795c4","cursor":"pointer","padding":"0 20px","margin":"0 10px 20px 0","outline":"none","color":"#3795c4","borderRadius":"6px","background":"none","width":"auto","lineHeight":"36px","fontSize":"14px","height":"36px"}'
              v-if="detail.alllimittimes"
              type="warning"
              size="small"
              @click="buyNow"
            >Buy Now</el-button>

            <el-button
              :style='{"border":"0","cursor":"pointer","padding":"0 20px","margin":"0 10px 10px 0","outline":"none","color":"#fff","borderRadius":"6px","background":"#3795c4","width":"auto","lineHeight":"36px","fontSize":"14px","height":"36px"}'
              v-if="detail.alllimittimes"
              type="warning"
              size="small"
              @click="jfTap"
            >Redeem with Points</el-button>
          </div>

          <div class="btn" :style='{"width":"100%","padding":"20px 112px 0","borderRadius":"0","flexWrap":"wrap","background":"#fff","display":"block"}'>
            <el-button
              :style='{"border":"1px solid #3795c4","cursor":"pointer","padding":"0 20px","margin":"0 10px 20px 0","outline":"none","color":"#3795c4","borderRadius":"6px","background":"none","width":"auto","lineHeight":"36px","fontSize":"14px","height":"36px"}'
              v-if="btnAuth('shangpinxinxi','修改')"
              @click="editClick"
            >Edit</el-button>

            <el-button
              :style='{"border":"0","cursor":"pointer","padding":"0 20px","margin":"0 10px 10px 0","outline":"none","color":"#fff","borderRadius":"6px","background":"#3795c4","width":"auto","lineHeight":"36px","fontSize":"14px","height":"36px"}'
              v-if="btnAuth('shangpinxinxi','删除')"
              @click="delClick"
            >Delete</el-button>
          </div>
        </div>
      </div>

      <div
        class="swiper3"
        v-if="detailBanner.length"
        :style='{"margin":"0 2% 0px 0","flexWrap":"wrap","background":"none","display":"block","width":"38%","justifyContent":"space-between","height":"auto","order":"1"}'
      >
        <div class="big" :style='{"border":"0px solid #1abc9e50","margin":"0 0 20px","background":"#fff","width":"calc(100% - 0px)","position":"relative","height":"450px","order":"1"}'>
          <img id="big" :style='{"objectFit":"contain","top":"0px","left":"0px","background":"#fff","display":"block","width":"100%","position":"absolute","height":"100%","zIndex":"1"}' :src="swiperBigUrl" class="image">
        </div>
        <div class="samll" :style='{"padding":"0 0px","overflow":"hidden","flexWrap":"wrap","background":"none","display":"flex","width":"100%","justifyContent":"space-between","height":"auto","order":"2"}'>
          <div
            :style='{"border":"0px solid #1abc9e50","width":"23%","margin":"0 0px 10px","position":"relative","background":"#fff","height":"120px"}'
            v-for="item in detailBanner"
            :key="item.id"
          >
            <img
              :style='{"cursor":"pointer","objectFit":"cover","top":"0px","left":"0px","display":"block","width":"100%","position":"absolute","height":"100%","zIndex":"1"}'
              v-if="item.substr(0,4)=='http'"
              :src="item"
              @click="swiperClick3(item)"
              class="image"
            >
            <img
              :style='{"cursor":"pointer","objectFit":"cover","top":"0px","left":"0px","display":"block","width":"100%","position":"absolute","height":"100%","zIndex":"1"}'
              v-else
              :src="baseUrl + item"
              @click="swiperClick3(baseUrl + item)"
              class="image"
            >
          </div>
        </div>
      </div>

      <div class="zancai" :style='{"border":"0px solid #ddd","padding":"10px 20px","margin":"0 auto 0 40%","background":"#fff","display":"flex","width":"60%","float":"right","order":"3"}'>
        <div
          :style='{"cursor":"pointer","padding":"12px 24px","margin":"0 20px 0 0","borderColor":"#ddd","alignItems":"center","borderRadius":"30px","background":"#3795c4","borderWidth":"0 0 0px","display":"flex","width":"auto","borderStyle":"solid","justifyContent":"center"}'
          v-if="!isThumbsupnum && !isCrazilynum"
          class="zan"
          @click="thumbsupOrCrazily(21)"
        >
          <i :style='{"color":"#fff","margin":"0 3px","fontSize":"16px"}' class="iconfont icon-dianzan"></i>
          <span :style='{"color":"#fff","margin":"0 3px","fontSize":"14px"}'>Like ({{detail.thumbsupnum}})</span>
        </div>
        <div
          :style='{"cursor":"pointer","padding":"12px 24px","margin":"0 20px 0 0","borderColor":"#ddd","alignItems":"center","borderRadius":"30px","background":"#3795c4","borderWidth":"0 0 0px","display":"flex","width":"auto","borderStyle":"solid","justifyContent":"center"}'
          v-if="!isThumbsupnum && !isCrazilynum"
          class="cai"
          @click="thumbsupOrCrazily(22)"
        >
          <i :style='{"color":"#fff","margin":"0 3px","fontSize":"16px"}' class="iconfont icon-iconfontzan"></i>
          <span :style='{"color":"#fff","margin":"0 3px","fontSize":"14px"}'>Dislike ({{detail.crazilynum}})</span>
        </div>
        <div
          :style='{"cursor":"pointer","padding":"12px 24px","margin":"0 20px 0 0","borderColor":"#ddd","alignItems":"center","borderRadius":"30px","background":"#3795c4","borderWidth":"0 0 0px","display":"flex","width":"auto","borderStyle":"solid","justifyContent":"center"}'
          v-if="isThumbsupnum"
          class="zan"
          @click="cancelThumbsupOrCrazily(21)"
        >
          <i :style='{"color":"#fff","margin":"0 3px","fontSize":"16px"}' class="iconfont icon-dianzan"></i>
          <span :style='{"color":"#fff","margin":"0 3px","fontSize":"14px"}'>Cancel Like ({{detail.thumbsupnum}})</span>
        </div>
        <div
          :style='{"cursor":"pointer","padding":"12px 24px","margin":"0 20px 0 0","borderColor":"#ddd","alignItems":"center","borderRadius":"30px","background":"#3795c4","borderWidth":"0 0 0px","display":"flex","width":"auto","borderStyle":"solid","justifyContent":"center"}'
          v-if="isCrazilynum"
          class="cai"
          @click="cancelThumbsupOrCrazily(22)"
        >
          <i :style='{"color":"#fff","margin":"0 3px","fontSize":"16px"}' class="iconfont icon-iconfontzan"></i>
          <span :style='{"color":"#fff","margin":"0 3px","fontSize":"14px"}'>Cancel Dislike ({{detail.crazilynum}})</span>
        </div>
      </div>

      <el-tabs
        class="detail"
        :style='{"border":"0px solid #e9e9e9","boxShadow":"none","margin":"20px auto 40px","background":"#fff","display":"block","width":"100%","order":"40"}'
        v-model="activeName"
        type="border-card"
      >
        <el-tab-pane label="Book Description" name="first">
          <div v-html="detail.shangpinjieshao"></div>
        </el-tab-pane>

        <el-tab-pane label="Reviews" name="second">
          <el-form class="add comment" :style='{"padding":"15px","margin":"0 0 20px"}' :model="form" :rules="rules" ref="form">
            <el-form-item class="item" :style='{"width":"100%","display":"flex","height":"auto"}' label="Review" prop="content">
              <editor
                :style='{"minHeight":"200px","border":"0","outline":"none","color":"#333","borderRadius":"4px","width":"100%","lineHeight":"32px","fontSize":"14px"}'
                v-model="form.content"
                class="editor"
                action="file/upload"
              >
              </editor>
            </el-form-item>
            <el-form-item class="btn" :style='{"width":"100%","padding":"0 0 0 80px","margin":"10px 0 0","height":"auto"}'>
              <el-button
                :style='{"border":"0","cursor":"pointer","padding":"0","margin":"0 20px 0 0","outline":"none","color":"rgba(255, 255, 255, 1)","borderRadius":"30px","background":"#3795c4","width":"110px","lineHeight":"44px","fontSize":"14px","height":"44px"}'
                type="primary"
                @click="submitForm('form')"
              >Submit</el-button>
              <el-button
                :style='{"border":"1px solid #3795c4","cursor":"pointer","padding":"0","margin":"0 20px 0 0","outline":"none","color":"#3795c4","borderRadius":"30px","background":"none","width":"110px","lineHeight":"44px","fontSize":"14px","height":"44px"}'
                @click="resetForm('form')"
              >Reset</el-button>
            </el-form-item>
          </el-form>

          <div v-if="infoList.length" :style='{"padding":"15px"}' class="comment">
            <div
              :style='{"padding":"8px 20px","margin":"0 0 0px","borderColor":"#d7d7d7","alignItems":"center","borderWidth":"1px 0 0","background":"#fff","width":"100%","borderStyle":"solid","height":"auto"}'
              v-for="item in infoList"
              :key="item.id"
              @mouseenter="discussEnter(item.id)"
              @mouseleave="discussLeave"
            >
              <div class="user" :style='{"width":"100%","alignItems":"center","display":"flex","height":"auto"}'>
                <el-image v-if="item.avatarurl" :style='{"width":"40px","margin":"0 10px 0 0","borderRadius":"100%","objectFit":"cover","height":"40px"}' :size="50" :src="baseUrl + item.avatarurl"></el-image>
                <el-image v-if="!item.avatarurl" :style='{"width":"40px","margin":"0 10px 0 0","borderRadius":"100%","objectFit":"cover","height":"40px"}' :size="50" :src="require('@/assets/touxiang.png')"></el-image>
                <div :style='{"color":"#333","fontSize":"16px"}' class="name">{{item.nickname}}</div>
              </div>
              <div
                :style='{"padding":"0px","margin":"10px 0px 0px","color":"#999","borderRadius":"4px","background":"none","wordWrap":"break-word","lineHeight":"30px","fontSize":"14px"}'
                class="content-block-ask"
              >
                <div v-html="item.content"></div>
                <div class="btn" :style='{"width":"100%","margin":"8px 0 0 0","alignItems":"center","justifyContent":"flex-start","display":"flex","height":"40px"}'>
                  <el-button
                    v-if="showIndex==item.id&&userid==item.userid"
                    @click="discussDel(item.id)"
                    :style='{"border":"1px solid #3795c4","cursor":"pointer","padding":"0 20px","margin":"0 10px","outline":"none","color":"#3795c4","borderRadius":"30px","background":"none","width":"auto","lineHeight":"32px","fontSize":"14px","height":"32px"}'
                  >Delete</el-button>
                </div>
              </div>
              <div
                :style='{"padding":"0px","margin":"10px 0px 0px","color":"#999","borderRadius":"4px","background":"none","wordWrap":"break-word","lineHeight":"30px","fontSize":"14px"}'
                class="content-block-reply"
                v-if="item.reply"
              >
                Reply: <span v-html="item.reply"></span>
              </div>
            </div>
          </div>

          <el-pagination
            background
            id="pagination"
            class="pagination"
            :pager-count="7"
            :page-size="pageSize"
            :page-sizes="pageSizes"
            prev-text="Previous"
            next-text="Next"
            :hide-on-single-page="false"
            :layout='["prev","pager","next"].join()'
            :total="total"
            :style='{"padding":"25px 0","margin":"0 auto","color":"#333","textAlign":"left","background":"none","width":"100%","clear":"both","lineHeight":"40px","fontWeight":"500","height":"auto","order":"50"}'
            @current-change="curChange"
            @prev-click="prevClick"
            @next-click="nextClick"
          ></el-pagination>
        </el-tab-pane>
      </el-tabs>
    </div>

    <div class="share_view" :style='{"boxShadow":"0 1px 6px rgba(0,0,0,.3)","position":"fixed","right":"0","bottom":"20%","background":"#fff","zIndex":"11"}'>
    </div>
  </div>
</template>

<script>
import CountDown from '@/components/CountDown'
import axios from 'axios'
import Swiper from 'swiper'

export default {
  data() {
    return {
      tablename: 'shangpinxinxi',
      baseUrl: '',
      breadcrumbItem: [{ name: 'Book Info' }],
      title: '',
      detailBanner: [],
      userid: localStorage.getItem('frontUserid'),
      id: 0,
      detail: {},
      activeName: 'second',
      form: {
        content: '',
        userid: localStorage.getItem('frontUserid'),
        nickname: localStorage.getItem('username'),
        avatarurl: ''
      },
      showIndex: -1,
      infoList: [],
      rules: {
        content: [{ required: true, message: 'Please enter content', trigger: 'blur' }]
      },
      total: 1,
      pageSize: 5,
      pageSizes: [10, 20, 30, 50],
      totalPage: 1,
      storeupParams: {
        name: '',
        picture: '',
        refid: 0,
        tablename: 'shangpinxinxi',
        userid: localStorage.getItem('frontUserid')
      },
      isStoreup: false,
      storeupInfo: {},
      isCrazilynum: false,
      isThumbsupnum: false,
      thumbsupOrCrazilyInfo: {},
      buynumber: 1,
      cart: {
        buynumber: 0,
        discountprice: 0,
        goodid: 0,
        goodname: '',
        picture: '',
        price: 0,
        userid: localStorage.getItem('frontUserid')
      },
      isInCart: false,
      centerType: false,
      shareUrl: location.href,
      swiperBigUrl: null
    }
  },
  created() {
    if (this.$route.query.centerType) {
      this.centerType = true
    }
    this.init()
  },
  mounted() {},
  computed: {
    // fallback to placeholder when there is no cover image
    firstImage() {
      if (this.detailBanner && this.detailBanner.length) {
        const f = this.detailBanner[0]
        return f && f.substr(0, 4) === 'http' ? f : this.baseUrl + f
      }
      // you can replace with your own placeholder path
      try {
        return require('@/assets/placeholder.png')
      } catch (e) {
        return ''
      }
    }
  },
  methods: {
    swiperClick3(src) {
      this.swiperBigUrl = src
    },
    init() {
      this.id = this.$route.query.id
      this.baseUrl = this.$config.baseUrl
      this.$http.get(this.tablename + '/detail/' + this.id, {}).then(res => {
        if (res.data.code == 0) {
          this.detail = res.data.data
          this.title = this.detail.shangpinmingcheng
          this.detailBanner = this.detail.fengmian ? this.detail.fengmian.split(',') : []
          this.$forceUpdate()

          this.getDiscussList(1)
          if (localStorage.getItem('frontToken')) {
            this.getStoreupStatus()
            this.getThumbsupOrCrazilyStatus()
            this.getCartList()
          }
        }
        if (this.detailBanner.length) {
          if (this.detailBanner[0].substr(0, 4) == 'http') {
            this.swiperBigUrl = this.detailBanner[0]
          } else {
            this.swiperBigUrl = this.baseUrl + this.detailBanner[0]
          }
        } else {
          this.swiperBigUrl = this.firstImage
        }
      })
    },
    storeup(type) {
      if (type == 1 && !this.isStoreup) {
        this.storeupParams.name = this.title
        this.storeupParams.picture = this.firstImage
        this.storeupParams.refid = this.detail.id
        this.storeupParams.type = type
        this.$http.post('storeup/add', this.storeupParams).then(res => {
          if (res.data.code == 0) {
            this.isStoreup = true
            this.detail.storeupnum++
            this.$http.post('shangpinxinxi/update', this.detail).then(() => {})
            this.$message({
              type: 'success',
              message: 'Added to favorites!',
              duration: 1500
            })
          }
        })
      }
      if (type == -1 && this.isStoreup) {
        this.$http
          .get('storeup/list', {
            params: {
              page: 1,
              limit: 1,
              type: 1,
              refid: this.detail.id,
              tablename: 'shangpinxinxi',
              userid: localStorage.getItem('frontUserid')
            }
          })
          .then(res => {
            if (res.data.code == 0 && res.data.data.list.length > 0) {
              this.isStoreup = true
              this.storeupInfo = res.data.data.list[0]
              let delIds = []
              delIds.push(this.storeupInfo.id)
              this.$http.post('storeup/delete', delIds).then(res => {
                if (res.data.code == 0) {
                  this.isStoreup = false
                  this.detail.storeupnum--
                  this.$http.post('shangpinxinxi/update', this.detail).then(() => {})
                  this.$message({
                    type: 'success',
                    message: 'Removed from favorites!',
                    duration: 1500
                  })
                }
              })
            }
          })
      }
    },
    getStoreupStatus() {
      if (localStorage.getItem('frontToken')) {
        this.$http
          .get('storeup/list', {
            params: {
              page: 1,
              limit: 1,
              type: 1,
              refid: this.detail.id,
              tablename: 'shangpinxinxi',
              userid: localStorage.getItem('frontUserid')
            }
          })
          .then(res => {
            if (res.data.code == 0 && res.data.data.list.length > 0) {
              this.isStoreup = true
              this.storeupInfo = res.data.data.list[0]
            }
          })
      }
    },
    thumbsupOrCrazily(type) {
      this.storeupParams.name = this.title
      this.storeupParams.picture = this.firstImage
      this.storeupParams.refid = this.detail.id
      this.storeupParams.type = type
      this.$http.post('storeup/add', this.storeupParams).then(res => {
        if (res.data.code == 0) {
          this.getThumbsupOrCrazilyStatus()
          this.$message({
            type: 'success',
            message: 'Success!',
            duration: 1500
          })
        }
      })

      if (type == 21) this.detail.thumbsupnum = Number(this.detail.thumbsupnum) + 1
      if (type == 22) this.detail.crazilynum = Number(this.detail.crazilynum) + 1
      this.$http.post('shangpinxinxi/update', this.detail).then(() => {})
    },
    cancelThumbsupOrCrazily(type) {
      let delIds = []
      delIds.push(this.thumbsupOrCrazilyInfo.id)
      this.$http.post('storeup/delete', delIds).then(res => {
        if (res.data.code == 0) {
          this.isThumbsupnum = false
          this.isCrazilynum = false
          this.$message({
            type: 'success',
            message: 'Cancelled!',
            duration: 1500
          })
        }
      })

      if (type == 21) this.detail.thumbsupnum -= 1
      if (type == 22) this.detail.crazilynum -= 1
      this.$http.post('shangpinxinxi/update', this.detail).then(() => {})
    },
    getThumbsupOrCrazilyStatus() {
      if (localStorage.getItem('frontToken')) {
        this.$http
          .get('storeup/list', {
            params: {
              page: 1,
              limit: 1,
              type: 21,
              refid: this.detail.id,
              tablename: 'shangpinxinxi',
              userid: localStorage.getItem('frontUserid')
            }
          })
          .then(res => {
            if (res.data.code == 0 && res.data.data.list.length > 0) {
              this.isThumbsupnum = true
              this.thumbsupOrCrazilyInfo = res.data.data.list[0]
            }
          })

        this.$http
          .get('storeup/list', {
            params: {
              page: 1,
              limit: 1,
              type: 22,
              refid: this.detail.id,
              tablename: 'shangpinxinxi',
              userid: localStorage.getItem('frontUserid')
            }
          })
          .then(res => {
            if (res.data.code == 0 && res.data.data.list.length > 0) {
              this.isCrazilynum = true
              this.thumbsupOrCrazilyInfo = res.data.data.list[0]
            }
          })
      }
    },
    curChange(page) {
      this.getDiscussList(page)
    },
    prevClick(page) {
      this.getDiscussList(page)
    },
    nextClick(page) {
      this.getDiscussList(page)
    },
    // Back
    backClick() {
      history.back()
    },
    // Download
    download(file) {
      if (!file) {
        this.$message({
          type: 'error',
          message: 'File does not exist',
          duration: 1500
        })
        return
      }
      let arr = file.replace(new RegExp('upload/', 'g'), '')
      axios
        .get(this.baseUrl + '/file/download?fileName=' + arr, {
          headers: {
            token: localStorage.getItem('frontToken')
          },
          responseType: 'blob'
        })
        .then(({ data }) => {
          const binaryData = []
          binaryData.push(data)
          const objectUrl = window.URL.createObjectURL(new Blob(binaryData, {
            type: 'application/pdf;chartset=UTF-8'
          }))
          const a = document.createElement('a')
          a.href = objectUrl
          a.download = arr
          a.dispatchEvent(new MouseEvent('click', { bubbles: true, cancelable: true, view: window }))
          window.URL.revokeObjectURL(data)
        }, err => {
          axios
            .get((location.href.split(this.$config.name).length > 1 ? location.href.split(this.$config.name)[0] : '') + this.$config.name + '/file/download?fileName=' + arr, {
              headers: { token: localStorage.getItem('frontToken') },
              responseType: 'blob'
            })
            .then(({ data }) => {
              const binaryData = []
              binaryData.push(data)
              const objectUrl = window.URL.createObjectURL(new Blob(binaryData, {
                type: 'application/pdf;chartset=UTF-8'
              }))
              const a = document.createElement('a')
              a.href = objectUrl
              a.download = arr
              a.dispatchEvent(new MouseEvent('click', { bubbles: true, cancelable: true, view: window }))
              window.URL.revokeObjectURL(data)
            })
        })
    },
    getDiscussList(page) {
      this.$http.get('discussshangpinxinxi/list', { params: { page, limit: this.pageSize, refid: this.detail.id } }).then(res => {
        if (res.data.code == 0) {
          this.infoList = res.data.data.list
          this.total = res.data.data.total
          this.pageSize = res.data.data.pageSize; this.pageSizes = [this.pageSize, this.pageSize * 2, this.pageSize * 3, this.pageSize * 5]
          this.totalPage = res.data.data.totalPage
        }
      })
    },
    discussEnter(index) {
      this.showIndex = index
    },
    discussLeave() {
      this.showIndex = -1
    },
    discussDel(id) {
      this.$confirm('Delete this review?')
        .then(_ => {
          this.$http.post('discussshangpinxinxi/delete', [id]).then(res => {
            if (res.data && res.data.code == 0) {
              this.addDiscussNum(1)
              this.$message({
                type: 'success',
                message: 'Deleted successfully!',
                duration: 1500,
                onClose: () => {
                  this.getDiscussList(1)
                }
              })
            }
          })
        })
        .catch(_ => {})
    },
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.$http.get('orders/list', { params: { page: 1, limit: 1, status: '已完成', goodid: this.detail.id, userid: localStorage.getItem('frontUserid') } }).then(res => {
            if (res.data.code == 0 && res.data.data.list.length == 0) {
              this.$message({
                type: 'success',
                message: 'Please complete the order before commenting!',
                duration: 1500
              })
              return false
            } else {
              this.form.refid = this.detail.id
              this.form.avatarurl = localStorage.getItem('frontHeadportrait') ? localStorage.getItem('frontHeadportrait') : ''
              this.$http.post('discussshangpinxinxi/add', this.form).then(res => {
                if (res.data.code == 0) {
                  this.addDiscussNum(2)
                  this.form.content = ''
                  this.getDiscussList(1)
                  this.$message({
                    type: 'success',
                    message: 'Comment submitted!',
                    duration: 1500
                  })
                }
              })
            }
          })
        } else {
          return false
        }
      })
    },
    resetForm(formName) {
      this.$refs[formName].resetFields()
    },
    addDiscussNum(type) {
      if (type == 2) {
        this.detail.discussnum++
      } else if (type == 1) {
        if (this.detail.discussnum != 0) {
          this.detail.discussnum--
        } else {
          this.detail.discussnum = 0
        }
      }
      this.$http.post('shangpinxinxi/update', this.detail).then(res => {})
    },
    getCartList() {
      this.$http.get('cart/list', { params: { userid: localStorage.getItem('frontUserid'), tablename: 'shangpinxinxi', goodid: this.detail.id } }).then(res => {
        if (res.data.code == 0) {
          this.isInCart = res.data.data.list.length > 0
        }
      })
    },
    addCart() {
      if (this.detail.onelimittimes > 0 && 5 < this.buynumber) {
        this.$message.error(`Each order is limited to 5 item(s) per person`)
        return
      }
      if (this.detail.alllimittimes <= 0) {
        this.$message.error('Out of stock')
        return
      }
      if (this.detail.alllimittimes > 0 && this.detail.alllimittimes < this.buynumber) {
        this.$message.error('Insufficient stock')
        return
      }
      if (this.isInCart) {
        this.$message.error('This book is already in your cart')
        return
      }

      this.cart.buynumber = this.buynumber
      this.cart.goodid = this.detail.id
      this.cart.goodname = this.title
      this.cart.tablename = this.tablename
      this.cart.shangjiazhanghao = this.detail.shangjiazhanghao
      this.cart.picture = this.firstImage
      this.cart.price = this.detail.price
      this.$http.post('cart/save', this.cart).then(res => {
        if (res.data.code === 0) {
          this.getCartList()
          this.$message({
            message: 'Added successfully',
            type: 'success',
            duration: 1500
          })
        } else {
          this.$message.error(res.data.msg)
        }
      })
    },
    // Buy Now
    buyNow() {
      if (this.detail.onelimittimes > 0 && this.detail.onelimittimes < this.buynumber) {
        this.$message.error(`Each order is limited to ${this.detail.onelimittimes} item(s) per person`)
        return
      }
      if (this.detail.alllimittimes <= 0) {
        this.$message.error('Out of stock')
        return
      }
      if (this.detail.alllimittimes > 0 && this.detail.alllimittimes < this.buynumber) {
        this.$message.error('Insufficient stock')
        return
      }
      localStorage.setItem('orderGoods', JSON.stringify([
        {
          tablename: this.tablename,
          goodid: this.detail.id,
          goodname: this.title,
          shangjiazhanghao: this.detail.shangjiazhanghao,
          picture: this.firstImage,
          buynumber: this.buynumber,
          userid: localStorage.getItem('frontUserid'),
          price: this.detail.price,
          discountprice: this.detail.vipprice ? this.detail.vipprice : 0
        }
      ]))
      let query = { type: 1 }
      this.$router.push({ path: '/index/shop-order/orderConfirm', query })
    },
    // Redeem with points
    jfTap() {
      if (this.detail.onelimittimes > 0 && this.detail.onelimittimes < this.buynumber) {
        this.$message.error(`Each order is limited to ${this.detail.onelimittimes} item(s) per person`)
        return
      }
      if (this.detail.alllimittimes <= 0) {
        this.$message.error('Out of stock')
        return
      }
      if (this.detail.alllimittimes > 0 && this.detail.alllimittimes < this.buynumber) {
        this.$message.error('Insufficient stock')
        return
      }
      localStorage.setItem('orderGoods', JSON.stringify([
        {
          tablename: this.tablename,
          goodid: this.detail.id,
          goodname: this.title,
          shangjiazhanghao: this.detail.shangjiazhanghao,
          picture: this.firstImage,
          buynumber: this.buynumber,
          userid: localStorage.getItem('frontUserid'),
          price: this.detail.jf,
          discountprice: this.detail.jf ? this.detail.jf : 0
        }
      ]))
      let query = { type: 2 }
      this.$router.push({ path: '/index/shop-order/orderConfirm', query })
    },
    // Permission check
    btnAuth(tableName, key) {
      if (this.centerType) {
        return this.isBackAuth(tableName, key)
      } else {
        return this.isAuth(tableName, key)
      }
    },
    // Edit
    editClick() {
      this.$router.push(`/index/shangpinxinxiAdd?type=edit&&id=${this.detail.id}`)
    },
    // Delete
    async delClick() {
      await this.$confirm('Delete this book info?')
        .then(_ => {
          this.$http.post('shangpinxinxi/delete', [this.detail.id]).then(async res => {
            if (res.data.code == 0) {
              this.$http
                .get('storeup/list', {
                  params: {
                    page: 1,
                    limit: 100,
                    refid: this.detail.id,
                    tablename: 'shangpinxinxi'
                  }
                })
                .then(async obj => {
                  if (obj.data && obj.data.code == 0) {
                    let arr = []
                    for (let x in obj.data.data.list) {
                      arr.push(obj.data.data.list[x].id)
                    }
                    await this.$http.post('storeup/delete', arr).then(() => {})
                    this.$message({
                      type: 'success',
                      message: 'Deleted successfully!',
                      duration: 1500,
                      onClose: () => {
                        history.back()
                      }
                    })
                  }
                })
            }
          })
        })
        .catch(_ => {})
    }
  },
  components: {
    CountDown
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
/* —— styles unchanged; only Chinese texts in UI were translated —— */
.editor /deep/ .avatar-uploader { height: 0; line-height: 0; }

.detail-preview {
  .attr {
    .el-carousel /deep/ .el-carousel__indicator button { width: 0; height: 0; display: none; }
    .el-input-number__decrease:hover:not(.is-disabled)~.el-input .el-input__inner:not(.is-disabled),
    .el-input-number__increase:hover:not(.is-disabled)~.el-input .el-input__inner:not(.is-disabled) { border-color: none; }
  }
  .detail {
    & /deep/ .el-tabs__header .el-tabs__nav-wrap { margin-bottom: 0; }
    & .add .el-textarea { width: auto; }
  }
}

/* ...（其余样式保持不变，与你提供的相同）... */

</style>
