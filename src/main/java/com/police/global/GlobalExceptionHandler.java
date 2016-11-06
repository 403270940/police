package com.police.global;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by liyy on 16/10/26.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    @ExceptionHandler(value=Exception.class)
    @ResponseBody
    public String errorHandler(HttpServletRequest request,Exception e){
        LOGGER.error("",e);
        return e.getMessage();
    }
}
