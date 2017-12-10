package com.chao.springboot.system.controller;

import com.chao.springboot.system.bean.SysUser;
import com.chao.springboot.system.service.SysUserService;
import com.chao.springboot.utils.Md5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
@RequestMapping("/admin/sysUserController")
public class SysUserController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping(value = "/saveSysUser",method = RequestMethod.GET)
    @ResponseBody
    public SysUser saveSysUser(){
        log.info("进入方法saveSysUser()........");
        SysUser sysUser = new SysUser();
        sysUser.setUserCode("00013");
        sysUser.setUserName("普通管理员");
        sysUser.setPassword(Md5Util.encode("123456"));
        sysUser.setAddress("中国天帝城");
        sysUser.setAge(18);
        sysUser.setEmail("316448421@qq.com");
        sysUser.setMobile("13750508384");
        sysUser.setMemo("程序新增");
        sysUser.setCreateBy("系统");
        sysUser.setCreateTime(new Date());
        sysUser.setUpdateBy("");
        sysUser.setStatus(0);
        sysUser.setSex(0);
        sysUser.setUpdateTime(new Date());
        return sysUserService.save(sysUser);

    }

    @RequestMapping(value = "/adminIndex",method = RequestMethod.GET)
    public String adminIndex(Model model){
        log.info("进入了首页请求方法adminIndex().......");
        Iterable<SysUser> sysUser = sysUserService.findAll();
        model.addAttribute("sysUsers",sysUser);
        return "index";
    }
    @RequestMapping(value = "/loginIndex",method = RequestMethod.GET)
    public String loginIndex(){
        log.info("进入了转向登录页的方法loginInde()....");

        return "/pc/login";
    }

}
