package com.chao.springboot.system.service;

import com.chao.springboot.system.bean.SysUser;

public interface SysUserService {

    SysUser save(SysUser sysUser);

    SysUser findOne(int id);

    SysUser findByUserCode(String userCode);

    boolean exists(int id);

    Iterable<SysUser> findAll();

    long count();

    void delete(int id);

    void delete(SysUser sysUser);

    void deleteAll();

}
