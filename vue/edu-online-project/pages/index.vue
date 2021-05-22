<template>

  <div>
   <!-- 幻灯片 开始 -->
  <div v-swiper:mySwiper="swiperOption">
      <div class="swiper-wrapper">
          <div class="swiper-slide" style="background: #040B1B;">
              <a target="_blank" href="/">
                  <img src="~/assets/photo/banner/1525939573202.jpg" alt="首页banner">
              </a>
          </div>
          <div class="swiper-slide" style="background: #040B1B;">
              <a target="_blank" href="/">
                  <img src="~/assets/photo/banner/153525d0ef15459596.jpg" alt="首页banner">
              </a>
          </div>
      </div>
      <div class="swiper-pagination swiper-pagination-white"></div>
      <div class="swiper-button-prev swiper-button-white" slot="button-prev"></div>
      <div class="swiper-button-next swiper-button-white" slot="button-next"></div>
  </div>
  <!-- 幻灯片 结束 -->

     <div id="aCoursesList">
      <!-- 网校课程 开始 -->
      <div>
        <section class="container">
          <header class="comm-title">
            <h2 class="tac">
              <span class="c-333">    </span>
            </h2>
          </header>
        </section>
      </div>
      <!-- /网校课程 结束 -->
      <!-- 网校名师 开始 -->

      <!-- /网校名师 结束 -->
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      swiperOption: {
        //配置分页
        pagination: {
          el: '.swiper-pagination'//分页的dom节点
        },
        //配置导航
        navigation: {
          nextEl: '.swiper-button-next',//下一页dom节点
          prevEl: '.swiper-button-prev'//前一页dom节点
        }
      }
    }
  }
}
</script>
