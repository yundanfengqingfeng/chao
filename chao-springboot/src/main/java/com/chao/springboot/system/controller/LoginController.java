package com.chao.springboot.system.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author fengchaodexiatian
 * @date 2017/12/11 17:29
 */
@Controller
@RequestMapping("/")
public class LoginController {

    private final Logger log = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
        log.info("进入了转向登录页的方法login()....");

        return "/pc/login";
    }

}
