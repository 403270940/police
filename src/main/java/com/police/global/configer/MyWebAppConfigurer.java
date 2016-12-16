package com.police.global.configer;

import com.police.global.AdminAuthorityInterceptor;
import com.police.global.AuthorityInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by zhaomn on 16/5/10.
 */
@Configuration
public class MyWebAppConfigurer extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorityInterceptor()).addPathPatterns("/api/**")
                .excludePathPatterns("/api/register", "/api/getcaptcha",
                        "/api/checkphone", "/api/forgotpassword", "/api/login", "/api/announcement","/api/forum/theme/summary","/api/forum/theme/detail",
                        "/admin/login","/api/adminlogin");

        registry.addInterceptor(adminAuthorityInterceptor()).addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/login","/admin/api/login");
    }

    @Bean
    public AuthorityInterceptor authorityInterceptor(){
        return new AuthorityInterceptor();
    }

    @Bean
    public AdminAuthorityInterceptor adminAuthorityInterceptor(){
        return new AdminAuthorityInterceptor();
    }
}
