package com.online.edu.eduservice.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.online.edu.common.R;
import com.online.edu.eduservice.entity.EduCourse;
import com.online.edu.eduservice.entity.EduTeacher;
import com.online.edu.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

//前台讲师controller
@RestController
@RequestMapping("/eduservice/frontTeacher")
@CrossOrigin
public class FrontEduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    //查询讲师详情信息
    @GetMapping("{id}")
    public R getTeacherInfoCourseId(@PathVariable String id) {
        //1 根据讲师id查询讲师详情信息，返回对象
        EduTeacher eduTeacher = eduTeacherService.getById(id);

        //2 查询讲师所讲的课程，返回list集合
        List<EduCourse> courseList = eduTeacherService.getCourseListByTeacherId(id);

        return R.ok().data("eduTeacher",eduTeacher).data("courseList",courseList);
    }

    //讲师列表的方法
    @GetMapping("{page}/{limit}")
    public R getFrontTeacherListPage(@PathVariable Long page,
                                     @PathVariable Long limit) {
        //实现分页查询
        Page<EduTeacher> pageTeacher = new Page<>(page,limit);
        //调用service的实现分页
        Map<String,Object> map = eduTeacherService.getFrontTeacherList(pageTeacher);
        return R.ok().data(map);
    }
}
