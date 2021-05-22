package com.online.edu.eduservice.handler;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//在服务器启动时候，让这个类读取配置文件内容
@Component
public class ConstantPropertiesUtil implements InitializingBean {

    //服务器启动时候，ConstantPropertiesUtil初始化，调用里面afterPropertiesSet读取配置文件内容
    @Value("${aliyun.oss.file.endpoint}")
    private String endpoint;

    @Value("${aliyun.oss.file.keyid}")
    private String keyid;

    @Value("${aliyun.oss.file.keysecret}")
    private String keySecret;

    @Value("${aliyun.oss.file.bucketname}")
    private String bucketName;

    //读取文件夹名称
    @Value("${aliyun.oss.file.host}")
    private String host;

    //定义常量，为了能够使用
    public static String ENDPOINT;
    public static String KEYID;
    public static String KEYSECRET;
    public static String BUCKETNAME;
    public static String HOST;

    @Override
    public void afterPropertiesSet() throws Exception {
        ENDPOINT = endpoint;
        KEYID = keyid;
        KEYSECRET = keySecret;
        BUCKETNAME = bucketName;
        HOST = host;
    }
}
