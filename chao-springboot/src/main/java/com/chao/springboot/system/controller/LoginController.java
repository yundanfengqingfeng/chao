package com.chao.springboot.system.controller;

import com.chao.springboot.system.bean.SysUser;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * @author fengchaodexiatian
 * @date 2017/12/11 17:29
 */
@Controller
@RequestMapping("/")
public class LoginController {

    private final Logger log = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout(HttpServletRequest request){
        log.info("进入了转向登录页的方法logout()....");
        Subject subject = SecurityUtils.getSubject();
        Object object = subject.getPrincipal();
        SysUser sysUser = (SysUser) object ;

        if (subject.isAuthenticated()) {
            subject.logout(); // session 会销毁，在SessionListener监听session销毁，清理权限缓存
            log.info("登录账号："+sysUser.getUsername()+"，退出登录....");
        }
        return "/pc/login";
    }



    @RequestMapping(value = "/login")
    public String login(HttpServletRequest request, Map<String,Object> map,SysUser sysUser){
        log.info("进入了登录判断方法：LoginController.login()");
        // 登录失败从request中获取shiro处理的异常信息。
        // shiroLoginFailure:就是shiro异常类的全类名.
        String exception = (String) request.getAttribute("shiroLoginFailure");
        log.info("exception={}",exception);
        String msg = "";
        if (exception != null) {
            if (UnknownAccountException.class.getName().equals(exception)) {
                log.info("UnknownAccountException -- > 账号不存在：");
                msg = "账号不存在";
            } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
                log.info("IncorrectCredentialsException -- > 密码不正确：");
                msg = "密码不正确";
            } else if ("kaptchaValidateFailed".equals(exception)) {
                log.info("kaptchaValidateFailed -- > 验证码错误");
                msg = "验证码错误";
            } else {
                msg = exception;
                log.info("else -- >" + exception);
            }
        }
        /*String msg = "";
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(sysUser.getUsername(),sysUser.getPassword());
        try {
            subject.login(token);
            msg = "登录成功";
        } catch (IncorrectCredentialsException e) {
            msg =  "密码错误";
        } catch (LockedAccountException e) {
            msg =  "登录失败，该用户已被冻结";
        } catch (AuthenticationException e) {
           msg = "该用户不存在";
        } catch (Exception e) {
            log.error("错误信息：",e);
        }
        map.put("msg", msg);*/
        return "/pc/login";
    }

    @RequestMapping({"/","/index"})
    public String index(){
        SysUser sysUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
        log.info("账号："+sysUser.getUsername()+",登录成功，进入主页...index().....");
        return "/pc/index";
    }

    @RequestMapping("/403")
    public String unauthorizedRole(){
        System.out.println("------没有权限-------");
        return "/403";
    }

}
