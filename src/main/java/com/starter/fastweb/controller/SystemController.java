package com.starter.fastweb.controller;


import com.starter.fastweb.entity.*;
import com.starter.fastweb.service.SystemService;
import com.starter.fastweb.utils.AjaxTable;
import com.starter.fastweb.utils.JsonBean;
import com.starter.fastweb.utils.SysConstants;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Jack
 * @Time: 2016-02-29 13:10
 */
@Controller
@RequestMapping("/system")
public class SystemController {

    @Resource
    private SystemService systemService;

    /**
     * 加载功能权限树
     * @return
     */
    @RequestMapping(value = "/loadFunctionTree", method = RequestMethod.POST)
    @ResponseBody
    public JsonBean loadFunctionTree(@RequestParam(value = "roleCode", required = false) String roleCode){
        JsonBean jsonBean = new JsonBean();
        FunctionNode functionNode = systemService.loadFunctionTree("0", roleCode);
        List<FunctionNode> list = new ArrayList<FunctionNode>();
        list.add(functionNode);
        Map<String, Object> results = new HashMap<String, Object>();
        results.put("functionTree", list);
        jsonBean.setResults(results);
        return jsonBean;
    }


    /**
     * 根据functionCode查找function
     * @param request
     * @param session
     * @param functionCode
     * @return
     */
    @RequestMapping(value = "/queryFunctionByCode", method = RequestMethod.POST)
    @ResponseBody
    public JsonBean queryFunctionByCode(HttpServletRequest request,HttpSession session,
                                   @RequestParam(value = "functionCode", required = true) String functionCode){
        JsonBean jsonBean = new JsonBean();
        Function function = systemService.queryFunctionByCode(functionCode);
        function.setParentFunction(systemService.queryFunctionByCode(function.getParentCode()));
        jsonBean.setResults(function);
        return jsonBean;
    }

    /**
     * 查找页面上的button
     * @param request
     * @param session
     * @param functionCode
     * @return
     */
    @RequestMapping(value = "/queryButton", method = RequestMethod.POST)
    @ResponseBody
    public JsonBean querySubFunctionByCode(HttpServletRequest request,HttpSession session,
                                        @RequestParam(value = "functionCode", required = true) String functionCode){
        JsonBean jsonBean = new JsonBean();
        Function function = new Function();
        function.setParentCode(functionCode);
        function.setFunctionType(SysConstants.FUNCTION_TYPE_BUTTON);
        List<Function> functionList = systemService.queryFunction(function);
        jsonBean.setResults(functionList);
        return jsonBean;
    }

    /**
     * 删除function
     * @param request
     * @param session
     * @param functionCode
     * @return
     */
    @RequestMapping(value = "/deleteFunctionByCode", method = RequestMethod.POST)
    @ResponseBody
    public JsonBean deleteFunctionByCode(HttpServletRequest request,HttpSession session,
                                           @RequestParam(value = "functionCode", required = true) String functionCode){
        JsonBean jsonBean = new JsonBean();
        Function params = new Function();
        params.setParentCode(functionCode);
        List<Function> subFunctionList = systemService.queryFunction(params);
        //含有子功能，禁止删除
        if(CollectionUtils.isNotEmpty(subFunctionList)){
            jsonBean.setCode(SysConstants.CODE_FAIL);
            jsonBean.setMessage(SysConstants.FORBIDDEN_DELETE_HAS_SUB_FUNCTION);
            return jsonBean;
        }
        RoleFunction roleFunction = new RoleFunction();
        roleFunction.setFunctionCode(functionCode);
        if(CollectionUtils.isNotEmpty(systemService.queryRoleFunction(roleFunction))){
            jsonBean.setCode(SysConstants.CODE_FAIL);
            jsonBean.setMessage(SysConstants.ASSOCIATE_ROLE);
            return jsonBean;
        }

        try{
            systemService.removeFunctionByCode(functionCode);
            jsonBean.setCode(SysConstants.CODE_OK);
        }catch (Exception e){
            e.printStackTrace();
            jsonBean.setCode(SysConstants.CODE_ERROR);
        }

        return jsonBean;
    }


    /**
     * 保存function
     * @param request
     * @param session
     * @param function
     * @return
     */
    @RequestMapping(value = "/saveFunction", method = RequestMethod.POST)
    @ResponseBody
    public JsonBean querySubFunctionByCode(HttpServletRequest request,HttpSession session,Function function){
        JsonBean jsonBean = new JsonBean();

        Function parentFunction = systemService.queryFunctionByCode(function.getParentCode());
        function.setParentFunction(parentFunction);
        //更新
        if(StringUtils.isNotBlank(function.getId())){
            systemService.updateFunctionByCode(function);
            jsonBean.setCode(SysConstants.CODE_OK);
            jsonBean.setMessage(SysConstants.UPDATE_SUCCESS);
        //添加
        }else{
            Function result = systemService.queryFunctionByCode(function.getFunctionCode());
            //功能编码已存在
            if(result != null){
                jsonBean.setCode(SysConstants.CODE_FAIL);
                jsonBean.setMessage(SysConstants.FUNCTION_CODE_EXISTS);
            }
            systemService.addFunction(function);
            jsonBean.setCode(SysConstants.CODE_OK);
            jsonBean.setMessage(SysConstants.SAVE_SUCCESS);
        }
        return jsonBean;
    }

    /**
     * 分页查询角色
     * @param request
     * @param session
     * @param roleName
     * @param draw
     * @param start
     * @param length
     * @param sort
     * @param order
     * @return
     */
    @RequestMapping(value = "/queryRole", method = RequestMethod.POST)
    @ResponseBody
    public AjaxTable queryRole(HttpServletRequest request, HttpSession session,
                               @RequestParam(value = "roleName", required = false) String roleName,
                               @RequestParam(value = "draw", required = false) int draw,
                               @RequestParam(value = "start", required = false) int start,
                               @RequestParam(value = "length", required = false) int length,
                               @RequestParam(value = "sort", required = false) String sort,
                               @RequestParam(value = "order", required = false) String order){
        AjaxTable ajaxTable = new AjaxTable();
        int page = start/length + 1;
        Role params = new Role();
        params.setRoleName(roleName);
        List<Role> roleList = systemService.queryRole(params, page, length);
        //数据总数
        // TODO 需要实现分页
//        ajaxTable.setRecordsTotal(((PageList<Role>)roleList).getPaginator().getTotalCount());
//        ajaxTable.setRecordsFiltered(((PageList<Role>)roleList).getPaginator().getTotalCount());
        ajaxTable.setData(roleList);
        ajaxTable.setDraw(draw);

        return ajaxTable;
    }

    /**
     * 删除角色
     * @param request
     * @param session
     * @param roleCode
     * @return
     */
    @RequestMapping(value = "/deleteRole", method = RequestMethod.POST)
    @ResponseBody
    public JsonBean deleteRole(HttpServletRequest request,HttpSession session,
                               @RequestParam(value = "roleCode", required = false) String roleCode){
        JsonBean jsonBean = new JsonBean();

        try{
            systemService.deleteRole(roleCode);
            jsonBean.setCode(SysConstants.CODE_OK);
        }catch (Exception e){
            e.printStackTrace();
            jsonBean.setCode(SysConstants.CODE_ERROR);
        }

        return jsonBean;
    }

    /**
     * 更新角色
     * @param request
     * @param session
     * @param role
     * @return
     */
    @RequestMapping(value = "/saveRole", method = RequestMethod.POST)
    @ResponseBody
    public JsonBean saveRole(HttpServletRequest request,HttpSession session,Role role){
        JsonBean jsonBean = new JsonBean();
        //更新
        if(StringUtils.isNotBlank(role.getId())){
            systemService.updateRole(role);
            jsonBean.setCode(SysConstants.CODE_OK);
            jsonBean.setMessage(SysConstants.UPDATE_SUCCESS);
        //添加
        }else{
            Role result = systemService.queryRoleByCode(role.getRoleCode());
            if(result != null){
                jsonBean.setCode(SysConstants.CODE_FAIL);
                jsonBean.setMessage(SysConstants.ROLE_CODE_EXISTS);
                return jsonBean;
            }
            systemService.addRole(role);
            jsonBean.setCode(SysConstants.CODE_OK);
            jsonBean.setMessage(SysConstants.SAVE_SUCCESS);
        }
        return jsonBean;
    }

    /**
     * 加载字典类型树
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = "/loadDictionaryTypeTree", method = RequestMethod.POST)
    @ResponseBody
    public JsonBean loadDictionaryTypeTree(HttpServletRequest request,HttpSession session){
        JsonBean jsonBean = new JsonBean();
        TreeNode treeNode = systemService.loadDictionaryTypeTree();
        jsonBean.setResults(new TreeNode[]{treeNode});
        return jsonBean;
    }

    /**
     * 分页查询数据字典
     * @param request
     * @param session
     * @param dictType
     * @param draw
     * @param start
     * @param length
     * @param sort
     * @param order
     * @return
     */
    @RequestMapping(value = "/queryDictionary", method = RequestMethod.POST)
    @ResponseBody
    public AjaxTable queryDictionary(HttpServletRequest request,HttpSession session,
                                     @RequestParam(value = "dictType", required = false) String dictType,
                                     @RequestParam(value = "draw", required = false) int draw,
                                     @RequestParam(value = "start", required = false) int start,
                                     @RequestParam(value = "length", required = false) int length,
                                     @RequestParam(value = "sort", required = false) String sort,
                                     @RequestParam(value = "order", required = false) String order){
        AjaxTable ajaxTable = new AjaxTable();
        int page = start/length + 1;
        DictionaryData params = new DictionaryData();
        params.setDictType(dictType);
        List<DictionaryData> dictionaryDataList = systemService.queryDictionaryData(params, page, length);
        //数据总数
        // TODO 需要实现分页
//        ajaxTable.setRecordsTotal(((PageList<DictionaryData>)dictionaryDataList).getPaginator().getTotalCount());
//        ajaxTable.setRecordsFiltered(((PageList<DictionaryData>)dictionaryDataList).getPaginator().getTotalCount());
        ajaxTable.setData(dictionaryDataList);
        ajaxTable.setDraw(draw);
        return ajaxTable;
    }

    /**
     * 删除数据字典
     * @param request
     * @param session
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteDictionary", method = RequestMethod.POST)
    @ResponseBody
    public JsonBean deleteDictionary(HttpServletRequest request,HttpSession session,
                                     @RequestParam(value = "id", required = true) String id){
        JsonBean jsonBean = new JsonBean();

        try{
            systemService.deleteDictionary(id);
            jsonBean.setCode(SysConstants.CODE_OK);
        }catch (Exception e){
            e.printStackTrace();
            jsonBean.setCode(SysConstants.CODE_ERROR);
        }

        return jsonBean;
    }


    /**
     * 保存数据字典
     * @param request
     * @param session
     * @param dictionaryData
     * @return
     */
    @RequestMapping(value = "/saveDictionary", method = RequestMethod.POST)
    @ResponseBody
    public JsonBean saveDictionary(HttpServletRequest request,HttpSession session,DictionaryData dictionaryData){
        JsonBean jsonBean = new JsonBean();
        String id = dictionaryData.getId();
        //更新
        if(StringUtils.isNotBlank(id)){
            systemService.updateDictionary(dictionaryData);
            jsonBean.setCode(SysConstants.CODE_OK);
            jsonBean.setMessage(SysConstants.UPDATE_SUCCESS);
        //保存
        }else{
            systemService.addDictionaryData(dictionaryData);
            jsonBean.setCode(SysConstants.CODE_OK);
            jsonBean.setMessage(SysConstants.SAVE_SUCCESS);
        }

        return jsonBean;
    }






}
