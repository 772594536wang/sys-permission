package com.morethink.syspermission.interceptor;

import com.morethink.syspermission.util.JsonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 拦截http请求
 *
 * @author wangpf
 */
@Component("httpInterceptor")
public class HttpInterceptor implements HandlerInterceptor {

    private final static Logger log = LoggerFactory.getLogger(HttpInterceptor.class);

    private static final String START_TIME = "requestStartTime";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI().toString();
        Map parameterMap = request.getParameterMap();
        log.info("request start , url:{}, params:{}", url, JsonMapper.object2String(parameterMap));

        long start = System.currentTimeMillis();
        request.setAttribute(START_TIME, start);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        String url = request.getRequestURI().toString();
//
//        long start = (long) request.getAttribute(START_TIME);
//        long end = System.currentTimeMillis();
//
//        log.info("request finish, url:{}, costTime:{}", url, end - start);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String url = request.getRequestURI().toString();
        long start = (long) request.getAttribute(START_TIME);
        long end = System.currentTimeMillis();
        // 记录请求耗费时间
        log.info("request finish, url:{}, costTime:{}", url, end - start);
    }
}
