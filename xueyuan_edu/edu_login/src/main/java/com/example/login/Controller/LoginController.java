package com.example.login.Controller;

import com.example.login.Service.UserService;
import com.example.login.pojo.Result;
import com.example.login.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;

@RestController
public class LoginController {
    @Autowired
    UserService userService;
    @CrossOrigin
    @PostMapping(value = "api/login")
    @ResponseBody
    public Result login (@RequestBody User requestUser,HttpSession session){
        String username = requestUser.getUsername();
        username = HtmlUtils.htmlEscape(username);

        User user = userService.get(username, requestUser.getPassword());
        if(null == user){
            System.out.println("账号密码错误");
            return new Result(400);
        }else {
            return new Result(200);
        }
    }


}
