package com.online.edu.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.online.edu.eduservice.entity.EduCourse;
import com.online.edu.eduservice.entity.EduTeacher;
import com.online.edu.eduservice.entity.query.QueryTeacher;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author liuqc7
 * @since 2021-02-25
 */
public interface EduTeacherService extends IService<EduTeacher> {

    //条件查询带分页
    void pageListCondition(Page<EduTeacher> pageTeacher, QueryTeacher queryTeacher);

    boolean deleteTeacherById(String id);

    //前台分页查询讲师的方法
    Map<String, Object> getFrontTeacherList(Page<EduTeacher> pageTeacher);

    //根据讲师id查询讲师所讲的课程
    List<EduCourse> getCourseListByTeacherId(String id);
}
