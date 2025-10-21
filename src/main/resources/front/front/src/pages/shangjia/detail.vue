<template>
  <div>
    <div
      class="breadcrumb-preview"
      :style='{"padding":"16px 12%","margin":"0px auto","borderColor":"#d8d8d8","borderRadius":"0px","background":"none","borderWidth":"0 0 3px","width":"100%","borderStyle":"solid"}'
    >
      <el-breadcrumb :separator="'/'" :style='{"fontSize":"16px","lineHeight":"1","justifyContent":"flex-start","display":"flex"}'>
        <el-breadcrumb-item class="item1" to="/"><a>Home</a></el-breadcrumb-item>
        <el-breadcrumb-item
          class="item2"
          v-for="(item, index) in breadcrumbItem"
          :key="index"
          to="/index/shangjia"
          ><a>{{ item.name }}</a></el-breadcrumb-item
        >
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
            <div
              :style='{"padding":"0px","overflow":"hidden","color":"#333","display":"block","width":"calc(100% - 0px)","fontSize":"20px","lineHeight":"40px","fontWeight":"600"}'
            >
              {{ detail.dianpuming }}
            </div>
          </div>

          <div
            class="item"
            :style='{"padding":"0px 10px","margin":"0","borderColor":"#28890b10","background":"#fff","borderWidth":"0px","display":"flex","borderStyle":"solid","justifyContent":"spaceBetween"}'
          >
            <div class="lable" :style='{"padding":"8px 10px 0","color":"#000","textAlign":"right","width":"102px","fontSize":"14px","lineHeight":"24px","height":"auto"}'>
              Seller Account
            </div>
            <div :style='{"padding":"8px 10px 0","fontSize":"14px","lineHeight":"24px","color":"#666","flex":"1","height":"auto"}'>
              {{ detail.shangjiazhanghao }}
            </div>
          </div>

          <div
            class="item"
            :style='{"padding":"0px 10px","margin":"0","borderColor":"#28890b10","background":"#fff","borderWidth":"0px","display":"flex","borderStyle":"solid","justifyContent":"spaceBetween"}'
          >
            <div class="lable" :style='{"padding":"8px 10px 0","color":"#000","textAlign":"right","width":"102px","fontSize":"14px","lineHeight":"24px","height":"auto"}'>
              Store Address
            </div>
            <div :style='{"padding":"8px 10px 0","fontSize":"14px","lineHeight":"24px","color":"#666","flex":"1","height":"auto"}'>
              {{ detail.dianpudizhi }}
            </div>
          </div>

          <div
            class="item"
            :style='{"padding":"0px 10px","margin":"0","borderColor":"#28890b10","background":"#fff","borderWidth":"0px","display":"flex","borderStyle":"solid","justifyContent":"spaceBetween"}'
          >
            <div class="lable" :style='{"padding":"8px 10px 0","color":"#000","textAlign":"right","width":"102px","fontSize":"14px","lineHeight":"24px","height":"auto"}'>
              Manager
            </div>
            <div :style='{"padding":"8px 10px 0","fontSize":"14px","lineHeight":"24px","color":"#666","flex":"1","height":"auto"}'>
              {{ detail.fuzeren }}
            </div>
          </div>

          <div
            class="item"
            :style='{"padding":"0px 10px","margin":"0","borderColor":"#28890b10","background":"#fff","borderWidth":"0px","display":"flex","borderStyle":"solid","justifyContent":"spaceBetween"}'
          >
            <div class="lable" :style='{"padding":"8px 10px 0","color":"#000","textAlign":"right","width":"102px","fontSize":"14px","lineHeight":"24px","height":"auto"}'>
              Phone
            </div>
            <div :style='{"padding":"8px 10px 0","fontSize":"14px","lineHeight":"24px","color":"#666","flex":"1","height":"auto"}'>
              {{ detail.lianxidianhua }}
            </div>
          </div>

          <div
            class="item"
            :style='{"padding":"0px 10px","margin":"0","borderColor":"#28890b10","background":"#fff","borderWidth":"0px","display":"flex","borderStyle":"solid","justifyContent":"spaceBetween"}'
          >
            <div class="lable" :style='{"padding":"8px 10px 0","color":"#000","textAlign":"right","width":"102px","fontSize":"14px","lineHeight":"24px","height":"auto"}'>
              Email
            </div>
            <div :style='{"padding":"8px 10px 0","fontSize":"14px","lineHeight":"24px","color":"#666","flex":"1","height":"auto"}'>
              {{ detail.youxiang }}
            </div>
          </div>

          <div
            class="item"
            :style='{"padding":"0px 10px","margin":"0","borderColor":"#28890b10","background":"#fff","borderWidth":"0px","display":"flex","borderStyle":"solid","justifyContent":"spaceBetween"}'
          >
            <div class="lable" :style='{"padding":"8px 10px 0","color":"#000","textAlign":"right","width":"102px","fontSize":"14px","lineHeight":"24px","height":"auto"}'>
              Business License
            </div>
            <el-button
              :style='{"padding":"0 2px","margin":"4px 0 0","borderColor":"#3795c4","color":"#3795c4","textAlign":"center","textDecoration":"none","background":"none","borderWidth":"0 0 1px","width":"auto","lineHeight":"30px","fontSize":"14px","borderStyle":"solid","height":"30px"}'
              @click="download(detail.yingyezhizhao)"
              >Download</el-button
            >
          </div>

          <div class="btn" :style='{"width":"100%","padding":"20px 112px 0","borderRadius":"0","flexWrap":"wrap","background":"#fff","display":"block"}'>
            <el-button
              :style='{"border":"1px solid #3795c4","cursor":"pointer","padding":"0 20px","margin":"0 10px 20px 0","outline":"none","color":"#3795c4","borderRadius":"6px","background":"none","width":"auto","lineHeight":"36px","fontSize":"14px","height":"36px"}'
              v-if="btnAuth('shangjia','修改')"
              @click="editClick"
              >Edit</el-button
            >
            <el-button
              :style='{"border":"0","cursor":"pointer","padding":"0 20px","margin":"0 10px 10px 0","outline":"none","color":"#fff","borderRadius":"6px","background":"#3795c4","width":"auto","lineHeight":"36px","fontSize":"14px","height":"36px"}'
              v-if="btnAuth('shangjia','删除')"
              @click="delClick"
              >Delete</el-button
            >
          </div>
        </div>
      </div>

      <div
        class="swiper3"
        v-if="detailBanner.length"
        :style='{"margin":"0 2% 0px 0","flexWrap":"wrap","background":"none","display":"block","width":"38%","justifyContent":"space-between","height":"auto","order":"1"}'
      >
        <div class="big" :style='{"border":"0px solid #1abc9e50","margin":"0 0 20px","background":"#fff","width":"calc(100% - 0px)","position":"relative","height":"450px","order":"1"}'>
          <img
            id="big"
            :style='{"objectFit":"contain","top":"0px","left":"0px","background":"#fff","display":"block","width":"100%","position":"absolute","height":"100%","zIndex":"1"}'
            :src="swiperBigUrl"
            class="image"
          />
        </div>
        <div class="samll" :style='{"padding":"0 0px","overflow":"hidden","flexWrap":"wrap","background":"none","display":"flex","width":"100%","justifyContent":"space-between","height":"auto","order":"2"}'>
          <div
            :style='{"border":"0px solid #1abc9e50","width":"23%","margin":"0 0px 10px","position":"relative","background":"#fff","height":"120px"}'
            v-for="item in detailBanner"
            :key="item.id"
          >
            <img
              v-if="item.substr(0,4)=='http'"
              :style='{"cursor":"pointer","objectFit":"cover","top":"0px","left":"0px","display":"block","width":"100%","position":"absolute","height":"100%","zIndex":"1"}'
              :src="item"
              @click="swiperClick3(item)"
              class="image"
            />
            <img
              v-else
              :style='{"cursor":"pointer","objectFit":"cover","top":"0px","left":"0px","display":"block","width":"100%","position":"absolute","height":"100%","zIndex":"1"}'
              :src="baseUrl + item"
              @click="swiperClick3(baseUrl + item)"
              class="image"
            />
          </div>
        </div>
      </div>

      <el-tabs
        class="detail"
        :style='{"border":"0px solid #e9e9e9","boxShadow":"none","margin":"20px auto 40px","background":"#fff","display":"block","width":"100%","order":"40"}'
        v-model="activeName"
        type="border-card"
      >
      </el-tabs>
    </div>

    <div class="share_view" :style='{"boxShadow":"0 1px 6px rgba(0,0,0,.3)","position":"fixed","right":"0","bottom":"20%","background":"#fff","zIndex":"11"}'></div>
  </div>
</template>

<script>
import CountDown from '@/components/CountDown'
import axios from 'axios'
import Swiper from 'swiper'

export default {
  data() {
    return {
      tablename: 'shangjia',
      baseUrl: '',
      breadcrumbItem: [{ name: 'Sellers' }],
      title: '',
      detailBanner: [],
      userid: localStorage.getItem('frontUserid'),
      id: 0,
      detail: {},
      activeName: 'first',
      total: 1,
      pageSize: 5,
      pageSizes: [10, 20, 30, 50],
      totalPage: 1,
      buynumber: 1,
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
  methods: {
    swiperClick3(src) {
      this.swiperBigUrl = src
    },
    init() {
      this.id = this.$route.query.id
      this.baseUrl = this.$config.baseUrl
      this.$http.get(this.tablename + '/detail/' + this.id, {}).then((res) => {
        if (res.data.code == 0) {
          this.detail = res.data.data
          this.title = this.detail.dianpuming
          this.detailBanner = this.detail.touxiang ? this.detail.touxiang.split(',') : []
          this.$forceUpdate()

          if (localStorage.getItem('frontToken')) {
            // reserved for future auth-related fetches
          }
        }
        if (this.detailBanner.length) {
          if (this.detailBanner[0].substr(0, 4) == 'http') {
            this.swiperBigUrl = this.detailBanner[0]
          } else {
            this.swiperBigUrl = this.baseUrl + this.detailBanner[0]
          }
        }
      })
    },
    curChange(page) {},
    prevClick(page) {},
    nextClick(page) {},
    // Back button
    backClick() {
      history.back()
    },
    // Download
    download(file) {
      if (!file) {
        this.$message({
          type: 'error',
          message: 'File not found',
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
            .get(
              (location.href.split(this.$config.name).length > 1 ? location.href.split(this.$config.name)[0] : '') +
                this.$config.name +
                '/file/download?fileName=' +
                arr,
              {
                headers: { token: localStorage.getItem('frontToken') },
                responseType: 'blob'
              }
            )
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

    // Permissions check
    btnAuth(tableName, key) {
      if (this.centerType) {
        return this.isBackAuth(tableName, key)
      } else {
        return this.isAuth(tableName, key)
      }
    },
    // Edit
    editClick() {
      this.$router.push(`/index/shangjiaAdd?type=edit&&id=${this.detail.id}`)
    },
    // Delete
    async delClick() {
      await this.$confirm('Delete this seller?')
        .then((_) => {
          this.$http.post('shangjia/delete', [this.detail.id]).then(async (res) => {
            if (res.data.code == 0) {
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
        })
        .catch((_) => {})
    }
  },
  components: {
    CountDown
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
/* styles unchanged (only text content translated in the template/script) */
.editor /deep/ .avatar-uploader {
  height: 0;
  line-height: 0;
}

.detail-preview {
  .attr {
    .el-carousel /deep/ .el-carousel__indicator button {
      width: 0;
      height: 0;
      display: none;
    }

    .el-input-number__decrease:hover:not(.is-disabled) ~ .el-input .el-input__inner:not(.is-disabled),
    .el-input-number__increase:hover:not(.is-disabled) ~ .el-input .el-input__inner:not(.is-disabled) {
      border-color: none;
    }
  }

  .detail {
    & /deep/ .el-tabs__header .el-tabs__nav-wrap {
      margin-bottom: 0;
    }

    & .add .el-textarea {
      width: auto;
    }
  }
}

.attr .el-carousel /deep/ .el-carousel__container .el-carousel__arrow--left {
  width: 36px;
  font-size: 12px;
  height: 36px;
}
.attr .el-carousel /deep/ .el-carousel__container .el-carousel__arrow--left:hover {
  background: red;
}
.attr .el-carousel /deep/ .el-carousel__container .el-carousel__arrow--right {
  width: 36px;
  font-size: 12px;
  height: 36px;
}
.attr .el-carousel /deep/ .el-carousel__container .el-carousel__arrow--right:hover {
  background: red;
}

.attr .el-carousel /deep/ .el-carousel__indicators {
  padding: 0;
  margin: 0;
  z-index: 2;
  position: absolute;
  list-style: none;
}
.attr .el-carousel /deep/ .el-carousel__indicators li {
  padding: 0;
  margin: 0 4px;
  background: #fff;
  display: inline-block;
  width: 12px;
  opacity: 0.4;
  transition: 0.3s;
  height: 12px;
}
.attr .el-carousel /deep/ .el-carousel__indicators li:hover {
  padding: 0;
  margin: 0 4px;
  background: #fff;
  display: inline-block;
  width: 24px;
  opacity: 0.7;
  height: 12px;
}
.attr .el-carousel /deep/ .el-carousel__indicators li.is-active {
  padding: 0;
  margin: 0 4px;
  background: #fff;
  display: inline-block;
  width: 24px;
  opacity: 1;
  height: 12px;
}

.attr .el-input-number /deep/ .el-input-number__decrease {
  cursor: pointer;
  margin: 0 0 0 -1px;
  display: flex;
  border-color: #dcdfE6;
  border-radius: 0px 0 0 0px;
  background: #fff;
  width: 80px;
  justify-content: center;
  border-width: 1px 1px 1px 0;
  align-items: center;
  position: inherit;
  border-style: solid;
  text-align: center;
  height: 36px;
  order: 3;
}
.attr .el-input-number /deep/ .el-input-number__decrease i {
  color: #000;
  font-size: 14px;
}

.attr .el-input-number /deep/ .el-input-number__increase {
  cursor: pointer;
  margin: 0 -1px 0 0;
  display: flex;
  border-color: #ddd;
  border-radius: 0;
  background: #fff;
  width: 80px;
  justify-content: center;
  border-width: 1px 0 1px 1px;
  align-items: center;
  position: inherit;
  border-style: solid;
  text-align: center;
  height: 36px;
  order: 0;
}
.attr .el-input-number /deep/ .el-input-number__increase i {
  color: #000;
  font-size: 14px;
}

.attr .el-input-number /deep/ .el-input .el-input__inner {
  border: 1px solid #ddd;
  padding: 0;
  margin: 1px 0 0;
  color: #666;
  display: inline-block;
  font-size: 14px;
  line-height: 36px;
  border-radius: 0px;
  outline: none;
  background: #fff;
  width: 100%;
  position: inherit;
  text-align: center;
  height: 36px;
  order: 2;
}

.detail-preview .detail.el-tabs /deep/ .el-tabs__header {
  margin: 0;
  background: #f6f6f6;
  border-color: #e9e9e9;
  border-width: 1px 0 1px 0;
  border-style: solid;
}
.detail-preview .detail.el-tabs /deep/ .el-tabs__header .el-tabs__item {
  border: 0;
  padding: 0 20px;
  margin: 0;
  color: #999;
  font-weight: 500;
  display: inline-block;
  font-size: 16px;
  border-color: #f6f6f6 #e9e9e9;
  line-height: 60px;
  transition: all 0s;
  background: none;
  border-width: 6px 1px 0 0;
  position: relative;
  border-style: solid;
  list-style: none;
  text-align: center;
  min-width: 120px;
  height: 60px;
}
.detail-preview .detail.el-tabs /deep/ .el-tabs__header .el-tabs__item:hover {
  color: #1e88bd;
  background: none;
  border-color: #1e88bd #e9e9e9;
  border-width: 6px 1px 0 0;
  border-style: solid;
}
.detail-preview .detail.el-tabs /deep/ .el-tabs__header .el-tabs__item.is-active {
  border: 0;
  color: #1e88bd;
  background: none;
  font-size: 16px;
  border-color: #1e88bd #e9e9e9;
  border-width: 6px 1px 0 0;
  line-height: 60px;
  border-style: solid;
  text-align: center;
  min-width: 120px;
  height: 60px;
}
.detail-preview .detail.el-tabs /deep/ .el-tabs__content {
  padding: 15px;
}

.breadcrumb-preview .el-breadcrumb /deep/ .el-breadcrumb__separator {
  margin: 0 9px;
  color: #ccc;
  font-weight: 500;
}
.breadcrumb-preview .el-breadcrumb .item1 /deep/ .el-breadcrumb__inner a,
.breadcrumb-preview .el-breadcrumb .item2 /deep/ .el-breadcrumb__inner a,
.breadcrumb-preview .el-breadcrumb .item3 /deep/ .el-breadcrumb__inner a {
  color: #999;
  display: inline-block;
}

/* pagination styles kept as-is ... */

.share_view {
  position: fixed;
  right: 0;
  bottom: 20%;
  background: #fff;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  .share {
    width: 40px;
    height: 40px;
    display: flex;
    align-items: center;
    justify-content: center;
    border-bottom: 1px solid #eee;
    cursor: pointer;
  }
  .share:last-of-type {
    border: none;
  }
}

.detail-preview .el-rate /deep/ .el-rate__item {
  cursor: pointer;
  display: inline-block;
  vertical-align: middle;
  font-size: 0;
  position: relative;
}
.detail-preview .el-rate /deep/ .el-rate__item .el-rate__icon {
  margin: 0 3px;
  display: block;
  font-size: 18px;
  position: relative;
  transition: 0.3s;
}
</style>
