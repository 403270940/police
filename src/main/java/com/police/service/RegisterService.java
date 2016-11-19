package com.police.service;

import com.police.dao.CaptchaRepository;
import com.police.model.Captcha;
import com.police.model.LogEvent;
import com.police.dao.UserRepository;
import com.police.model.BaseResponse;
import com.police.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by liyy on 16/10/20.
 */
@Service
public class RegisterService {

    @Autowired
    UserRepository registerRepository;
    @Autowired
    CaptchaRepository captchaRepository;
    @Autowired
    LogEventService logEventService;

    public boolean existPhone(String phone){
        User user = registerRepository.findByPhone(phone);
        return user != null;
    }

    public BaseResponse resetpassword(int uid,String oldPassword,String newPassword){
        User user = registerRepository.findById(uid);
        if(oldPassword.equals(user.getPassword())){
            user.setPassword(newPassword);
            registerRepository.save(user);
            return new BaseResponse(0,"修改成功");
        }
        return new BaseResponse(4101,"原始密码错误");
    }

    public BaseResponse forgotpassword(String phone,String password,String captcha,int captchaId){
        Captcha captcha1 = captchaRepository.findOne(captchaId);
        if(captcha1 == null || !captcha1.getCaptcha().equals(captcha)){
            return new BaseResponse(-1,"验证码错误!");
        }
        User user = registerRepository.findByPhone(phone);
        user.setPassword(password);
        registerRepository.save(user);
        captchaRepository.delete(captcha1);
        return new BaseResponse(0,"修改密码成功");
    }

    public BaseResponse getCaptcha(String phone){
        StringBuilder sb = new StringBuilder();
        sb.append("123456");
        Captcha captcha = new Captcha(phone,sb.toString());
        captchaRepository.save(captcha);
        Map<String,Object> resultMap = new HashMap<String, Object>();
        resultMap.put("captchaId",captcha.getId());
        resultMap.put("captcha",captcha.getCaptcha());
        return new BaseResponse(0,"",resultMap);
    }

    public BaseResponse register(String phone,String password,String captcha,int captchaId){
        Captcha captcha1 = captchaRepository.findOne(captchaId);
        if(captcha1 == null || !captcha1.getCaptcha().equals(captcha)){
            return new BaseResponse(-1,"验证码错误!");
        }
        boolean existPhone = existPhone(phone);
        if(existPhone){
            BaseResponse baseResponse = new BaseResponse(-1,"手机号已被占用!","");
            return  baseResponse;
        }
        User user = new User(phone,password);
        registerRepository.save(user);
        captchaRepository.delete(captcha1);
        Map<String,String> responseData = new HashMap<String, String>();
        BaseResponse baseResponse = new BaseResponse(0,"注册成功!",responseData);
        logEventService.recieve(new LogEvent(phone, "注册成功"));
        return baseResponse;
    }
}
