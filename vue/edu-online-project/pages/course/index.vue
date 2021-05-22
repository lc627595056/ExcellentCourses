<template>
  <div id="aCoursesList" class="bg-fa of">
    <!-- /课程列表 开始 -->
    <section class="container">
      <header class="comm-title">
        <h2 class="fl tac">
          <span class="c-333">全部课程</span>

<!--          <form :inline="true" class="demo-form-inline">-->
<!--            <form-item>-->
<!--              <input v-model="searchObj1.title" placeholder="课程名"/>-->
<!--            </form-item>-->

<!--            <button type="primary" icon="el-icon-search" @click="getListCourse()">查询</button>-->
<!--            <button type="default" @click="resetData()">清空</button>-->
<!--          </form>-->
        </h2>
      </header>



      <section class="c-sort-box">

        <div class="js-wrap">
          <section class="fr">
            <span class="c-ccc">
              <i class="c-master f-fM">1</i>/
              <i class="c-666 f-fM">1</i>
            </span>
          </section>
          <section class="fl">
            <ol class="js-tap clearfix">
              <li>
                <a title="最新" href="#">最新</a>
              </li>
            </ol>
          </section>
        </div>
        <div class="mt40">
          <!-- /无数据提示 开始-->
          <section class="no-data-wrap" v-if="data.total==0">
            <em class="icon30 no-data-ico">&nbsp;</em>
            <span class="c-666 fsize14 ml10 vam">没有相关数据，小编正在努力整理中...</span>
          </section>
          <!-- /无数据提示 结束-->
          <article v-if="data.total>0" class="comm-course-list">
            <ul class="of" id="bna">
              <li v-for="(item,index) in data.items" :key="index">
                <div class="cc-l-wrap">
                  <section class="course-img">
                    <img :src="item.cover" class="img-responsive" alt="">
                    <div class="cc-mask">
                      <a :href="'/course/'+item.id" title="开始学习" class="comm-btn c-btn-1">开始学习</a>
                    </div>
                  </section>
                  <h3 class="hLh30 txtOf mt10">
                    <a :href="'/course/'+item.id" :title="item.title" class="course-title fsize18 c-333">{{item.title}}</a>
                  </h3>
                  <section class="mt10 hLh20 of">
                    <span class="fr jgTag bg-green">
                      <i class="c-fff fsize12 f-fA">免费</i>
                    </span>
                    <span class="fl jgAttr c-ccc f-fA">
                      <i class="c-999 f-fA">9634人学习</i>
                      |
                      <i class="c-999 f-fA">9634评论</i>
                    </span>
                  </section>
                </div>
              </li>

            </ul>
            <div class="clear"></div>
          </article>
        </div>
        <!-- 公共分页 开始 -->
        <div>
            <div class="paging">
                <!-- undisable这个class是否存在，取决于数据属性hasPrevious -->
                <a
                  :class="{undisable: !data.hasPrevious}"
                  href="#"
                  title="首页"
                  @click.prevent="gotoPage(1)">首</a>
                <a
                  :class="{undisable: !data.hasPrevious}"
                  href="#"
                  title="前一页"
                  @click.prevent="gotoPage(data.current-1)">&lt;</a>
                <a
                  v-for="page in data.pages"
                  :key="page"
                  :class="{current: data.current == page, undisable: data.current == page}"
                  :title="'第'+page+'页'"
                  href="#"
                  @click.prevent="gotoPage(page)">{{ page }}</a>
                <a
                  :class="{undisable: !data.hasNext}"
                  href="#"
                  title="后一页"
                  @click.prevent="gotoPage(data.current+1)">&gt;</a>
                <a
                  :class="{undisable: !data.hasNext}"
                  href="#"
                  title="末页"
                  @click.prevent="gotoPage(data.pages)">末</a>
                <div class="clear"/>
            </div>
        </div>
        <!-- /公共分页 结束 -->
      </section>
    </section>
    <!-- /课程列表 结束 -->
  </div>
</template>
<script>
import course from "@/api/course"

export default {
    // 定义变量和初始值
    data() {
        return {
            listLoading: true, // 是否显示loading信息
            list: null, // 每页数据list集合
            total: 0, // 总记录数
            page: 1, // 当前页
            limit: 4, // 每页显示记录数
            searchObj1: {}// 条件封装对象
        }
    },
  asyncData({ params, error }) {
    return course.getCourseListPage(1,8)
      .then(response => {
        return {data: response.data.data}
      })

  },
  methods: {
      // 查询课程方法
      getListCourse(page = 1) {
          this.page = page
          this.listLoading = true
          course.getCoursePageList(this.page, this.limit, this.searchObj1)
              .then(response => { // 如果请求成功，返回状态码20000，执行then里面操作
                  // console.log(response)
                  // 每页数据
                  this.list = response.data.items
                  // 总记录数
                  this.total = response.data.total

                  this.listLoading = false
              })
              .catch(response => { // 如果请求失败，执行catch里面操作
                  console.log(response)
              })
      },
    gotoPage(page){
        course.getCourseListPage(page, 8).then(response => {
            this.data = response.data.data
        })
    }
}

};
</script>
