package com.police.global;

import com.alibaba.fastjson.JSON;
import com.police.model.AdminToken;
import com.police.model.BaseResponse;
import com.police.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by liyy on 16/11/5.
 */
public class AdminAuthorityInterceptor implements HandlerInterceptor{
    @Autowired
    TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        System.out.println(">>>AuthorityInterceptor>>>>>>>在请求处理之前进行调用（Controller方法调用之前）");
        String token = null;

        Cookie[] cookies = request.getCookies();
        if(cookies == null){
            response.setHeader("Location", "/admin/login");
            response.setStatus(302);
            return false;
        }
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("User-Token")){
                token = cookie.getValue();
            }
        }
        boolean exists = tokenService.existsAdminToken(token);
        if(!exists){
            response.setHeader("Location","/admin/login");
            response.setStatus(302);
        }
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
