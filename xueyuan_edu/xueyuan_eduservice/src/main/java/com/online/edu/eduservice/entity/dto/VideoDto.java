package com.online.edu.eduservice.entity.dto;

import lombok.Data;

//小节的dto对象
@Data
public class VideoDto {
    private String id;
    private String title;
    private String videoOriginalName;
    private Boolean free;
}
