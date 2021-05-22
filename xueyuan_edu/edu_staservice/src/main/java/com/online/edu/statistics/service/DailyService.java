package com.online.edu.statistics.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.online.edu.statistics.entity.Daily;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author liuqc7
 * @since 2021-03-05
 */
public interface DailyService extends IService<Daily> {
    void getCountRegisterNum(String day);

    Map<String, Object> getDataCount(String type, String begin, String end);
}
