package com.police.controller;

import com.police.model.BaseResponse;
import com.police.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by liyy on 16/10/24.
 */
@Controller
@RequestMapping("/api")
public class LoginController {
    @Autowired
    LoginService loginService;

    @RequestMapping(value = "login",method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse login(@RequestParam(value="phone",defaultValue = "")String phone,
                              @RequestParam(value="password",defaultValue = "")String password){
        return loginService.login(phone, password);
    }


    @RequestMapping(value = "logout",method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse login(@RequestParam(value="uid",defaultValue = "")int uid){
        return loginService.logout(uid);
    }
}
