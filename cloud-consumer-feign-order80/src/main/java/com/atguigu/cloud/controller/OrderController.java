package com.atguigu.cloud.controller;

import cn.hutool.core.date.DateUtil;
import com.atguigu.cloud.apis.PayFeignApi;
import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import com.atguigu.cloud.resp.ReturnCodeEnum;
import jakarta.annotation.Resource;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * ClassName: OrderController
 * Package: com.atguigu.cloud.controller
 *
 * @Author 刘新雨
 * @Create 2024/6/25 19:51
 * @Version 1.0
 * Description:
 */
@RestController
public class OrderController {
    @Resource
    private PayFeignApi payFeignApi;

    @PostMapping("/feign/pay/add")
    public ResultData addOrder(@RequestBody PayDTO payDTO){
        System.out.println("第一步：模拟本地addOrder新增订单成功(省略sql操作)，第二步：再开启addPay支付微服务远程调用");
        return payFeignApi.addPay(payDTO);
    }


    @GetMapping("/feign/pay/get/{id}")
    public ResultData getById(@PathVariable("id") Integer id){
        System.out.println("-------支付微服务远程调用，按照id查询订单支付流水信息");
        ResultData resultData = null;
        try {
            System.out.println("调用开始  ：" + DateUtil.now());
            resultData = payFeignApi.getById(id);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("调用结束  ：" + DateUtil.now());
            ResultData.fail(ReturnCodeEnum.RC500.getCode(),e.getMessage());
        }
        return resultData;
    }

    @DeleteMapping("/feign/pay/del/{id}")
    public ResultData deleteById(@PathVariable("id") Integer id){
        return payFeignApi.deleteById(id);
    }

    @PutMapping("/feign/pay/update")
    public ResultData updatePay(@RequestBody PayDTO payDTO){
        return payFeignApi.updatePay(payDTO);
    }

    @GetMapping("/feign/pay/getAll")
    public ResultData getAll(){
        return payFeignApi.getAll();
    }

    /**
     * openfeign天然支持负载均衡演示
     * @return
     */
    @GetMapping("/feign/pay/get/info")
    public String testLoadBalanced(){
        System.out.println("openfeign天然支持负载均衡演示");
        return payFeignApi.testLoadBalanced();
    }

}
