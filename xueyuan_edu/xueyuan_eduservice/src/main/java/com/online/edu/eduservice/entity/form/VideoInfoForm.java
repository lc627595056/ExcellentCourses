package com.online.edu.eduservice.entity.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

//小节（课时）封装数据的实体类
@Data
public class VideoInfoForm {
    @ApiModelProperty(value = "视频ID")
    private String id;

    @ApiModelProperty(value = "节点名称")
    private String title;

    @ApiModelProperty(value = "课程ID")
    private String courseId;

    @ApiModelProperty(value = "章节ID")
    private String chapterId;

    @ApiModelProperty(value = "云服务器上存储的视频文件名称")
    private String videoOriginalName;

    @ApiModelProperty(value = "视频资源")
//    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String videoSourceId;

    @ApiModelProperty(value = "显示排序")
    private Integer sort;

    @ApiModelProperty(value = "是否可以试听：0默认 1免费")
    private Boolean free;
}
