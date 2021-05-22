<template>
  <div class="app-container">
    课程列表

    <!-- 表格 -->
    <el-table
      v-loading="listLoading"
      :data="list"
      element-loading-text="数据加载中"
      border
      fit
      highlight-current-row>

      <el-table-column
        label="序号"
        width="100"
        align="center">
        <template slot-scope="scope">
          {{ scope.$index + 1 }}
        </template>
      </el-table-column>

      <el-table-column prop="title" label="名称" width="350"/>

      <el-table-column prop="price" label="价格" width="200"/>

      <el-table-column prop="lessonNum" label="课时数" width="200"/>

      <el-table-column prop="gmtCreate" label="创建时间" width="200"/>

      <el-table-column label="操作" width="250" align="center">
        <template slot-scope="scope">
          <!--          <router-link :to="'/course/edit/'+scope.row.id">-->
          <!--            <el-button type="primary" size="mini" icon="el-icon-edit">修改</el-button>-->
          <!--          </router-link>-->
          <router-link :to="'/course/info/'+scope.row.id">
            <el-button type="primary" size="mini" icon="el-icon-edit">修改</el-button>
          </router-link>

          <!--          <el-button @click="previous">修改1</el-button>-->

          <el-button type="danger" size="mini" icon="el-icon-delete" @click="removeDataById(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>

    </el-table>

  </div>
</template>

<script>
import course from '@/api/course'

export default {
  // 定义变量和初始值
  data() {
    return {
      listLoading: true, // 是否显示loading信息
      list: null, // 每页数据list集合
      total: 0, // 总记录数
      page: 1, // 当前页
      limit: 10, // 每页显示记录数
      searchObj: {}// 条件封装对象
    }
  },
  // 在页面渲染之前调用，调用具体的某个方法
  created() {
    // 调用方法
    this.getListCourse()
  },
  // 写具体的方法调用
  methods: {
    // 删除课程的方法
    removeDataById(id) {
      this.$confirm('此操作将永久删除该课程, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        // 调用方法进行删除
        // return 表示后面then还会执行
        return course.deleteCourseId(id)
      }).then(() => {
        // 刷新整个页面
        this.getListCourse()
        this.$message({
          type: 'success',
          message: '删除成功!'
        })
      }).catch(() => {
        // 判断点击取消，还是删除失败
        if (response === 'cancel') {
          this.$message({
            type: 'info',
            message: '已取消删除'
          })
        } else {
          this.$message({
            type: 'error',
            message: '删除失败'
          })
        }
      })
    },
    // 1 讲师列表
    getListCourse() {
      course.getCourseList()
        .then(response => { // 如果请求成功，返回状态码20000，执行then里面操作
          // console.log(response)
          // 每页数据
          this.list = response.data.items

          this.listLoading = false
        })
        .catch(response => { // 如果请求失败，执行catch里面操作
          console.log(response)
        })
    },
    previous() {
      this.$router.push({ path: '/course/info/' + this.id })
    }
  }
}
</script>
