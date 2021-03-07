package com.starter.fastweb.service.impl;


import com.starter.fastweb.entity.*;
import com.starter.fastweb.mapper.SystemMapper;
import com.starter.fastweb.service.SystemService;
import com.starter.fastweb.utils.SysConstants;
import com.starter.fastweb.utils.UUIDUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Author: Jack
 * @Time: 2016-02-29 13:38
 */
@Service("systemService")
public class SystemServiceImpl implements SystemService {


    @Resource
    private SystemMapper systemMapper;

    /**
     * 新增功能权限
     * @param function
     * @return
     */
    @Override
    public int addFunction(Function function) {

        function.setId(UUIDUtils.getUUID());
        function.setActive(true);
        function.setCreateDate(new Date());
        function.setSystemType(1);
        function.setIconCls("foss_icons_emp");

        // 设置层级
        int level = function.getParentFunction().getFunctionLevel() + 1;
        function.setFunctionLevel(level);

        // 路径
        function.setFunctionSeq(
                function.getParentFunction().getFunctionSeq() +
                        SysConstants.SEQ_SEPARATOR +
                        function.getFunctionCode());

        if(function.getFunctionType().equals(SysConstants.FUNCTION_TPYE_MENU)){
            function.setLeaf(true);
        }else{
            function.setLeaf(false);
        }

        return systemMapper.insertFunction(function);
    }

    /**
     * 根据functionCode查找Function
     * @param functionCode
     * @return
     */
    @Override
    public Function queryFunctionByCode(String functionCode) {
        return systemMapper.selectFunctionByCode(functionCode);
    }

    /**
     * 删除Function
     * @param functionCode
     * @return
     */
    @Override
    public int removeFunctionByCode(String functionCode) {
        return systemMapper.deleteFunctionByCode(functionCode);
    }

    /**
     * 根据functionCode 递归查询所有子Function
     * @param functionCode
     * @return
     */
    @Override
    public List<Function> queryAllChildFunctionByCode(String functionCode) {
        return systemMapper.selectAllChildFunctionByCode(functionCode);
    }

    /**
     * 根据functionCode 修改Function
     * @param function
     * @return
     */
    @Override
    public int updateFunctionByCode(Function function) {

        function.setActive(true);
        function.setModifyDate(new Date());
        function.setSystemType(1);
        function.setIconCls("foss_icons_emp");

        // 设置层级
        int level = function.getParentFunction().getFunctionLevel() + 1;
        function.setFunctionLevel(level);

        // 路径
        function.setFunctionSeq(
                function.getParentFunction().getFunctionSeq() +
                        SysConstants.SEQ_SEPARATOR +
                        function.getFunctionCode());

        if(function.getFunctionType().equals(SysConstants.FUNCTION_TPYE_MENU)){
            function.setLeaf(true);
        }else{
            function.setLeaf(false);
        }

        return systemMapper.updateFunctionSelectiveByCode(function);
    }

    /**
     * 加载功能权限树
     * @param functionCode
     * @return
     */
    @Override
    public FunctionNode loadFunctionTree(String functionCode, String roleCode) {
        FunctionNode result = new FunctionNode();
        List<Function> functionList = this.queryAllChildFunctionByCode(functionCode);
        if(CollectionUtils.isNotEmpty(functionList)){
            Map<String, FunctionNode> functionNodeMap = new HashMap<String, FunctionNode>();
            //根节点
            Function rootFunction = functionList.get(0);
            result.setFunctionCode(rootFunction.getFunctionCode());
            result.setText(rootFunction.getFunctionName());
            result.setFunctionType(rootFunction.getFunctionType());
            TreeState treeState = new TreeState();
            treeState.setChecked(true);
            result.setState(treeState);
            functionNodeMap.put(rootFunction.getFunctionCode(), result);
            Set<String> checkFunctionCode = new HashSet<String>();

            if(StringUtils.isNotBlank(roleCode)){
                List<RoleFunction> roleFunctions = this.queryRoleFunction(roleCode);
                for(RoleFunction r : roleFunctions){
                    checkFunctionCode.add(r.getFunctionCode());
                }
            }
            result.setAllCheckNodes(checkFunctionCode);

            for(int j = 1; j < functionList.size(); j++){
                FunctionNode node = new FunctionNode(functionList.get(j).getFunctionCode(), functionList.get(j).getFunctionName(), functionList.get(j).getFunctionType());
                functionNodeMap.put(functionList.get(j).getFunctionCode(),node);
                if(checkFunctionCode.contains(node.getFunctionCode())){
                    TreeState state = new TreeState();
                    state.setChecked(true);
                    node.setState(state);
                }
            }





            for(int i = 1; i < functionList.size(); i++){
                Function function = functionList.get(i);
                //functionType: MODULE、MENU
                if(function.getFunctionType().equals(SysConstants.FUNCTION_TPYE_MODULE) || function.getFunctionType().equals(SysConstants.FUNCTION_TPYE_MENU)){
                    FunctionNode parentNode = functionNodeMap.get(function.getParentCode());
                    if(parentNode != null){
                        List<FunctionNode> nodes = parentNode.getNodes();
                        if(nodes == null){
                            nodes = new ArrayList<FunctionNode>();
                            parentNode.setNodes(nodes);
                        }
                        nodes.add(functionNodeMap.get(function.getFunctionCode()));
                    }
                }
            }
        }

        return result;
    }

    @Override
    public List<Function> queryFunction(Function function) {
        List<Function> functionList = systemMapper.selectFunction(function);
        return functionList != null ? functionList : new ArrayList<Function>();
    }

    /**
     * 分页查询role
     * @param role
     * @param page
     * @param rows
     * @return
     */
    @Override
    public List<Role> queryRole(Role role, int page, int rows) {
        List<Role> roleList = systemMapper.selectRole(role, page, rows);
        // TODO 分页查询需要实现
        return roleList;
    }

    /**
     * 查找角色
     * @param role
     * @return
     */
    @Override
    public List<Role> queryRole(Role role) {
        return systemMapper.selectRole(role);
    }

    /**
     * 删除角色
     * @param roleCode
     * @return
     */
    @Override
    @Transactional
    public void deleteRole(String roleCode) {
        systemMapper.deleteRoleByCode(roleCode);
        systemMapper.deleteRoleFunctionByRoleCode(roleCode);
    }

    /**
     * 查找角色
     * @param roleCode
     * @return
     */
    @Override
    public Role queryRoleByCode(String roleCode) {
        return systemMapper.selectRoleByCode(roleCode);
    }

    /**
     * 添加角色
     * @param role
     */
    @Override
    @Transactional
    public void addRole(Role role) {
        List<RoleFunction> roleFunctions = new ArrayList<RoleFunction>();

        for(String functionCode : role.getFunctionCodes()){
            RoleFunction roleFunction = new RoleFunction();
            roleFunction.setId(UUIDUtils.getUUID());
            roleFunction.setRoleCode(role.getRoleCode());
            roleFunction.setFunctionCode(functionCode);
            roleFunctions.add(roleFunction);
        }

        role.setId(UUIDUtils.getUUID());
        role.setActive(true);
        role.setCreateDate(new Date());

        systemMapper.deleteRoleFunctionByRoleCode(role.getRoleCode());
        systemMapper.insertRoleFunction(roleFunctions);
        systemMapper.insertRole(role);

    }

    /**
     * 更新角色
     * @param role
     */
    @Override
    @Transactional
    public void updateRole(Role role) {
        List<RoleFunction> roleFunctions = new ArrayList<RoleFunction>();

        for(String functionCode : role.getFunctionCodes()){
            RoleFunction roleFunction = new RoleFunction();
            roleFunction.setId(UUIDUtils.getUUID());
            roleFunction.setRoleCode(role.getRoleCode());
            roleFunction.setFunctionCode(functionCode);
            roleFunctions.add(roleFunction);
        }

        role.setActive(true);
        role.setModifyDate(new Date());

        systemMapper.deleteRoleFunctionByRoleCode(role.getRoleCode());
        systemMapper.insertRoleFunction(roleFunctions);
        systemMapper.updateRoleByCodeSelective(role);

    }

    /**
     * 查询RoleFunction
     * @param roleCode
     * @return
     */
    @Override
    public List<RoleFunction> queryRoleFunction(String roleCode) {
        RoleFunction params = new RoleFunction();
        params.setRoleCode(roleCode);
        return this.queryRoleFunction(params);
    }

    /**
     * 查询RoleFunction
     * @return
     */
    @Override
    public List<RoleFunction> queryRoleFunction(RoleFunction roleFunction) {
        return systemMapper.selectRoleFunction(roleFunction);
    }

    /**
     * 查询字典数据
     * @param dictionaryData
     * @return
     */
    @Override
    public List<DictionaryData> queryDictionaryData(DictionaryData dictionaryData) {
        return systemMapper.selectDictionaryData(dictionaryData);
    }

    @Override
    public List<DictionaryData> queryDictionaryData(DictionaryData dictionaryData, int page, int rows) {
        List<DictionaryData> dictionaryDataList = systemMapper.selectDictionaryData(dictionaryData, page, rows);
        // TODO 分页查询需要实现
        return dictionaryDataList;
    }

    /**
     * 添加字典数据
     * @param dictionaryData
     * @return
     */
    @Override
    public int addDictionaryData(DictionaryData dictionaryData) {
        dictionaryData.setId(UUIDUtils.getUUID());
        dictionaryData.setLanguage(SysConstants.DICTIONARY_LANGUAGE_CN);
        dictionaryData.setActive(SysConstants.ACTIVE_YES);
        dictionaryData.setVersionNo(0L);
        dictionaryData.setCreateTime(new Date());
        return systemMapper.insertDictionaryData(dictionaryData);
    }

    /**
     * 删除字典数据
     * @param id
     * @return
     */
    @Override
    public int deleteDictionary(String id) {
        return systemMapper.deleteDictionaryData(id);
    }

    /**
     * 更新字典数据
     * @param dictionaryData
     * @return
     */
    @Override
    public int updateDictionary(DictionaryData dictionaryData) {
        return systemMapper.updateDictionaryDataSelective(dictionaryData);
    }

    /**
     * 加载字典类型树
     * @return
     */
    @Override
    public TreeNode loadDictionaryTypeTree() {
        TreeNode result = new TreeNode();
        List<DictionaryType> dictionaryTypeList = systemMapper.selectDictionaryType(new DictionaryType());

        if(CollectionUtils.isNotEmpty(dictionaryTypeList)){
            Map<String, TreeNode> nodeMap = new HashMap<String, TreeNode>();

            for(DictionaryType dictionaryType : dictionaryTypeList){
                TreeNode treeNode = new TreeNode();
                treeNode.setId(dictionaryType.getId());
                treeNode.setText(dictionaryType.getTypeName());
                treeNode.setType(dictionaryType.getIsLeaf());
                //根节点
                if(StringUtils.isBlank(dictionaryType.getParentCode())){
                    result = treeNode;
                }
                nodeMap.put(dictionaryType.getId(), treeNode);
            }

            for(DictionaryType dictionaryType : dictionaryTypeList){
                TreeNode parentNode = nodeMap.get(dictionaryType.getParentCode());
                TreeNode treeNode = nodeMap.get(dictionaryType.getId());
                if(parentNode != null){
                    List<TreeNode> nodes = parentNode.getNodes();
                    if(nodes == null){
                        nodes = new ArrayList<TreeNode>();
                        parentNode.setNodes(nodes);
                    }
                    nodes.add(treeNode);
                }
            }

        }
        return result;
    }


}
