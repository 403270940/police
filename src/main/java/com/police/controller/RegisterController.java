package com.police.controller;

import com.police.model.BaseResponse;
import com.police.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by liyy on 16/10/20.
 */
@Controller
@RequestMapping("/api")
public class RegisterController {
    @Autowired
    RegisterService registerService;

    @RequestMapping(value = "getcaptcha",method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse getCaptcha(@RequestParam(value = "phone") String phone){
        BaseResponse baseResponse = registerService.getCaptcha(phone);
        return baseResponse;
    }

    @RequestMapping(value = "checkphone",method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse checkPhoneExists(@RequestParam(value = "phone") String phone){
        boolean exists = registerService.existPhone(phone);
        if(exists)
            return new BaseResponse(1,"请求的手机号码存在",null);
        return new BaseResponse(0,"请求的手机号码不存在",null);
    }


    @RequestMapping(value = "register",method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse register(@RequestParam(value = "phone") String phone,
                                 @RequestParam(value = "password") String password,
                                 @RequestParam(value = "captcha") String captcha,
                                 @RequestParam(value = "captchaId") int captchaId
                                 ){
        return registerService.register(phone,password,captcha,captchaId);
    }

    @RequestMapping(value = "resetpassword",method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse resetpassword(@RequestParam(value = "uid") int uid,
                                      @RequestParam(value = "oldpassword") String password,
                                     @RequestParam(value = "newpassword") String newpassword){
        return registerService.resetpassword(uid, password, newpassword);
    }

    @RequestMapping(value = "forgotpassword",method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse forgotpassword(@RequestParam(value = "phone") String phone,
                                       @RequestParam(value = "password") String password,
                                       @RequestParam(value = "captcha") String captcha,
                                       @RequestParam(value = "captchaId") int captchaId){
        return registerService.forgotpassword(phone, password, captcha, captchaId);
    }
}

