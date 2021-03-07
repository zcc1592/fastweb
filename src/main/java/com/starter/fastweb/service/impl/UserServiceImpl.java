package com.starter.fastweb.service.impl;


import com.starter.fastweb.entity.Function;
import com.starter.fastweb.entity.Role;
import com.starter.fastweb.entity.User;
import com.starter.fastweb.entity.UserRole;
import com.starter.fastweb.mapper.UserMapper;
import com.starter.fastweb.service.UserService;
import com.starter.fastweb.utils.MD5Util;
import com.starter.fastweb.utils.SysConstants;
import com.starter.fastweb.utils.UUIDUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @Author: Jack
 * @Time: 2017-02-23 16:47
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Value("${system.config.passwordHashAlgorithm}")
    private String passwordHashAlgorithm;

    @Value("${system.config.passwordHashIterations}")
    private int passwordHashIterations;



    /**
     * 分页查询User
     * @param user
     * @param page
     * @param rows
     * @return
     */
    @Override
    public List<User> queryUser(User user, int page, int rows) {
        List<User> userList = userMapper.selectUser(user, page, rows);
        // TODO 分页查询需要实现
        return userList;
    }

    /**
     * 删除用户
     * @param userAccount
     */
    @Override
    @Transactional
    public void deleteUser(String userAccount) {
        userMapper.deleteUser(userAccount);
        userMapper.deleteUserRole(userAccount);
    }

    /**
     * 添加用户
     * @param user
     */
    @Override
    public void addUser(User user) {
        user.setCreateDate(new Date());
        user.setActive("1");
        String password = SysConstants.DEFAULT_PASSWORD;
        if(StringUtils.isNotBlank(user.getPassword())){
            password = user.getPassword();
        }
//        user.setPassword(MD5Util.md5(password));
        user.setPassword(new SimpleHash(passwordHashAlgorithm,password, ByteSource.Util.bytes(user.getUserAccount()),passwordHashIterations).toString());

        userMapper.insertUser(user);
    }

    /**
     * 根据账号查找用户
     * @param userAccount
     * @return
     */
    @Override
    public User queryUserByAccount(String userAccount) {
        return userMapper.selectUserByAccount(userAccount);
    }

    /**
     * 更新用户
     * @param user
     */
    @Override
    public void updateUser(User user) {
        user.setModifyDate(new Date());
//        if(StringUtils.isNotBlank(user.getPassword())){
//            user.setPassword(MD5Util.md5(user.getPassword()));
//        }else{
//
//        }
        user.setPassword(null);
        userMapper.updateUserSelective(user);
    }

    /**
     * 查找角色
     * @param userAccount
     * @return
     */
    @Override
    public List<Role> queryRoleByUserAccount(String userAccount) {
        return userMapper.selectRoleByUserAccount(userAccount);
    }

    /**
     * 添加用户角色
     */
    @Override
    @Transactional
    public void addUserRole(String userAccount, String[] roleCodes) {
        userMapper.deleteUserRole(userAccount);
        if(roleCodes != null && roleCodes.length > 0){
            for(String roleCode : roleCodes){
                UserRole userRole = new UserRole();
                userRole.setId(UUIDUtils.getUUID());
                userRole.setUserCode(userAccount);
                userRole.setRoleCode(roleCode);
                userMapper.insertUserRole(userRole);
            }

        }


    }

    /**
     * 查询用户
     * @param userAccount
     * @param password
     * @return
     */
    @Override
    public User queryUser(String userAccount, String password) {
        if(StringUtils.isBlank(userAccount) || StringUtils.isBlank(password)){
            return null;
        }
        User user = this.queryUserByAccount(userAccount);
        if(user != null && user.getPassword().equals(MD5Util.md5(password))){
            return user;
        }
        return null;
    }

    /**
     * 查询用户权限
     * @param userAccount
     * @return
     */
    @Override
    public List<Function> queryFunctionList(String userAccount) {
        List<Function> functionList = userMapper.selectFunctionByUserAccount(userAccount);
        return functionList != null ? functionList : new ArrayList<Function>();
    }


}
