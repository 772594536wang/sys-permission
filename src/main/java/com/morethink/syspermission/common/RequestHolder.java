package com.morethink.syspermission.common;

import com.morethink.syspermission.entity.user.SysUser;

import javax.servlet.http.HttpServletRequest;

/**
 * 每个线程持有的request
 *
 * @author wangpf
 */
public class RequestHolder {

    /**
     * 存放当前线程的登录用户信息，确保每个用户的信息都是线程隔离的
     */
    private static final ThreadLocal<SysUser> userHolder = new ThreadLocal<>();

    /**
     * 存放当前线程的rquest请求，确保每个用户的信息都是线程隔离的
     */
    private static final ThreadLocal<HttpServletRequest> requestHolder = new ThreadLocal<>();

    public static void add(SysUser user){
        userHolder.set(user);
    }

    public static void add(HttpServletRequest request){
        requestHolder.set(request);
    }

    public static SysUser getCurrentUser(){
        return userHolder.get();
    }

    public static HttpServletRequest getCurrentRequest(){
        return requestHolder.get();
    }

    public static void remove(){
        userHolder.remove();
        requestHolder.remove();
    }
}
