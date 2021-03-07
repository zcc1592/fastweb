package com.starter.fastweb.configuration;

import com.starter.fastweb.shiro.CustomRealm;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * shiro config
 */
@Configuration
public class ShiroConfig {

    @Value("${system.config.passwordHashAlgorithm}")
    private String passwordHashAlgorithm;

    @Value("${system.config.passwordHashIterations}")
    private int passwordHashIterations;

    /**
     * 自定义realm
     * @return
     */
    @Bean("customRealm")
    public CustomRealm getRealm(){
        CustomRealm customRealm = new CustomRealm();
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName(passwordHashAlgorithm);
        credentialsMatcher.setHashIterations(passwordHashIterations);
        customRealm.setCredentialsMatcher(credentialsMatcher);
        return customRealm;
    }

    /**
     * 配置安全管理器
     * @return
     */
    @Bean("securityManager")
    public SecurityManager getSecurityManager(@Qualifier("customRealm") CustomRealm customRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(customRealm);
        return securityManager;
    }

    /**
     * 配置ShiroFilterFactoryBean
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, String> filterMap = new HashMap<>();
        /**
         * shiro常用的内置过滤器：
         *  1. anon: 无需认证（登录）即可访问
         *  2. authc: 必须认证才可以访问
         *  3. user: 如果使用rememberMe功能可以直接访问
         *  4. perms: 必须得到资源权限才可以访问
         *  5. roles: 必须得到角色权限才可以访问
         */
        // 登录请求不需要拦截
//        filterMap.put("/auth", "anon");
//        filterMap.put("/*", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        shiroFilterFactoryBean.setLoginUrl("/login");
        return shiroFilterFactoryBean;
    }







}
