package com.starter.fastweb.controller;


import com.starter.fastweb.entity.Role;
import com.starter.fastweb.entity.User;
import com.starter.fastweb.service.SystemService;
import com.starter.fastweb.service.UserService;
import com.starter.fastweb.utils.AjaxTable;
import com.starter.fastweb.utils.JsonBean;
import com.starter.fastweb.utils.SysConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Jack
 * @Time: 2017-02-24 10:55
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private SystemService systemService;

    /**
     * 分页查询用户
     * @param request
     * @param session
     * @param userAccount
     * @param userName
     * @param draw
     * @param start
     * @param length
     * @param sort
     * @param order
     * @return
     */
    @RequestMapping(value = "/queryUser", method = RequestMethod.POST)
    @ResponseBody
    public AjaxTable queryUser(HttpServletRequest request, HttpSession session,
                               @RequestParam(value = "userAccount", required = false) String userAccount,
                               @RequestParam(value = "userName", required = false) String userName,
                               @RequestParam(value = "draw", required = false) int draw,
                               @RequestParam(value = "start", required = false) int start,
                               @RequestParam(value = "length", required = false) int length,
                               @RequestParam(value = "sort", required = false) String sort,
                               @RequestParam(value = "order", required = false) String order){
        AjaxTable ajaxTable = new AjaxTable();
        int page = start/length + 1;
        User params = new User();
        params.setUserName(userName);
        params.setUserAccount(userAccount);
        List<User> userList = userService.queryUser(params, page, length);
        //数据总数
        // TODO 需要实现缓存
//        ajaxTable.setRecordsTotal(((PageList<User>)userList).getPaginator().getTotalCount());
//        ajaxTable.setRecordsFiltered(((PageList<User>)userList).getPaginator().getTotalCount());
        ajaxTable.setData(userList);
        ajaxTable.setDraw(draw);

        return ajaxTable;
    }

    /**
     * 删除用户
     * @param request
     * @param session
     * @param userAccount
     * @return
     */
    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    @ResponseBody
    public JsonBean deleteUser(HttpServletRequest request, HttpSession session,
                               @RequestParam(value = "userAccount", required = true) String userAccount){
        JsonBean jsonBean = new JsonBean();

        try{
            userService.deleteUser(userAccount);
            jsonBean.setCode(SysConstants.CODE_OK);
        }catch (Exception e){
            e.printStackTrace();
            jsonBean.setCode(SysConstants.CODE_ERROR);
        }

        return jsonBean;
    }

    /**
     * 保存用户
     * @param request
     * @param session
     * @param user
     * @return
     */
    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    @ResponseBody
    public JsonBean saveUser(HttpServletRequest request,HttpSession session,User user, BindingResult bindingResult){
        JsonBean jsonBean = new JsonBean();

        // 如果验证不通过，错误信息会在BindingResult这个对象中
        if (bindingResult.hasErrors()) {
            jsonBean.setCode(SysConstants.CODE_FAIL);
            jsonBean.setMessage(bindingResult.getFieldErrors().get(0).getDefaultMessage());
            return jsonBean;
        }

        //更新
        if(user.getId() != null){
            userService.updateUser(user);
            jsonBean.setCode(SysConstants.CODE_OK);
            jsonBean.setMessage(SysConstants.UPDATE_SUCCESS);
        //保存
        }else{
            User result = userService.queryUserByAccount(user.getUserAccount());
            if(result != null){
                jsonBean.setCode(SysConstants.CODE_FAIL);
                jsonBean.setMessage(SysConstants.USER_EXISTS);
                return jsonBean;
            }
            userService.addUser(user);
            jsonBean.setCode(SysConstants.CODE_OK);
            jsonBean.setMessage(SysConstants.SAVE_SUCCESS);
        }


        return jsonBean;
    }

    /**
     * 查找角色
     * @param request
     * @param session
     * @param userAccount
     * @return
     */
    @RequestMapping(value = "/queryRoleByUserAccount", method = RequestMethod.POST)
    @ResponseBody
    public JsonBean queryRoleByUserAccount(HttpServletRequest request,HttpSession session,
                               @RequestParam(value = "userAccount", required = true) String userAccount){
        JsonBean jsonBean = new JsonBean();
        List<Role> selectRoleList = userService.queryRoleByUserAccount(userAccount);
        List<Role> roleList = systemService.queryRole(new Role());
        Map<String, Object> results = new HashMap<String, Object>();
        results.put("selectRoleList", selectRoleList);
        results.put("roleList", roleList);
        jsonBean.setResults(results);
        return jsonBean;
    }

    /**
     * 保存用户角色信息
     * @param request
     * @param session
     * @param userAccount
     * @return
     */
    @RequestMapping(value = "/saveUserRole", method = RequestMethod.POST)
    @ResponseBody
    public JsonBean saveUserRole(HttpServletRequest request,HttpSession session,
                                           @RequestParam(value = "userAccount", required = true) String userAccount, String roleCodes){
        JsonBean jsonBean = new JsonBean();
        String[] roleCodeArr;
        if(StringUtils.isNotBlank(roleCodes)){
            roleCodeArr = roleCodes.split(",");
        }else{
            roleCodeArr = new String[]{};
        }

        try{
            userService.addUserRole(userAccount, roleCodeArr);
            jsonBean.setCode(SysConstants.CODE_OK);
        }catch (Exception e){
            e.printStackTrace();
            jsonBean.setCode(SysConstants.CODE_ERROR);

        }

        return jsonBean;
    }


}
