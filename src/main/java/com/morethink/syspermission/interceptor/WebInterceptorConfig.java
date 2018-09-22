package com.morethink.syspermission.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * http请求拦截器配置类
 *
 * @author wangpf
 */
@Configuration
public class WebInterceptorConfig implements WebMvcConfigurer {

    /**
     * 拦截/下的所有请求，但是不包含资源文件。
     *
     * @param registry 注册拦截
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HttpInterceptor()).addPathPatterns("/**");
    }
}
