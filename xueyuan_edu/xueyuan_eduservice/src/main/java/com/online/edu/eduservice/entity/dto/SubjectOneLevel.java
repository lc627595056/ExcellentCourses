package com.online.edu.eduservice.entity.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

//一级分类
@Data
public class SubjectOneLevel {

    private String id;
    private String title;
    //一个一级分类里面有多个二级分类
    private List<SubjectTwoLevel> children = new ArrayList<>();

}
