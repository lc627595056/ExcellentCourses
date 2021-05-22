package com.online.edu.eduservice.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.online.edu.common.R;
import com.online.edu.eduservice.entity.EduCourse;
import com.online.edu.eduservice.entity.dto.EduChapterDto;
import com.online.edu.eduservice.entity.dto.TeacherAllInfoDto;
import com.online.edu.eduservice.service.EduChapterService;
import com.online.edu.eduservice.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

//前台课程controller
@RestController
@RequestMapping("/eduservice/frontCourse")
@CrossOrigin
public class FrontEduCourseController {

    @Autowired
    private EduCourseService eduCourseService;

    @Autowired
    private EduChapterService eduChapterService;

    //课程分页列表的方法
    @GetMapping("{page}/{limit}")
    public R getCourseListPage(@PathVariable Long page,
                               @PathVariable Long limit) {
        Page<EduCourse> pageCourse = new Page<>(page,limit);
        Map<String,Object> map = eduCourseService.listCoursePage(pageCourse);
        return R.ok().data(map);
    }

    //根据课程id查询课程详情信息
    @GetMapping("{id}")
    public R getCourseInfoAll(@PathVariable String id) {
        //1 根据课程id查询信息，包含课程基本信息，课程描述，讲师信息，分类信息
        TeacherAllInfoDto courseInfoDto = eduCourseService.getTeacherAllInfo(id);

        //2 根据课程id查询课程里面所有的章节，章节里面所有小节
        List<EduChapterDto> chapterVideoList = eduChapterService.getChapterVideoListByCourseId(id);

        return R.ok().data("courseInfo",courseInfoDto).data("chapterVideoList",chapterVideoList);
    }
}
