package com.online.edu.common;

//定义返回数据使用的状态码
public interface ResultCode {

    int SUCCESS = 20000; //成功状态码
    int ERROR = 20001;//失败状态码
    int AUTH = 30000;//没有操作权限状态码
}
