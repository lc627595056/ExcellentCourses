package com.online.edu.eduservice.entity.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

//创建章节dto对象
@Data
public class ChapterDto {

    private String id;//章节id
    private String title;//章节名称
    //很多的小节
    private List<VideoDto> children = new ArrayList<>();

}
