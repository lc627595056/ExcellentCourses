package com.online.edu.statistics;

import com.online.edu.common.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("xueyuan-ucservice")
@Component
public interface UcenterClient {

    //得到某一天注册人数的方法定义
    @GetMapping("/ucenter/member/countRegisterNum/{day}")
    public R countRegisterNum(@PathVariable("day") String day);
}
