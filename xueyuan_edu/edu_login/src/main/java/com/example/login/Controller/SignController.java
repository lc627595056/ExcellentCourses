package com.example.login.Controller;

import com.example.login.Service.UserService;
import com.example.login.pojo.Result;
import com.example.login.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SignController {
    @Autowired
    UserService userService;
    @CrossOrigin
    @PostMapping(value = "api/signIn")
    @ResponseBody
    public Result sign (@RequestBody User requestBody){

        String username = requestBody.getUsername();
        if (userService.isExist(username) == false){
            userService.register(requestBody);
            return new Result(200,"注册成功");
        }else {
            return new Result(401,"用户名已存在");
        }
    }
}
