package com.online.edu.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.online.edu.eduservice.entity.EduCourse;
import com.online.edu.eduservice.entity.EduCourseDescription;
import com.online.edu.eduservice.entity.dto.CourseInfoDto;
import com.online.edu.eduservice.entity.dto.TeacherAllInfoDto;
import com.online.edu.eduservice.entity.form.CourseInfoForm;
import com.online.edu.eduservice.entity.query.QueryCourse;
import com.online.edu.eduservice.handler.EduException;
import com.online.edu.eduservice.mapper.EduCourseMapper;
import com.online.edu.eduservice.service.EduChapterService;
import com.online.edu.eduservice.service.EduCourseDescriptionService;
import com.online.edu.eduservice.service.EduCourseService;
import com.online.edu.eduservice.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author liuqc7
 * @since 2021-03-01
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {
    //课程描述
    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;

    //课程章节
    @Autowired
    private EduChapterService eduChapterService;

    //课程小节
    @Autowired
    private EduVideoService eduVideoService;
    //添加课程信息
    @Override
    public String insertCourseInfo(CourseInfoForm courseInfoForm) {
        //1 课程基本信息到课程表
        //courseInfoForm数据复制到EduCourse对象里面，进行添加
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoForm,eduCourse);
        int result = baseMapper.insert(eduCourse);
        //判断如果添加课程信息成功，添加描述
        if(result == 0) {//失败
            //抛出异常
            throw new EduException(20001,"添加课程信息失败");
        }

        //2 课程描述添加到课程描述表
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        //获取描述信息
        String description = courseInfoForm.getDescription();
        eduCourseDescription.setDescription(description);
        //课程id
        String courseId = eduCourse.getId();
        eduCourseDescription.setId(courseId);

        boolean save = eduCourseDescriptionService.save(eduCourseDescription);
        if(save) {
            return courseId;
        }else {
            return null;
        }
    }

    //根据id查询课程信息
    @Override
    public CourseInfoForm getIdCourse(String id) {
        //查询两张表
        //1 根据id查询课程基本信息表
        EduCourse eduCourse = baseMapper.selectById(id);
        if(eduCourse == null) {
            //没有课程信息
            throw new EduException(20001,"没有课程信息");
        }
        CourseInfoForm courseInfoForm = new CourseInfoForm();
        BeanUtils.copyProperties(eduCourse,courseInfoForm);
        //到上一行代码时候，courseInfoForm对象里面已经有课程基本信息，没有描述信息

        //2 根据id查询课程描述表
        EduCourseDescription eduCourseDescription = eduCourseDescriptionService.getById(id);
        String description = eduCourseDescription.getDescription();
        courseInfoForm.setDescription(description);
        //返回封装之后对象
        return courseInfoForm;
    }

    //修改课程信息
    @Override
    public Boolean updateCourse(CourseInfoForm courseInfoForm) {

        //1 修改课程基本信息表
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoForm,eduCourse);
        int result = baseMapper.updateById(eduCourse);
        if(result == 0) {
            throw new EduException(20001,"修改分类失败");
        }

        //2 修改描述表
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        String id = courseInfoForm.getId();
        String description = courseInfoForm.getDescription();
        eduCourseDescription.setId(id);
        eduCourseDescription.setDescription(description);
        boolean b = eduCourseDescriptionService.updateById(eduCourseDescription);
        return b;
    }

    //根据课程id删除课程
    @Override
    public boolean removeCourseId(String id) {
        //1 根据课程id删除章节
        eduChapterService.deleteChapterByCourseId(id);

        //2 根据课程id删除小节
        eduVideoService.deleteVideoByCourseId(id);

        //3 根据课程id删除课程描述
        eduCourseDescriptionService.deleteDescriptionByCourseId(id);

        //4 删除课程本身
        int result = baseMapper.deleteById(id);
        return result>0;
    }

    //根据课程id查询课程详情
    @Override
    public CourseInfoDto getCourseInfoAll(String courseId) {
        CourseInfoDto courseInfoAll = baseMapper.getCourseInfoById(courseId);
        return courseInfoAll;
    }

    //前台系统课程分页列表
    @Override
    public Map<String, Object> listCoursePage(Page<EduCourse> pageCourse) {
        baseMapper.selectPage(pageCourse,null);

        List<EduCourse> records = pageCourse.getRecords();//每页数据
        long total = pageCourse.getTotal();//总记录数
        long size = pageCourse.getSize();//每页显示记录数
        long pages = pageCourse.getPages();//总页数
        long current = pageCourse.getCurrent();//当前页
        boolean hasNext = pageCourse.hasNext();//是否有下一页
        boolean hasPrevious = pageCourse.hasPrevious();//是否有上一页

        //把分页数据放到map集合里面
        Map<String,Object> map = new HashMap<>();
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);
        return map;
    }

    //前台根据课程id查询课程详情
    @Override
    public TeacherAllInfoDto getTeacherAllInfo(String id) {
        return baseMapper.getTeacherAllInfo(id);
    }

    //条件查询带分页
    @Override
    public void pageListConditionCourse(Page<EduCourse> pageCourse, QueryCourse queryCourse) {

        //关键：queryTeacher有传递过来的条件值，判断，如果有条件值，拼接条件
        if(queryCourse == null) {
            //直接查询分页，不进行条件操作
            baseMapper.selectPage(pageCourse,null);
            return;
        }
        //如果queryTeacher不为空
        //从queryTeacher对象里面获取出条件值
        String title = queryCourse.getTitle();

        //判断条件值是否有，如果有拼接条件
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(title)) {
            //拼接条件
            wrapper.like("title",title);
        }

        //条件查询带分页
        baseMapper.selectPage(pageCourse,wrapper);
    }


}
