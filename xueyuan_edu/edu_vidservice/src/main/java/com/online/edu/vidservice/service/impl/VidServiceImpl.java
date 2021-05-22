package com.online.edu.vidservice.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import com.online.edu.vidservice.service.VidService;
import com.online.edu.vidservice.utils.AliyunVodSDKUtils;
import com.online.edu.vidservice.utils.ConstantPropertiesUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class VidServiceImpl implements VidService {

    @Override
    public String uploadVideoAlyun(MultipartFile file) {
        try {
            //获取上传文件名称
            //视频名称.mp4
            String fileName = file.getOriginalFilename();
            String title = fileName.substring(0,fileName.lastIndexOf("."));

            UploadStreamRequest request = new UploadStreamRequest(ConstantPropertiesUtil.ACCESS_KEY_ID, ConstantPropertiesUtil.ACCESS_KEY_SECRET,
                    title, fileName, file.getInputStream());
            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);
            String videoId = null;
            if (response.isSuccess()) {
                videoId = response.getVideoId();
            } else { //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
                videoId = response.getVideoId();
            }
            return videoId;
        }catch(Exception e) {
            return null;
        }
    }

    //删除阿里云视频
    @Override
    public void deleteAliyunVideoId(String videoId) {
        try {
            //初始化操作
            DefaultAcsClient client =
                    AliyunVodSDKUtils.initVodClient(ConstantPropertiesUtil.ACCESS_KEY_ID, ConstantPropertiesUtil.ACCESS_KEY_SECRET);
            //创建删除视频请求对象
            DeleteVideoRequest request = new DeleteVideoRequest();
            //设置删除视频id
            request.setVideoIds(videoId);
            //调用方法实现删除
            DeleteVideoResponse response = client.getAcsResponse(request);
        }catch(Exception e) {

        }
    }

    //删除多个视频的方法
    @Override
    public void deleteVideoMore(List videoList) {
        try {
            //初始化操作
            DefaultAcsClient client =
                    AliyunVodSDKUtils.initVodClient(ConstantPropertiesUtil.ACCESS_KEY_ID, ConstantPropertiesUtil.ACCESS_KEY_SECRET);
            //创建删除视频请求对象
            DeleteVideoRequest request = new DeleteVideoRequest();
            //设置删除多个视频id
            //videoList集合中多个视频id传递到删除的方法里面
            //把list集合变成 1,2,3
            //join方法里面第一个参数是数组
            //第二个参数 根据什么内容进行分割 ,
            String videoIds = StringUtils.join(videoList.toArray(), ",");
            //多个视频id使用,隔开 1,2,3
            request.setVideoIds(videoIds);
            //调用方法实现删除
            DeleteVideoResponse response = client.getAcsResponse(request);
        }catch(Exception e) {

        }
    }
    public static void main(String[] args) {
//        String str = "视频名称1111.mp4";
//        String name = str.substring(0,str.lastIndexOf("."));
//        System.out.println(name);

        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");

        //1,2,3
        String join = StringUtils.join(list.toArray(), ",");
        System.out.println(join);
    }
}
