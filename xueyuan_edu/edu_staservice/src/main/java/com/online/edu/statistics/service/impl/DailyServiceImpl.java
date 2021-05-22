package com.online.edu.statistics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.online.edu.common.R;
import com.online.edu.statistics.UcenterClient;
import com.online.edu.statistics.entity.Daily;
import com.online.edu.statistics.mapper.DailyMapper;
import com.online.edu.statistics.service.DailyService;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author liuqc7
 * @since 2021-03-05
 */
@Service
public class DailyServiceImpl extends ServiceImpl<DailyMapper, Daily> implements DailyService {
    @Autowired
    private UcenterClient ucenterClient;

    @Override
    public void getCountRegisterNum(String day) {

        //判断再进行添加统计分析表里面是否存在添加的天数的记录，如果存在删除，
        QueryWrapper<Daily> wrapper = new QueryWrapper<>();
        wrapper.eq("date_calculated",day);
        baseMapper.delete(wrapper);

        R r = ucenterClient.countRegisterNum(day);
        Integer registerCount = (Integer)r.getData().get("registerCount");
        //把获取数据添加到统计分析表里面
        Integer courseNum = RandomUtils.nextInt(100, 200);//TODO
        Integer videoViewNum = RandomUtils.nextInt(100, 200);
        Integer loginNum = RandomUtils.nextInt(100, 200);

        Daily daily =new Daily();
        daily.setDateCalculated(day);
        daily.setRegisterNum(registerCount);
        daily.setCourseNum(courseNum);
        daily.setLoginNum(loginNum);
        daily.setVideoViewNum(videoViewNum);

        baseMapper.insert(daily);
    }


    //根据时间范围查询统计数据
    @Override
    public Map<String, Object> getDataCount(String type, String begin, String end) {
        //构建查询条件
        QueryWrapper<Daily> wrapper = new QueryWrapper<>();
        //大于等于开始时间，小于等于结束时间
//        wrapper.ge("date_calculated",begin);
//        wrapper.le("date_calculated",end);
        //between and方式构建
        wrapper.between("date_calculated",begin,end);
        //查询指定的字段
        //时间和具体查询因子，前端传递type就是字段名字
        wrapper.select("date_calculated",type);

        //调用方法根据条件得到结果
        List<Daily> dailyList = baseMapper.selectList(wrapper);

        //把查询出来list集合转换map集合
        //第一部分时间：['2019-01-02','2019-01-19']
        //第二部分数量：[5,3]
        //遍历list集合
        //创建集合用于存储所有的时间
        List<String> timeList = new ArrayList<>();
        //创建集合用于存储所有的数据
        List<Integer> dataList = new ArrayList<>();

        for (int i = 0; i < dailyList.size(); i++) {
            //得到每个daily对象
            Daily daily = dailyList.get(i);
            //1 构建所有的时间list集合
            //从每个daily对象获取时间
            String dateCalculated = daily.getDateCalculated();
            //放到list集合中
            timeList.add(dateCalculated);

            //2 构建所有数据的list集合
            //从daily对象获取查询数据
            //因为获取哪个字段数据不一定的，所以根据type进行判断，type是数据字段名称
            switch (type) {
                case "login_num":
                    dataList.add(daily.getLoginNum());
                    break;
                case "register_num":
                    dataList.add(daily.getRegisterNum());
                    break;
                case "video_view_num":
                    dataList.add(daily.getVideoViewNum());
                    break;
                case "course_num":
                    dataList.add(daily.getCourseNum());
                    break;
                default:
                    break;
            }
        }

        //把构建出来两个list集合放到map集合中
        Map<String,Object> map = new HashMap<>();
        map.put("timeList",timeList);
        map.put("dataList",dataList);

        return map;
    }
}
