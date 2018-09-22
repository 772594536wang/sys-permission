package com.morethink.syspermission.common;

import com.morethink.syspermission.exception.ParamException;
import com.morethink.syspermission.exception.PermissionException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 定义权限系统全局异常处理类
 *
 * @author wangpf
 */
public class SysExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest,
                                         HttpServletResponse httpServletResponse,
                                         Object handler, Exception ex) {
        String url = httpServletRequest.getRequestURL().toString();
        ModelAndView mv;
        String defaultMsg = "System error!";

        JsonData result;
        // 系统请求以.json结尾。页面请求以.page结尾
        if (url.endsWith(".json")) {
            if (ex instanceof PermissionException || ex instanceof ParamException) {
                result = JsonData.fail(ex.getMessage());
            } else {
                result = JsonData.fail(defaultMsg);
            }
            mv = new ModelAndView("jsonView", result.toMap());
        } else if (url.endsWith(".page")) {
            result = JsonData.fail(defaultMsg);
            mv = new ModelAndView("exception", result.toMap());
        } else {

        }

        return null;
    }
}
