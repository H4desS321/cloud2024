package com.atguigu.cloud.handler;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

/**
 * ClassName: MyRequestOriginParser
 * Package: com.atguigu.cloud.handler
 *
 * @Author 刘新雨
 * @Create 2024/7/2 14:32
 * @Version 1.0
 * Description:
 */
@Component
public class MyRequestOriginParser implements RequestOriginParser {
    @Override
    public String parseOrigin(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getParameter("serverName");
    }
}
