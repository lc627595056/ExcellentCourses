package com.online.edu.eduservice.client;


import com.online.edu.common.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("xueyuan-vidservice")  //找到注册中心里面名称是xueyuan-vidservice服务
@Component
public interface VidClient {

    //定义调用的方法
    //方法调用路径
    @DeleteMapping("/vidservice/vod/{videoId}")
    public R removeVideoAliyunId(@PathVariable("videoId") String videoId);

    //定义调用删除多个视频的方法
    @DeleteMapping("/vidservice/vod/removeMoreVideo")
    public R removeMoreVideoAlihyun(@RequestParam("videoList") List videoList);
}
