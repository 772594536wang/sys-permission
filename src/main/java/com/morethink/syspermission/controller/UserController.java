package com.morethink.syspermission.controller;

import com.morethink.syspermission.service.user.SysUserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登陆相关拦截controller实现
 *
 * @author wangpf
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Resource
    private SysUserService sysUserService;

    @RequestMapping(value = "/login.page", method = RequestMethod.GET)
    public void login(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
    }

}
