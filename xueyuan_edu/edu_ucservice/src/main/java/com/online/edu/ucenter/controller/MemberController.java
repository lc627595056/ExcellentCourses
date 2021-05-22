package com.online.edu.ucenter.controller;


import com.online.edu.common.R;
import com.online.edu.ucenter.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author liuqc7
 * @since 2021-03-05
 */
@RestController
@RequestMapping("/ucenter/member")
@CrossOrigin
public class MemberController {
    @Autowired
    private MemberService memberService;
    //统计某一天注册人数
    @GetMapping("countRegisterNum/{day}")
    public R countRegisterNum(@PathVariable String day) {
        Integer result = memberService.countRegisterNum(day);
        return R.ok().data("registerCount",result);
    }
}

