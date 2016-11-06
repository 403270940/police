package com.police.global;

import com.alibaba.fastjson.JSON;
import com.police.dao.LogInfoRepository;
import com.police.model.BaseResponse;
import com.police.model.LogInfo;
import com.police.service.LoginService;
import com.police.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by liyy on 16/11/5.
 */
public class AuthorityInterceptor implements HandlerInterceptor{
    @Autowired
    TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        System.out.println(">>>AuthorityInterceptor>>>>>>>在请求处理之前进行调用（Controller方法调用之前）");
        String token = request.getHeader("User-Token");
        boolean exists = tokenService.exists(token);
        if(!exists)
            response.getWriter().write(JSON.toJSONString(new BaseResponse(-1,"请登录",null)));
        return exists;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }
}
