package com.starter.fastweb.mapper;

import com.starter.fastweb.entity.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


//@Mapper
public interface SystemMapper {
    /**
     * 新增功能权限
     * @param function
     * @return
     */
    int insertFunction(Function function);

    /**
     * 根据functionCode查找Function
     * @param functionCode
     * @return
     */
    Function selectFunctionByCode(String functionCode);

    /**
     * 删除Function
     * @param functionCode
     * @return
     */
    int deleteFunctionByCode(String functionCode);

    /**
     * 根据functionCode 递归查询所有子Function
     * @param functionCode
     * @return
     */
    List<Function> selectAllChildFunctionByCode(String functionCode);

    /**
     * 根据functionCode 修改Function
     * @param function
     * @return
     */
    int updateFunctionSelectiveByCode(Function function);

    /**
     * 查找function
     * @param function
     * @return
     */
    List<Function> selectFunction(Function function);

    /**
     * 分页查询角色
     * @param role
     * @param page
     * @param rows
     * @return
     */
    List<Role> selectRole(Role role, int page, int rows);

    /**
     * 查询角色
     * @param role
     * @return
     */
    List<Role> selectRole(Role role);

    /**
     * 删除角色
     * @param roleCode
     * @return
     */
    int deleteRoleByCode(String roleCode);

    /**
     * 删除角色与功能的关联关系
     * @param roleCode
     * @return
     */
    int deleteRoleFunctionByRoleCode(String roleCode);

    /**
     * 查找角色
     * @param roleCode
     * @return
     */
    Role selectRoleByCode(String roleCode);

    /**
     * 批量插入roleFunction
     * @param roleFunctions
     * @return
     */
    int insertRoleFunction(List<RoleFunction> roleFunctions);

    /**
     * 插入角色
     * @param role
     * @return
     */
    int insertRole(Role role);

    /**
     * 更新role
     * @param role
     * @return
     */
    int updateRoleByCodeSelective(Role role);

    /**
     * 查询roleFunction
     * @return
     */
    List<RoleFunction> selectRoleFunction(RoleFunction roleFunction);

    /**
     * 查询字典数据
     * @param dictionaryData
     * @return
     */
    List<DictionaryData> selectDictionaryData(DictionaryData dictionaryData);

    /**
     * 查询字典数据
     * @param dictionaryData
     * @return
     */
    List<DictionaryData> selectDictionaryData(DictionaryData dictionaryData, int page, int rows);

    /**
     * 删除字典数据
     * @param id
     * @return
     */
    int deleteDictionaryData(String id);

    /**
     * 插入字典数据
     * @param dictionaryData
     * @return
     */
    int insertDictionaryData(DictionaryData dictionaryData);

    /**
     * 更新字典数据
     * @param dictionaryData
     * @return
     */
    int updateDictionaryDataSelective(DictionaryData dictionaryData);

    /**
     * 查询字典类型
     * @param dictionaryType
     * @return
     */
    List<DictionaryType> selectDictionaryType(DictionaryType dictionaryType);
}
