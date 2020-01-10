package com.dagm.devtool.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @description: 登录检查拦截器
 * @author: Guimu
 * @create: 2018/04/18 18:54:33
 **/
@Component
@Slf4j
public class LoginInterceptor extends HandlerInterceptorAdapter {


    /**
     * @param request : request
     * @param response : response
     * @return boolean
     * @author Guimu
     * @date 2020/1/10
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
        Object handler) {
        log.info("clientIp:[{}]请求访问接口url:[{}]", request.getHeader("X-Real-Ip"),
            request.getRequestURL());
        return true;
    }
}