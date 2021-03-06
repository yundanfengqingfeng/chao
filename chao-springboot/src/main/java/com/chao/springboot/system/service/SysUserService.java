package com.chao.springboot.system.service;

import com.chao.springboot.system.bean.SysUser;

public interface SysUserService {

    SysUser save(SysUser sysUser);

    SysUser findOne(int id);

    SysUser findByUsername(String username);

    SysUser findByUserCodeAndPassword(String userCode,String password);

    boolean exists(int id);

    Iterable<SysUser> findAll();

    long count();

    void delete(int id);

    void delete(SysUser sysUser);

    void deleteAll();

}
