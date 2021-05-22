package com.online.edu.eduservice.entity.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

//章节
@Data
public class EduChapterDto {

    private String id;
    private String title;
    //一个章节有很多小节
    private List<EduVideoDto> children = new ArrayList<>();
}
