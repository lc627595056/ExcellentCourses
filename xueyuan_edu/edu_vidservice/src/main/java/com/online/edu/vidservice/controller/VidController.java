package com.online.edu.vidservice.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.online.edu.common.R;
import com.online.edu.vidservice.service.VidService;
import com.online.edu.vidservice.utils.AliyunVodSDKUtils;
import com.online.edu.vidservice.utils.ConstantPropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/vidservice/vod")
@CrossOrigin
public class VidController {

    @Autowired
    private VidService vidService;

    //根据视频id获取播放凭证
    @GetMapping("getPalyAuth/{vid}")
    public R getPlayAutoId(@PathVariable String vid) {

        try {
            //初始化客户端、请求对象和响应对象
            DefaultAcsClient client = AliyunVodSDKUtils.initVodClient(ConstantPropertiesUtil.ACCESS_KEY_ID,
                    ConstantPropertiesUtil.ACCESS_KEY_SECRET);
            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
            GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();
            //设置请求参数
            request.setVideoId(vid);
            //获取请求响应
            response = client.getAcsResponse(request);

            //输出请求结果
            //播放凭证
            String playAuth = response.getPlayAuth();
            return R.ok().data("playAuth",playAuth);
        } catch (Exception e) {
            return R.error();
        }
    }

    //删除阿里云多个视频
    //参数是多个视频id的list集合
    @DeleteMapping("removeMoreVideo")
    public R deleteMoreVideo(@RequestParam("videoList") List videoList) {
        vidService.deleteVideoMore(videoList);
        return R.ok();
    }

    //实现删除云端视频的方法
    @DeleteMapping("{videoId}")
    public R deleteVideoIdAliyun(@PathVariable String videoId) {
        vidService.deleteAliyunVideoId(videoId);
        return R.ok();
    }

    //实现上传视频到阿里云服务器的方法
    @PostMapping("upload")
    public R uploadAliyunVideo(@RequestParam("file") MultipartFile file) {
        //调用方法实现视频上传，返回上传之后视频id
        String videoId = vidService.uploadVideoAlyun(file);
        return R.ok().data("videoId",videoId);
    }
}
