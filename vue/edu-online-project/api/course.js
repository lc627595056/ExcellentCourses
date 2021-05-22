import request from '@/utils/request'

export default {
  // 分页条件查询的方法
  // 三个参数：当前页，每页记录数，条件封装对象
  getCoursePageList(page, limit, searchObj1) {
    return request({
      // 后端controller里面的路径
      url: '/eduservice/course/moreCondtionPageListCourse/' + page + '/' + limit,
      // 提交方式
      method: 'post',
      // 传递条件对象,如果传递json数据，使用data。如果不是json，使用params
      data: searchObj1
    })
  },
    //课程列表的方法
    getCourseListPage(page,limit) {
        return request({
            url: '/eduservice/frontCourse/'+page+'/'+limit,
            method: 'get'
        })
    },
    //根据课程id查询课程详情
    getCourseInfoId(id) {
        return request({
            url: '/eduservice/frontCourse/'+id,
            method: 'get'
        })
    }
}
