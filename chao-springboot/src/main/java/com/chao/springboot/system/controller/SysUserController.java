package com.chao.springboot.system.controller;

import com.alibaba.fastjson.JSON;
import com.chao.springboot.common.utils.PasswordHelper;
import com.chao.springboot.system.bean.SysUser;
import com.chao.springboot.system.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin/sysUserController")
public class SysUserController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping(value = "/registerSysUser",method = RequestMethod.GET)
    @ResponseBody
    public SysUser registerSysUser(){
        log.info("进入方法registerSysUser()........");
        SysUser sysUser = new SysUser();
        sysUser.setUsername("00000");
        sysUser.setName("超级管理员");
        sysUser.setPassword("123456");
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
        PasswordHelper.encryptPassword(sysUser);
        return sysUserService.save(sysUser);

    }

    @RequestMapping(value = "/adminIndex",method = RequestMethod.GET)
    public String adminIndex(Model model){
        log.info("进入了首页请求方法adminIndex().......");
        Iterable<SysUser> sysUser = sysUserService.findAll();
        model.addAttribute("sysUsers",sysUser);
        return "/pc/general";
    }

    @RequestMapping(value = "/loginIndex",method = RequestMethod.GET)
    public String loginIndex(){
        log.info("进入了转向登录页的方法loginInde()....");

        return "/pc/login";
    }


    @RequestMapping(value = "/showSysUser")
    public Iterable<SysUser> sysUserList(){

        Iterable<SysUser> sysUser = sysUserService.findAll();
        log.info("输出：{}", JSON.toJSONString(sysUser));
        return sysUser;
    }







}
