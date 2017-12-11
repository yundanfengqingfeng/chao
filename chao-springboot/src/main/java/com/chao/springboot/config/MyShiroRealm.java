package com.chao.springboot.config;

import com.chao.springboot.system.bean.SysPermission;
import com.chao.springboot.system.bean.SysRole;
import com.chao.springboot.system.bean.SysUser;
import com.chao.springboot.system.service.SysUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * @author fengchaodexiatian
 * @date 2017/12/11 16:42
 */
public class MyShiroRealm extends AuthorizingRealm {

    private static final Logger log = LoggerFactory.getLogger(MyShiroRealm.class);

    @Resource
    private SysUserService sysUserService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
       log.info("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        SysUser sysUser = (SysUser) principalCollection.getPrimaryPrincipal();
        for(SysRole sysRole : sysUser.getRoleList()) {
            simpleAuthorizationInfo.addRole(sysRole.getRole());
            for(SysPermission p : sysRole.getPermissions()) {
                simpleAuthorizationInfo.addStringPermission(p.getPermission());
            }
        }
       return simpleAuthorizationInfo;
    }

    /*主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.info("进入方法MyShiroRealm.doGetAuthenticationInfo()............");
        //获取用户的输入的账号.
        String userCode = (String)token.getPrincipal();
        log.info("得到token.getCredentials()={}",token.getCredentials());
        //通过userCode从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        SysUser sysUser = sysUserService.findByUserCode(userCode);
        log.info("数据库查询到sysUser={}",sysUser.toString());
        if(null == sysUser) return null;
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(sysUser,sysUser.getPassword(),ByteSource.Util.bytes(sysUser.getCredentialsSalt()),sysUser.getUserName());
        return null;
    }
}
