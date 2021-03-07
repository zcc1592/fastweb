package com.starter.fastweb.mapper;


import com.starter.fastweb.entity.Function;
import com.starter.fastweb.entity.Role;
import com.starter.fastweb.entity.User;
import com.starter.fastweb.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

//@Mapper
public interface UserMapper {
    /**
     * 分页查询用户
     * @param user
     * @param page
     * @param rows
     * @return
     */
    List<User> selectUser(@Param("user") User user, int page, int rows);

    /**
     * 根据账号查找用户
     * @param userAccount
     * @return
     */
    User selectUserByAccount(@Param("userAccount") String userAccount);

    /**
     * 插入用户
     * @param user
     * @return
     */
    int insertUser(User user);

    /**
     * 插入用户与角色的关联关系
     * @return
     */
    int insertUserRole(UserRole userRole);

    /**
     * 更新用户
     * @param user
     * @return
     */
    int updateUserSelective(User user);

    /**
     * 删除用户
     * @param userAccount
     * @return
     */
    int deleteUser(String userAccount);

    /**
     * 删除用户与角色的关联关系
     * @param userAccount
     * @return
     */
    int deleteUserRole(String userAccount);

    /**
     * 查询角色
     * @param userAccount
     * @return
     */
    List<Role> selectRoleByUserAccount(String userAccount);

    /**
     * 查询用户拥有的功能
     * @param userAccount
     * @return
     */
    List<Function> selectFunctionByUserAccount(String userAccount);
}
