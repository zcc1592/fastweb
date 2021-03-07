package com.starter.fastweb.service;



import com.starter.fastweb.entity.*;

import java.util.List;

/**
 * @Author: Jack
 * @Time: 2016-02-29 13:38
 */
public interface SystemService {
    /**
     * 新增功能权限
     * @param function
     * @return
     */
    int addFunction(Function function);

    /**
     * 根据functionCode查找Function
     * @param functionCode
     * @return
     */
    Function queryFunctionByCode(String functionCode);

    /**
     * 删除Function
     * @param functionCode
     * @return
     */
    int removeFunctionByCode(String functionCode);

    /**
     * 根据functionCode 递归查询所有子Function
     * @param functionCode
     * @return
     */
    List<Function> queryAllChildFunctionByCode(String functionCode);

    /**
     * 根据functionCode 修改Function
     * @param function
     * @return
     */
    int updateFunctionByCode(Function function);

    /**
     * 加载功能权限树
     * @param functionCode
     * @return
     */
    FunctionNode loadFunctionTree(String functionCode, String roleCode);

    /**
     * 查询function
     * @param function
     * @return
     */
    List<Function> queryFunction(Function function);

    /**
     * 查找角色
     * @param role
     * @param page
     * @param rows
     * @return
     */
    List<Role> queryRole(Role role, int page, int rows);

    /**
     * 查找角色
     * @param role
     * @return
     */
    List<Role> queryRole(Role role);

    /**
     * 删除角色
     * @param roleCode
     * @return
     */
    void deleteRole(String roleCode);

    /**
     * 查询角色
     * @param roleCode
     * @return
     */
    Role queryRoleByCode(String roleCode);

    /**
     * 添加角色
     * @param role
     */
    void addRole(Role role);

    /**
     * 更新角色
     * @param role
     */
    void updateRole(Role role);

    /**
     * 查询roleFunction
     * @param roleCode
     * @return
     */
    List<RoleFunction> queryRoleFunction(String roleCode);

    /**
     * 查询roleFunction
     * @param roleFunction
     * @return
     */
    List<RoleFunction> queryRoleFunction(RoleFunction roleFunction);

    /**
     * 查询字典数据
     * @param dictionaryData
     * @return
     */
    List<DictionaryData> queryDictionaryData(DictionaryData dictionaryData);

    /**
     * 查询字典数据
     * @param dictionaryData
     * @return
     */
    List<DictionaryData> queryDictionaryData(DictionaryData dictionaryData, int page, int rows);

    /**
     * 添加字典数据
     * @param dictionaryData
     * @return
     */
    int addDictionaryData(DictionaryData dictionaryData);

    /**
     * 删除字典数据
     * @param id
     * @return
     */
    int deleteDictionary(String id);

    /**
     * 更新字典数据
     * @param dictionaryData
     * @return
     */
    int updateDictionary(DictionaryData dictionaryData);

    /**
     * 加载字典类型树
     * @return
     */
    TreeNode loadDictionaryTypeTree();

}
