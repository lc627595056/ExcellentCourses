import request from '@/utils/request'

export default {
  addCourseInfo(courseInfo) {
    return request({
      url: '/eduservice/course',
      method: 'post',
      data: courseInfo
    })
  },
  // 查询所有的讲师显示到下拉列表
  getAllTeacherList() {
    return request({
      url: '/eduservice/teacher',
      method: 'get'
    })
  },
  // 根据课程id查询课程信息
  getCourseInfoById(id) {
    return request({
      url: '/eduservice/course/getCourseInfo/' + id,
      method: 'get'
    })
  },
  // 修改课程信息的方法
  updateCourseInfoById(id, courseInfo) {
    return request({
      url: '/eduservice/course/updateCourseInfo/' + id,
      method: 'post',
      data: courseInfo
    })
  },
  // 查询所有的课程
  getCourseList() {
    return request({
      url: '/eduservice/course/listCourse',
      method: 'get'
    })
  },
  // 删除课程的方法
  deleteCourseId(id) {
    return request({
      url: '/eduservice/course/deleteCourse/' + id,
      method: 'delete'
    })
  },
  // 根据课程id查询课程详情信息
  getAllCourseInfo(id) {
    return request({
      url: '/eduservice/course/getAllCourseInfo/' + id,
      method: 'get'
    })
  },
  // 根据课程id查询课程详情信息
  updateCourseStatus(id) {
    return request({
      url: '/eduservice/course/publishCourse/' + id,
      method: 'get'
    })
  }
}
