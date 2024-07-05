package com.atguigu.cloud.config;

import feign.Logger;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName: FeignConfig
 * Package: com.atguigu.cloud.config
 *
 * @Author 刘新雨
 * @Create 2024/6/27 14:21
 * @Version 1.0
 * Description:
 */
@Configuration
public class FeignConfig {
    @Bean
    public Retryer myRetryer(){
//        //默认永不执行重试策略
        return Retryer.NEVER_RETRY;

        //最大请求次数为3(1+2)，初始间隔时间为100ms，重试的最大间隔时间为1s
//        return new Retryer.Default(100,1,3);
    }

    /**
     * 除了 HEADERS 中定义的信息之外，还有请求和响应的正文及元数据。
     * @return
     */
    @Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }
}
