package com.police.controller;

import com.police.model.BaseResponse;
import com.police.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by liyy on 16/10/24.
 */
@Controller
@RequestMapping("")
public class LoginController {
    @Autowired
    LoginService loginService;

    @RequestMapping(value = "api/login",method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse login(@RequestParam(value="phone",defaultValue = "")String phone,
                              @RequestParam(value="password",defaultValue = "")String password){
        return loginService.login(phone, password);
    }


    @RequestMapping(value = "api/logout",method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse logout(@RequestParam(value="uid",defaultValue = "")int uid,
                                @RequestHeader(value="User-Token") String user_token){

        return loginService.logout(uid,user_token);
    }
}
