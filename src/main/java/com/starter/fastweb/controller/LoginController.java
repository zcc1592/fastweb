package com.starter.fastweb.controller;

import com.starter.fastweb.param.LoginParam;
import com.starter.fastweb.utils.JsonBean;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(AuthController.class);


    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public JsonBean authentication(@RequestBody LoginParam loginParam){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(loginParam.getUserAccount(), loginParam.getPassword());
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            logger.info("登录失败，用户名或密码错误");
        }
        return null;
    }



}
