package com.police.service;

import com.police.model.LogEvent;
import com.police.dao.UserRepository;
import com.police.model.BaseResponse;
import com.police.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liyy on 16/10/20.
 */
@Service
public class LoginService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    LogEventService logEventService;

    public BaseResponse login(String phone,String password){
        BaseResponse baseResponse;
        User user = userRepository.findByPhone(phone);
        if(user != null && user.getPassword().equals(password)){
            String token = getMD5(phone + password + System.currentTimeMillis());
            Map<String,Object> responseBody = new HashMap<String, Object>();
            responseBody.put("token",token);
            responseBody.put("user_id",user.getId());
            logEventService.recieve(new LogEvent(phone,"登录成功"));
            baseResponse = new BaseResponse(0,"登录成功!",responseBody);
        }else{
            baseResponse = new BaseResponse(4001,"账号密码不匹配!",null);
        }
        return baseResponse;
    }

    public static String getMD5(String str) {
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
//            throw new SpeedException("MD5加密出现错误");
            return "";
        }
    }
}
