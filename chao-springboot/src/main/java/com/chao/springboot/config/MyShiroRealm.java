package com.chao.springboot.config;

import com.chao.springboot.system.bean.SysPermission;
import com.chao.springboot.system.bean.SysRole;
import com.chao.springboot.system.bean.SysUser;
import com.chao.springboot.system.service.SysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * 身份校验核心类;
 * @author fengchaodexiatian
 * @date 2017/12/11 16:42

 */
public class MyShiroRealm extends AuthorizingRealm {

    private static final Logger log = LoggerFactory.getLogger(MyShiroRealm.class);

    @Resource
    private SysUserService sysUserService;

    /**
     * 此方法调用  hasRole,hasPermission的时候才会进行回调.
     * 权限信息.(授权):
     * 1、如果用户正常退出，缓存自动清空；
     * 2、如果用户非正常退出，缓存自动清空；
     * 3、如果我们修改了用户的权限，而用户不退出系统，修改的权限无法立即生效。
     * （需要手动编程进行实现；放在service进行调用）
     * 在权限修改后调用realm中的方法，realm已经由spring管理，所以从spring中获取realm实例，
     * 调用clearCached方法；
     * :Authorization 是授权访问控制，用于对用户进行的操作授权，证明该用户是否允许进行当前操作，如访问某个链接，某个资源文件等。
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
       log.info("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        SysUser sysUser = (SysUser) principalCollection.getPrimaryPrincipal();
        // 将用户对应的角色和权限信息打包放到AuthorizationInfo中
        for(SysRole sysRole : sysUser.getRoleList()) {
            simpleAuthorizationInfo.addRole(sysRole.getRole());
            for(SysPermission p : sysRole.getPermissions()) {
                simpleAuthorizationInfo.addStringPermission(p.getPermission());
            }
        }
       return simpleAuthorizationInfo;
    }

    /**
     * 主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确
     * 认证信息.(身份验证)
     * Authentication 是用来验证用户身份
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.info("进入方法MyShiroRealm.doGetAuthenticationInfo()............");
        //获取用户的输入的账号.
        String username = (String)token.getPrincipal();
        log.info("得到token.getCredentials()={}",token.getCredentials());
        //通过userCode从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        SysUser sysUser = sysUserService.findByUsername(username);
        log.info("数据库查询到sysUser={}",sysUser == null ? "查询不到" : sysUser.toString());
        if(null == sysUser) {
            throw new UnknownAccountException();
        };
        //加密方式;  交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(sysUser,sysUser.getPassword(),ByteSource.Util.bytes(sysUser.getCredentialsSalt()),sysUser.getName());
        return authenticationInfo;
    }
}
