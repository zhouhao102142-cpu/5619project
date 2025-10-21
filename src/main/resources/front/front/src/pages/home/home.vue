<template>
  <div
    class="home-preview"
    :style='{"margin":"0px auto","flexWrap":"wrap","flexDirection":"row","background":"#fff","display":"flex","width":"100%","justifyContent":"center"}'
  >

    <!-- Book Recommendations -->
    <div
      id="animate_recommendshangpinxinxi"
      class="recommend animate__animated"
      :style='{"padding":"60px 0 60px","margin":"40px auto 20px","borderColor":"#ddd","background":"#f1f1f1","borderWidth":"0px 0","width":"100%","position":"relative","borderStyle":"solid","order":"2"}'
    >
      <div
        class="title"
        :style='{"width":"76%","padding":"45px 0 0","margin":"0px auto","textAlign":"left","background":"url() no-repeat left top / auto 42px","height":"120px"}'
      >
        <span :style='{"padding":"0","fontSize":"32px","color":"#000","fontWeight":"600","display":"block"}'>
          Recommended Books
        </span>
      </div>

      <div
        v-if="shangpinxinxiRecommend.length"
        class="list list16 index-pv1"
      >
        <div
          v-for="(item,index) in shangpinxinxiRecommend"
          :key="index"
          @click="toDetail('shangpinxinxiDetail', item)"
          class="list-item animation-box"
        >
          <div class="img-box">
            <img
              class="image"
              v-if="preHttp(item.fengmian)"
              :src="(item.fengmian || '').split(',')[0]"
              alt=""
            />
            <img
              class="image"
              v-else
              :src="baseUrl + ((item.fengmian || '').split(',')[0] || '')"
              alt=""
            />
          </div>

          <div class="item-info">
            <div class="title">{{ item.shangpinmingcheng }}</div>
            <div class="title">{{ item.dianpuming }}</div>

            <div class="meta-row">
              <div class="publisher">
                <span class="icon iconfont icon-geren16"></span>
                <span class="text">{{ item.shangjiazhanghao }}</span>
              </div>
              <div class="like">
                <span class="icon iconfont icon-zan10"></span>
                <span class="text">{{ item.thumbsupnum }}</span>
              </div>
              <div class="collect">
                <span class="icon iconfont icon-shoucang10"></span>
                <span class="text">{{ item.storeupnum }}</span>
              </div>
              <div class="view">
                <span class="icon iconfont icon-chakan9"></span>
                <span class="text">{{ item.clicknum }}</span>
              </div>
            </div>

            <div class="desc" v-html="item.shangpinjieshao"></div>
          </div>
        </div>
      </div>

      <!-- View More -->
      <div
        @click="moreBtn('shangpinxinxi')"
        :style='{"border":"0px solid #fff","cursor":"pointer","padding":"0 20px 0 0","margin":"90px -12px 0 0","textAlign":"right","display":"block","right":"12%","top":"0","background":"url(http://codegen.caihongy.cn/20231010/b345185c76ac4a77bf4cd6f6171a72ed.png) no-repeat center top / 100% 100%","width":"240px","lineHeight":"73px","position":"absolute","height":"80px"}'
      >
        <span :style='{"color":"#333","fontSize":"14px"}'>View More</span>
        <i :style='{"color":"#333","fontSize":"16px","display":"none"}' class="icon iconfont icon-jiantou09"></i>
      </div>
    </div>
    <!-- /Book Recommendations -->

  </div>
</template>

<script>
import 'animate.css'
import Swiper from 'swiper' // 保留依赖，便于后续扩展

export default {
  data() {
    return {
      baseUrl: '',
      shangpinxinxiRecommend: []
    }
  },
  created() {
    this.baseUrl = this.$config.baseUrl
    this.getList()
  },
  mounted() {
    window.addEventListener('scroll', this.handleScroll)
    setTimeout(() => this.handleScroll(), 100)
    this.swiperChanges()
  },
  beforeDestroy() {
    window.removeEventListener('scroll', this.handleScroll)
  },
  methods: {
    swiperChanges() {
      // 占位：若后续需要轮播，这里可初始化
      setTimeout(() => {}, 750)
    },
    handleScroll() {
      const targets = [{ id: 'animate_recommendshangpinxinxi', css: 'animate__' }]
      for (const t of targets) {
        const el = document.getElementById(t.id)
        if (el) {
          const top = el.offsetTop
          const winTop = window.innerHeight + window.pageYOffset
          if (winTop > top && el.classList.value.indexOf(t.css) < 0) {
            el.classList.add(t.css)
          }
        }
      }
    },
    preHttp(str) {
      return str && str.substr(0, 4) === 'http'
    },
    getList() {
      // books
      let autoSortUrl = 'shangpinxinxi/autoSort'
      if (localStorage.getItem('frontToken')) autoSortUrl = 'shangpinxinxi/autoSort2'
      this.$http
        .get(autoSortUrl, { params: { page: 1, limit: 8 } })
        .then(res => {
          if (res.data.code === 0) this.shangpinxinxiRecommend = res.data.data.list
        })
    },
    toDetail(path, item) {
      this.$router.push({ path: '/index/' + path, query: { id: item.id } })
    },
    moreBtn(path) {
      this.$router.push({ path: '/index/' + path })
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
/* ===== Keep your original search/input styles if存在（保留结构占位） ===== */
.home-preview {
  /* 可保留你的其它样式，这里主要修正列表对齐 */
}

/* ===== Fix alignment: use CSS Grid + fixed image area + line clamp ===== */
.list16 {
  display: grid !important;
  grid-template-columns: repeat(4, 1fr);
  gap: 24px;
  padding: 0 12%;
}

.list16 .list-item {
  display: flex;
  flex-direction: column;
  background: #fff;
  box-shadow: 1px 2px 9px #ccc;
  border-radius: 4px;
  overflow: hidden;
  min-height: 420px; /* 图片区 + 文本区统一高度感 */
}

/* 图片区域固定高度，图片等比裁切覆盖 */
.list16 .img-box {
  width: 100%;
  height: 240px;     /* 如需更高可改 300/320，与视觉取舍相关 */
  overflow: hidden;
  flex: 0 0 auto;
  background: #f5f5f5;
}
.list16 .img-box .image {
  width: 100%;
  height: 100%;
  object-fit: cover;
  display: block;
}

/* 文本区域填满剩余空间，内部内容裁剪，确保卡片等高 */
.list16 .item-info {
  flex: 1 1 auto;
  display: flex;
  flex-direction: column;
  padding: 10px 10px 12px;
}

/* 标题：两行裁剪，避免撑高 */
.list16 .item-info .title {
  width: 100%;
  margin: 0 0 6px 0 !important;
  font-size: 14px;
  color: #333;
  line-height: 1.4;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  overflow: hidden;
}

/* meta 行横向紧凑排列 */
.list16 .item-info .meta-row {
  margin-top: 2px;
}
.list16 .item-info .publisher,
.list16 .item-info .like,
.list16 .item-info .collect,
.list16 .item-info .view {
  display: inline-flex !important;
  align-items: center;
  margin-right: 8px;
  color: #666;
  line-height: 20px !important;
  font-size: 12px;
}

/* 介绍：最多 3 行 */
.list16 .item-info .desc {
  margin-top: 8px;
  color: #777;
  font-size: 13px;
  line-height: 1.5;
  display: -webkit-box;
  -webkit-line-clamp: 3;
  -webkit-box-orient: vertical;
  overflow: hidden;
}

/* 悬停动效（延续你原有风格） */
.list16 .list-item.animation-box {
  transform: translateZ(0);
  transition: transform .25s ease, box-shadow .25s ease;
}
.list16 .list-item.animation-box:hover {
  transform: scale(1.02);
  box-shadow: 2px 6px 18px rgba(0,0,0,.12);
}

/* 响应式列数 */
@media (max-width: 1200px) {
  .list16 { grid-template-columns: repeat(3, 1fr); }
}
@media (max-width: 900px) {
  .list16 { grid-template-columns: repeat(2, 1fr); }
}
@media (max-width: 540px) {
  .list16 { grid-template-columns: 1fr; }
}
</style>
