package com.online.edu.statistics.controller;


import com.online.edu.common.R;
import com.online.edu.statistics.service.DailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author liuqc7
 * @since 2021-03-05
 */
@RestController
@RequestMapping("/statistics/daily")
@CrossOrigin
public class DailyController {
    @Autowired
    private DailyService dailyService;

    //获取某一天用户注册人数
    @GetMapping("getStatisticsDay/{day}")
    public R getStatisticsDay(@PathVariable String day) {
        dailyService.getCountRegisterNum(day);
        return R.ok();
    }

    //返回图表显示使用的数据
    //第一部分时间：['2019-01-02','2019-01-19']
    //第二部分数量：[5,3]
    //第一个参数查询因子（查询什么数据，比如注册人数）
    //第二个参数开始时间，第三个参数结束时间
    @GetMapping("getCountData/{type}/{begin}/{end}")
    public R getDataCount(@PathVariable String type,
                          @PathVariable String begin,
                          @PathVariable String end) {
        //调用service查询需要的数据，返回
        Map<String,Object> map = dailyService.getDataCount(type,begin,end);
        return R.ok().data("mapData",map);
    }
}

