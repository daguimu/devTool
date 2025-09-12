package com.dagm.devtool.interceptor;

import com.dagm.devtool.utils.NetWorkUtils;
import com.dagm.devtool.utils.ResponseUtils;
import java.io.IOException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 前置检查拦截器
 *
 * @author Guimu
 * @date 2018/04/18 18:54:33
 **/
@Component
@Slf4j
public class InnerInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
        Object handler) {
        String clientIp = request.getHeader("X-Real-Ip");
        // 进行渠道校验
        if (!StringUtils.isEmpty(clientIp) && !NetWorkUtils.internalIp(clientIp)) {
            try {
                ResponseUtils.littleAuthor(response, "该接口仅内网可访问");
                return false;
            } catch (IOException e) {
                log.error("系统异常", e);
            }
        }
        return true;
    }
}