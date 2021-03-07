package com.starter.fastweb.controller;


import com.starter.fastweb.cache.RedisService;
import com.starter.fastweb.entity.Function;
import com.starter.fastweb.entity.Role;
import com.starter.fastweb.entity.User;
import com.starter.fastweb.service.UserService;
import com.starter.fastweb.utils.JsonBean;
import com.starter.fastweb.utils.SysConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 权限验证
 * 主要包括： 页面跳转、登录验证、退出登录
 */
@Controller
public class AuthController {
    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Resource
    private RedisService redisService;
    @Resource
    private UserService userService;

    private final static String ALM_FUNCTION_CODE = "05";


    /**
     * 根路径
     * @author Jack
     * @date 2015-12-18 下午1:54:37
     * @param modelAndView
     * @return
     * @see
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String rootPath(ModelAndView modelAndView){
        return "forward:/login";
    }

    /**
     * 登录页面
     * @author Jack
     * @date 2015-12-18 下午1:54:37
     * @param modelAndView
     * @return
     * @see
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public Object loginPage(ModelAndView modelAndView,HttpServletRequest request,HttpSession session){
        modelAndView.setViewName("auth/login");
        return modelAndView;
    }

    /**
     * 退出
     * @param modelAndView
     * @param session
     * @param request
     * @param locale
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout(ModelAndView modelAndView, HttpSession session,HttpServletRequest request, @PathVariable String locale){
        User user = (User)session.getAttribute(SysConstants.SESSION_USER);
        logger.info("["+user.getUserAccount()+"]退出登录");
        session.removeAttribute(SysConstants.SESSION_USER);
        modelAndView.setViewName("auth/login");
        return modelAndView;
    }

    /**
     * 登录验证
     * @author Jack
     * @date 2015-12-24 下午3:59:26
     * @param modelAndView
     * @param fdUserName
     * @param fdUserPwd
     * @return
     * @see
     */
    @RequestMapping(value = "/auth",method = RequestMethod.POST)
    @ResponseBody
    public JsonBean authentication(ModelAndView modelAndView, HttpSession session, HttpServletRequest request,
                                   @RequestParam(value = "fdUserName", required = true) String fdUserName,
                                   @RequestParam(value = "fdUserPwd", required = true) String fdUserPwd){
        JsonBean jsonBean = new JsonBean();
        logger.info("["+fdUserName+"]登录开始");
        try{
            User user = userService.queryUser(fdUserName, fdUserPwd);
            if(user == null){
                logger.info("["+fdUserName+"]账号或密码错误");
                jsonBean.setCode(SysConstants.CODE_FAIL);
                jsonBean.setMessage(SysConstants.LOGIN_MSG_NO_USER);
            }else{
                List<Role> roleList = userService.queryRoleByUserAccount(fdUserName);
                logger.info("[" + fdUserName + "]对应角色列表" + user.getRoleList());
                user.setRoleList(roleList);
                List<Function> functionList = userService.queryFunctionList(fdUserName);
                user.setFunctionList(functionList);
                Map<String, Function> functionMap = new HashMap<String, Function>();
                for(Function function : functionList){
                    functionMap.put(function.getFunctionCode(), function);
                }
                user.setFunctionMap(functionMap);
                logger.info("["+fdUserName+"]对应功能列表"+user.getFunctionList());

//                Function almFunction = user.getFunctionMap().get(ALM_FUNCTION_CODE);
                //用户没有权限
                if(functionMap.size() == 0){
                    logger.info("["+fdUserName+"]账号没有权限");
                    jsonBean.setCode(SysConstants.CODE_FAIL);
                    jsonBean.setMessage(SysConstants.LOGIN_MSG_PERMISSION_DENIED);
                }else{ //验证通过
                    logger.info("["+fdUserName+"]登录验证通过");
                    jsonBean.setCode(SysConstants.CODE_OK);
                    jsonBean.setMessage(SysConstants.LOGIN_MSG_SUCCESS);
                    session.setAttribute(SysConstants.SESSION_USER, user);
                    logger.info("["+fdUserName+"]将sessionId保存到redis...");
                    try{
                        redisService.setEx(user.getUserAccount() + "_sessionId", session.getId(), SysConstants.USERID_SESSIONID_EXPIRED);
                    }catch (Exception e){
                        logger.error("["+fdUserName+"]将sessionId保存到redis异常", e);
                        e.printStackTrace();
                    }
                    logger.info("["+fdUserName+"]成功将sessionId保存到redis");
                    logger.info("["+fdUserName+"]登录成功");

                }

            }


        }catch (Exception e){
            e.printStackTrace();
        }
        logger.info("["+fdUserName+"]登录结束");
        return jsonBean;
    }



	/**
	 * 首页
	 * @author Jack
	 * @date 2015-12-25 上午11:51:48
	 * @param modelAndView
	 * @return
	 * @see
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(ModelAndView modelAndView,HttpSession session){

        String indexUrl = "";
        User user = (User)session.getAttribute(SysConstants.SESSION_USER);
        List<Function> functionList = user.getFunctionList();
        for(Function function : functionList){
            if(function.getFunctionType().equals(SysConstants.FUNCTION_TYPE_MENU)){
                indexUrl = function.getUri();
                break;
            }
        }

		return "forward:" + indexUrl;
	}

    /**
     * 全局监控
     * @author Jack
     * @date 2015-12-25 上午11:57:11
     * @param modelAndView
     * @return
     * @see
     */
    @RequestMapping(value = "/toGlobalMonitor", method = RequestMethod.GET)
    public ModelAndView toGlobalMonitor(ModelAndView modelAndView,HttpServletRequest request){
        modelAndView.setViewName("monitor/globalmonitor");
        return modelAndView;
    }

    /**
     * 功能权限
     * @author Jack
     * @date 2015-12-25 上午11:57:11
     * @param modelAndView
     * @return
     * @see
     */
    @RequestMapping(value = "/toFunction", method = RequestMethod.GET)
    public ModelAndView toFunction(ModelAndView modelAndView,HttpServletRequest request){
        modelAndView.setViewName("system/function");
        return modelAndView;
    }

    /**
     * 角色管理
     * @author Jack
     * @date 2015-12-25 上午11:57:11
     * @param modelAndView
     * @return
     * @see
     */
    @RequestMapping(value = "/toRole", method = RequestMethod.GET)
    public ModelAndView toRole(ModelAndView modelAndView,HttpServletRequest request){
        modelAndView.setViewName("system/role");
        return modelAndView;
    }

    /**
     * 用户管理
     * @param modelAndView
     * @param request
     * @return
     */
    @RequestMapping(value = "/toUser", method = RequestMethod.GET)
    public ModelAndView toUser(ModelAndView modelAndView,HttpServletRequest request){
        modelAndView.setViewName("system/user");
        return modelAndView;
    }

    /**
     * 节点选择窗口
     * @param modelAndView
     * @return
     */
    @RequestMapping(value = "/toNodeWin", method = RequestMethod.GET)
    public ModelAndView toNodeWin(ModelAndView modelAndView,HttpServletRequest request){

        modelAndView.setViewName("basis/nodeWin");
        return modelAndView;
    }
    
    /**
     * 节点管理
     * @param modelAndView
     * @return
     */
    @RequestMapping(value = "/toNode", method = RequestMethod.GET)
    public ModelAndView toNode(ModelAndView modelAndView,HttpServletRequest request){

        modelAndView.setViewName("basis/node");
        return modelAndView;
    }
    
    /**
     * 器具管理
     * @param modelAndView
     * @return
     */
    @RequestMapping(value = "/toAppliance", method = RequestMethod.GET)
    public ModelAndView toAppliance(ModelAndView modelAndView,HttpServletRequest request){
       
    	modelAndView.setViewName("basis/appliance");
        return modelAndView;
    }
    
    /**
     * 节点组管理
     * @param modelAndView
     * @return
     */
    @RequestMapping(value = "/toNodeGroup", method = RequestMethod.GET)
    public ModelAndView toNodeGroup(ModelAndView modelAndView,HttpServletRequest request){

        modelAndView.setViewName("basis/nodeGroup");
        return modelAndView;
    }
    
    /**
     * 安全库存配置管理
     * @param modelAndView
     * @return
     */
    @RequestMapping(value = "/toInventory", method = RequestMethod.GET)
    public ModelAndView toInventory(ModelAndView modelAndView,HttpServletRequest request){
       
    	modelAndView.setViewName("basis/inventory");
        return modelAndView;
    }

    /**
     * 数据字典
     * @param modelAndView
     * @param request
     * @return
     */
    @RequestMapping(value = "/toDictionary", method = RequestMethod.GET)
    public ModelAndView toDictionary(ModelAndView modelAndView,HttpServletRequest request){
        modelAndView.setViewName("system/dictionary");
        return modelAndView;
    }

    /**
     * 超时页面
     * @param modelAndView
     * @return
     */
    @RequestMapping(value = "/timeout", method = RequestMethod.GET)
    public ModelAndView timeout(ModelAndView modelAndView,HttpServletRequest request){
        modelAndView.setViewName("error/timeout");
        return modelAndView;
    }

    /**
     * 踢出页面（账号在别处登录）
     * @param modelAndView
     * @return
     */
    @RequestMapping(value = "/shotoff", method = RequestMethod.GET)
    public ModelAndView shotoff(ModelAndView modelAndView,HttpServletRequest request){
        modelAndView.setViewName("error/shotoff");
        return modelAndView;
    }

}
