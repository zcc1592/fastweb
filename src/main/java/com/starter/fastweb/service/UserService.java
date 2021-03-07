package com.starter.fastweb.service;


import com.starter.fastweb.entity.Function;
import com.starter.fastweb.entity.Role;
import com.starter.fastweb.entity.User;

import java.util.List;

/**
 * @Author: Jack
 * @Time: 2017-02-23 16:47
 */
public interface UserService {

    /**
     * 分页查询用户
     * @param user
     * @param page
     * @param rows
     * @return
     */
    List<User> queryUser(User user, int page, int rows);

    /**
     * 删除用户
     * @param userAccount
     * @return
     */
    void deleteUser(String userAccount);

    /**
     * 添加用户
     * @param user
     */
    void addUser(User user);

    /**
     * 根据账号查找用户
     * @param userAccount
     * @return
     */
    User queryUserByAccount(String userAccount);

    /**
     * 更新用户
     * @param user
     */
    void updateUser(User user);


    /**
     * 查找角色
     * @param userAccount
     * @return
     */
    List<Role> queryRoleByUserAccount(String userAccount);

    /**
     * 添加用户角色
     */
    void addUserRole(String userAccount, String[] roleCodes);

    /**
     * 查询用户
     * @param userAccount
     * @param password
     * @return
     */
    User queryUser(String userAccount, String password);

    /**
     * 查询用户功能权限
     * @param userAccount
     * @return
     */
    List<Function> queryFunctionList(String userAccount);


}
