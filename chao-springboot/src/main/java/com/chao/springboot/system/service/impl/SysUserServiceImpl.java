package com.chao.springboot.system.service.impl;

import com.chao.springboot.system.bean.SysUser;
import com.chao.springboot.system.repository.SysUserRepository;
import com.chao.springboot.system.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SysUserServiceImpl implements SysUserService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Resource
    private SysUserRepository sysUserRepository;

    @Override
    public SysUser save(SysUser sysUser) {
        return sysUserRepository.save(sysUser);
    }

    @Override
    public SysUser findOne(int id) {
        return sysUserRepository.findOne(id);
    }

    @Override
    public SysUser findByUsername(String username) {
        log.info("进入了SysUserService.findByUsername().....username={}",username);
        return sysUserRepository.findByUsername(username);
    }

    @Override
    public SysUser findByUserCodeAndPassword(String userCode, String password) {
        log.info("进入了findByUserCodeAndPassword通过账号与密码查找用户的方法findByUserCodeAndPassword()...");
        return null;
    }

    @Override
    public boolean exists(int id) {
        return sysUserRepository.exists(id);
    }

    @Override
    public Iterable<SysUser> findAll() {
        return sysUserRepository.findAll();
    }

    @Override
    public long count() {
        return sysUserRepository.count();
    }

    @Override
    public void delete(int id) {
        sysUserRepository.delete(id);
    }

    @Override
    public void delete(SysUser sysUser) {
        sysUserRepository.delete(sysUser);
    }

    @Override
    public void deleteAll() {
        sysUserRepository.deleteAll();
    }

}
