package com.online.edu.eduservice.entity;

import lombok.Data;

//用于条件查询封装条件值的对象
@Data
public class QueryTeacher {

    private String name;//姓名
    private Integer level;//头衔
    private String begin;//开始时间
    private String end;//结束时间

}
