package com.online.edu.eduservice.entity.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CoursePublishDto implements Serializable {

    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price;//只用于显示
}
