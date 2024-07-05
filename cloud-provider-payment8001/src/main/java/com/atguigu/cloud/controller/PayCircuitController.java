package com.atguigu.cloud.controller;

import cn.hutool.core.util.IdUtil;
import com.atguigu.cloud.entities.Pay;
import com.atguigu.cloud.resp.ResultData;
import com.atguigu.cloud.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * ClassName: PayCircuitController
 * Package: com.atguigu.cloud.controller
 *
 * @Author 刘新雨
 * @Create 2024/6/28 11:30
 * @Version 1.0
 * Description:
 */
@RestController
public class PayCircuitController
{
    //=========Resilience4j CircuitBreaker 的例子
    @GetMapping(value = "/pay/circuit/{id}")
    public String myCircuit(@PathVariable("id") Integer id)
    {
        if(id == -4) throw new RuntimeException("----circuit id 不能负数");
        if(id == 9999){
            try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }
        }
        return "Hello, circuit! inputId:  "+id+" \t " + IdUtil.simpleUUID();
    }

    ///=========Resilience4j bulkhead 的例子
    @GetMapping(value = "/pay/bulkhead/{id}")
    public String myBulkhead(@PathVariable("id") Integer id)
    {
        if(id == -4) {throw new RuntimeException("----bulkhead id 不能-4");}
        if(id == 9999)
        {
            try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }
        }
        return "Hello, bulkhead! inputId:  "+id+" \t " + IdUtil.simpleUUID();
    }


    //===========Resilience4j ratelimit 例子
    @GetMapping("/pay/ratelimit/{id}")
    public String myRateLimit(@PathVariable("id") Integer id){
        return "Hello,myreatelimit欢迎到来，inputId："+id+"\t"+IdUtil.simpleUUID();
    }
}