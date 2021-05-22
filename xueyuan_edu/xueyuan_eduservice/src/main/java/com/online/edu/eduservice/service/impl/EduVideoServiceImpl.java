package com.online.edu.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.online.edu.eduservice.client.VidClient;
import com.online.edu.eduservice.entity.EduVideo;
import com.online.edu.eduservice.mapper.EduVideoMapper;
import com.online.edu.eduservice.service.EduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author liuqc7
 * @since 2021-03-03
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {
    //注入调用vid的接口
    @Autowired
    private VidClient vidClient;

    //根据课程id删除小节
    @Override
    public void deleteVideoByCourseId(String id) {

        //把课程里面所有的视频都进行删除
        //1 获取课程里面所有的视频id
        //根据课程id查询课程所有的小节中视频id
        QueryWrapper<EduVideo> wrapperVideo = new QueryWrapper<>();
        wrapperVideo.eq("course_id",id);
        //只是查询视频id字段，其他字段不查询
        wrapperVideo.select("video_source_id");
        List<EduVideo> eduVideos = baseMapper.selectList(wrapperVideo);

        //把查询出来集合里面视频id值获取出来，构建新list集合
        List<String> videoIdList = new ArrayList<>();
        for (int i = 0; i < eduVideos.size(); i++) {
            //获取每个小节
            EduVideo eduVideo = eduVideos.get(i);
            //每个小节获取视频id
            String videoSourceId = eduVideo.getVideoSourceId();
            //放到list集合
            videoIdList.add(videoSourceId);
        }

        //2 调用方法进行删除
        vidClient.removeMoreVideoAlihyun(videoIdList);

        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",id);
        baseMapper.delete(wrapper);
    }

    //删除小节的方法
    @Override
    public boolean removeVideo(String xiaojie) {
        //根据小节id查询数据库获取视频id
        EduVideo eduVideo = baseMapper.selectById(xiaojie);
        String videoSourceId = eduVideo.getVideoSourceId();
        //如果视频id存在，删除
        if(!StringUtils.isEmpty(videoSourceId)) {
            //调用方法，根据视频id删除
            vidClient.removeVideoAliyunId(videoSourceId);
        }

        int result = baseMapper.deleteById(xiaojie);
        return result>0;
    }
}
