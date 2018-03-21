package com.hawksoft.platform.controller;

import com.hawksoft.platform.entity.User;
import com.hawksoft.platform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    private Map<String,Object> params = new HashMap<>();

    /**
     * 验证用户登录信息
     * @param uname         用户名
     * @param password      密码
     * @return
     */
    @RequestMapping("login/{uname}/{password}")
    @ResponseBody
    public String login(@PathVariable("uname") String uname,
                         @PathVariable("password") String password){

        params.put("uname",uname);
        params.put("password",password);
        return (userService.findUser(params) != null) ? "success" : "fail";
    }

    /**
     * 注册用户
     * @param user 用户
     * @return
     */
    @RequestMapping("register")
    @ResponseBody
    public String register(User user){
        if(userService.findUserByName(user.getUname()) == null){
            if(userService.saveUser(user) != 0){
                return "success";
            }
        }
        return "fail";
    }

    /**
     * 检测用户名是否被使用
     * @param uname  用户名
     * @return  存在则返回false
     */
    @RequestMapping("checkUname/{uname}")
    @ResponseBody
    public String checkUnameUseful(@PathVariable("uname") String uname){

        return (userService.findUserByName(uname) != null)?"false" : "true";
    }
}
