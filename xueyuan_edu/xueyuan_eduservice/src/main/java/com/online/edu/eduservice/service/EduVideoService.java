package com.online.edu.eduservice.service;

import com.online.edu.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author liuqc7
 * @since 2021-03-03
 */
public interface EduVideoService extends IService<EduVideo> {
    //根据课程id删除小节
    void deleteVideoByCourseId(String id);
    //删除小节
    boolean removeVideo(String videoId);

}
