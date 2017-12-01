package com.chao.springboot.config;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by feng-pc on 2017/11/5.
 * 1、新建一个通用异常类；
 * 2、在calss上面注解，@ControllerAdvice；
 * 3、在class中添加方法；
 * 4、在方法上面添加注解ExceptionHandler拦截相对应的异常信息；
 * 5、如果返回的是view,方法的返回值是ModelAndView;
 * 6、如果返回的是String 或者Json 数据，那么需要在方法上面添加@ResponseBody注解；
 */

@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String defaultExceptionHandler(HttpServletRequest request, Exception e){

        log.info("进入了错误拦截方法defaultExceptionHandler().................");

        return "对不起，服务正忙，请稍后再尝试!";
    }
}
