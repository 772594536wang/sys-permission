package com.morethink.syspermission.controller;

import com.morethink.syspermission.entity.user.SysUser;
import com.morethink.syspermission.service.user.SysUserService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登陆相关controller实现
 *
 * @author wangpf
 */
@RestController
@RequestMapping(value = "/login")
public class LoginController {

    @Resource
    private SysUserService sysUserService;


    @RequestMapping(value = "/logout.page", method = RequestMethod.GET)
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().invalidate();
        String path = "signin.jsp";
        response.sendRedirect(path);
    }

    @RequestMapping(value = "/login.page", method = RequestMethod.GET)
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String errMsg = "";
        String ret = request.getParameter("ret");

        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            errMsg = "用户名和密码不能为空";
        }


        SysUser user = sysUserService.findUserByUsername(username);
        if (user == null) {
            errMsg = "用户名不存在";
        }

        if (!user.getPassword().equals(password)) {
            errMsg = "用户名或者密码错误";
        }

        if (user.getStatus() != 1) {
            errMsg = "用户已经被冻结，请联系管理员";
        }

        // 当前用户信息存放到session中
        request.getSession().setAttribute("user", user);

        if (!StringUtils.isEmpty(ret)) {
            response.sendRedirect(ret);
        } else {
            response.sendRedirect("/admin/index.page");
        }

        request.setAttribute("error", errMsg);
        request.setAttribute("username", username);
        if (!StringUtils.isEmpty(ret)) {
            request.setAttribute("ret", ret);
        }

        String path = "signin.jsp";
        request.getRequestDispatcher(path).forward(request, response);
    }
}
