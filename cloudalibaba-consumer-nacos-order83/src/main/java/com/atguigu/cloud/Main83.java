package com.atguigu.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * ClassName: Main83
 * Package: com.atguigu.cloud
 *
 * @Author 刘新雨
 * @Create 2024/7/1 10:11
 * @Version 1.0
 * Description:
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class Main83
{
    public static void main(String[] args)
    {
        SpringApplication.run(Main83.class,args);
    }
}

