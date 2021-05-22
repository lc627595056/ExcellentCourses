package com.online.edu.eduservice.service;

import com.online.edu.eduservice.entity.EduCourseDescription;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程简介 服务类
 * </p>
 *
 * @author liuqc7
 * @since 2021-03-02
 */
public interface EduCourseDescriptionService extends IService<EduCourseDescription> {
    //根据课程id删除描述
    void deleteDescriptionByCourseId(String id);
}
