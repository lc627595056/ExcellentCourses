package com.online.edu.eduservice.entity.query;

import lombok.Data;

//用于封装条件值
@Data
public class QueryTeacher {

    private String name;
    private String level;
    private String begin;//开始时间
    private String end;//结束时间
}
