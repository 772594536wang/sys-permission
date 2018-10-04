package com.morethink.syspermission.filter;

import com.morethink.syspermission.common.RequestHolder;
import com.morethink.syspermission.entity.user.SysUser;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录拦截，判断页面状态是否是登录状态
 */
@Slf4j
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        SysUser sysUser = (SysUser) request.getSession().getAttribute("user");
        if (sysUser == null) {
            // 用户未登录
            String path = "signin.jsp";
            response.sendRedirect(path);
            return;
        }

        RequestHolder.add(sysUser);
        RequestHolder.add(request);

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
