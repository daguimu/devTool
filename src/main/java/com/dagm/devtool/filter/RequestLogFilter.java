/*
 * Copyright (c) 2019 maoyan.com
 * All rights reserved.
 *
 */
package com.dagm.devtool.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Guimu
 * @date 2019/10/11
 */
@WebFilter(urlPatterns = {"/**"}, filterName = "request-log-filter")
@Order(1)
@Slf4j
public class RequestLogFilter extends OncePerRequestFilter {

    private static final String FILTER_STR = "actuator/health";

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse, FilterChain filterChain)
            throws ServletException, IOException {
        String url = httpServletRequest.getRequestURL().toString();
        if (StringUtils.isNotEmpty(url) && !url.contains(FILTER_STR)) {
            log.info("clientIp:[{}]请求访问接口url:[{}]", httpServletRequest.getHeader("X-Real-Ip"), url);
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}