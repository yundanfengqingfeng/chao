package com.chao.springboot.common.utils;

import com.chao.springboot.common.constants.SystemConstants;
import com.chao.springboot.system.bean.SysUser;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @author fengchaodexiatian
 * 注册时候的密码加密
 * @date 2018/1/5 10:05
 */
public class PasswordHelper {

    private static RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

    public static void encryptPassword(SysUser user){
        // User对象包含最基本的字段Username和Password
        user.setSalt(randomNumberGenerator.nextBytes().toHex());
        // 将用户的注册密码经过散列算法替换成一个不可逆的新密码保存进数据，散列过程使用了盐
        String newPassword = new SimpleHash(SystemConstants.HASH_ALGORITHM,user.getPassword(), ByteSource.Util.bytes(user.getCredentialsSalt()),SystemConstants.HASH_TIMES).toHex();
        user.setPassword(newPassword);
    }

}
