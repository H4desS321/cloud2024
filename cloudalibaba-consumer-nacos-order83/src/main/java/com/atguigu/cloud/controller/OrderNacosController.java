package com.atguigu.cloud.controller;

import com.atguigu.cloud.apis.PayFeignSentinelApi;
import com.atguigu.cloud.resp.ResultData;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * ClassName: OrderNacosController
 * Package: com.atguigu.cloud.controller
 *
 * @Author 刘新雨
 * @Create 2024/7/1 10:17
 * @Version 1.0
 * Description:
 */
@RestController
public class OrderNacosController
{
    @Resource
    private RestTemplate restTemplate;

    @Value("${service-url.nacos-user-service}")
    private String serverURL;

    @GetMapping("/consumer/pay/nacos/{id}")
    public String paymentInfo(@PathVariable("id") Integer id)
    {
        String result = restTemplate.getForObject(serverURL + "/pay/nacos/" + id, String.class);
        return result+"\t"+"    我是OrderNacosController83调用者。。。。。。";
    }


    //********************************************


    @Resource
    private PayFeignSentinelApi payFeignSentinelApi;

    @GetMapping(value = "/consumer/pay/nacos/get/{orderNo}")
    public ResultData getPayByOrderNo(@PathVariable("orderNo") String orderNo)
    {
        return payFeignSentinelApi.getPayByOrderNo(orderNo);
    }
}
