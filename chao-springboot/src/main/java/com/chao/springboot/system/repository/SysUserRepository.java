package com.chao.springboot.system.repository;

import com.chao.springboot.system.bean.SysUser;
import org.springframework.data.repository.CrudRepository;

public interface SysUserRepository extends CrudRepository<SysUser,Integer> {

}
