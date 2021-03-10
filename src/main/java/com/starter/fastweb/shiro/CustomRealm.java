package com.starter.fastweb.shiro;

import com.starter.fastweb.entity.User;
import com.starter.fastweb.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 用户认证及授权
 */
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;


    /**
     * 用户授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }


    /**
     * 用户认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 账号密码认证
        if(authenticationToken instanceof UsernamePasswordToken){
            // 用户账号
            String userAccount = (String)authenticationToken.getPrincipal();
            User user = userService.queryUserByAccount(userAccount);
            // 若用户不存在，返回null，shiro后续会抛出UnknownAccountException，表示用户不存在
            if(user == null){
                return null;
            }
            return new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes(user.getUserAccount()), getName());
        }
        return null;
    }
}
