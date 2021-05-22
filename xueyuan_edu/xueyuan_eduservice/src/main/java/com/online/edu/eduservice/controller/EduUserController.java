package com.online.edu.eduservice.controller;


import com.online.edu.common.R;
import com.online.edu.eduservice.entity.EduUser;
import com.online.edu.eduservice.service.EduUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liuqc7
 * @since 2021-03-10
 */
@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin
public class EduUserController {
    //注入service
    @Autowired
    private EduUserService eduUserService;

    @GetMapping
    public R getAllUserList() {
        //调用service的方法
        List<EduUser> list = eduUserService.list(null);
        return R.ok().data("items",list);
    }


    //6 根据id查询讲师
    @GetMapping("getUserInfo/{username},{password}")
    public R getUserInfo(@PathVariable String username,@PathVariable String password) {

        EduUser eduUser = eduUserService.getByUser(username,password);

        if (null != eduUser){
            return R.ok().data("success",eduUser);
        }else {
            return R.error().data("fail",eduUser);
        }
//        return R.ok().data("eduUser",eduUser);
    }


//    //模拟登陆
//    @PostMapping("login/{username},{password}")
//    public R login(@PathVariable String username,@PathVariable String password) {
//        EduUser eduUser = eduUserService.getByUser(username,password);
//
//        if (null != eduUser){
//            return R.ok().data("token",eduUser);
//        }else {
//            return R.error().data("fail",eduUser);
//        }
//
////        return R.ok().data("token","admin");
//    }
//
//    //{"code":20000,"data":{"roles":["admin"],"name":"admin","avatar":"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif"}}
//    @GetMapping("info/{username},{password}")
//    public R info(@PathVariable String username,@PathVariable String password) {
//        EduUser eduUser = eduUserService.getByUser(username,password);
//
//        if (null != eduUser){
//            return R.ok().data("roles",eduUser.getUsername()).data("name",eduUser.getPassword()).data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
//
////            return R.ok().data("token",eduUser);
//        }else {
//            return R.error().data("fail",eduUser);
//        }
//
////        return R.ok().data("roles","[admin]").data("name","admin").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
//    }
}

