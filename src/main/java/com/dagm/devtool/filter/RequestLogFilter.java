/*
 * Copyright (c) 2019 maoyan.com
 * All rights reserved.
 *
 */
package com.dagm.devtool.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * @author Guimu
 * @date 2019/10/11
 */
@WebFilter(urlPatterns = {"/**"}, filterName = "request-log-filter")
@Order(5)
@Slf4j
public class RequestLogFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
        HttpServletResponse httpServletResponse, FilterChain filterChain)
        throws ServletException, IOException {
        log.info("clientIp:[{}]请求访问接口url:[{}]", httpServletRequest.getHeader("X-Real-Ip"),
            httpServletRequest.getRequestURL());
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}