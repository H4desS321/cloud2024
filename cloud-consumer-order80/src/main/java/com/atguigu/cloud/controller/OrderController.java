package com.atguigu.cloud.controller;

import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.ResultData;
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
    //硬编码 写死的
//    public static final String PaymentSrv_URL = "http://localhost:8001";

    //服务注册中心上的微服务名称
    public static final String PAYMENT_SRV_URL = "http://cloud-payment-service";

    @Resource
    private RestTemplate restTemplate;

    /**
     * 新增流水信息
     * @param payDTO
     * @return
     */
    @PostMapping("/consumer/pay/add")
    public ResultData addOrder(@RequestBody PayDTO payDTO){
        return restTemplate.postForObject(PAYMENT_SRV_URL+"/pay/add",payDTO,ResultData.class);
    }

    /**
     * 修改流水信息
     * @param payDTO
     * @return
     */
    @PutMapping("/consumer/pay/update")
    public ResultData updateOrder(@RequestBody PayDTO payDTO){
        restTemplate.put(PAYMENT_SRV_URL+"/pay/update",payDTO);
        return ResultData.success("修改成功");
    }

    /**
     * 删除流水信息
     * @param id
     * @return
     */
    @DeleteMapping("/consumer/pay/del/{id}")
    public ResultData deleteOrder(@PathVariable("id") Integer id){
        restTemplate.delete(PAYMENT_SRV_URL+"/pay/del/"+id);
        return ResultData.success("删除成功");
    }
    /**
     * 根据id查询信息
     * @param id
     * @return
     */
    @GetMapping("/consumer/pay/get/{id}")
    public ResultData getPayInfo(@PathVariable("id") Integer id){
        return restTemplate.getForObject(PAYMENT_SRV_URL+"/pay/get/"+id,ResultData.class,id);
    }

    /**
     * 查询全部信息
     * @return
     */
    @GetMapping("/consumer/pay/getAll")
    public ResultData getAll(){
        return restTemplate.getForObject(PAYMENT_SRV_URL+"/pay/getAll",ResultData.class);
    }

    /**
     *
     * @return
     */
    @GetMapping("/consumer/pay/get/info")
    public String getInfoByConsul(){
        return restTemplate.getForObject(PAYMENT_SRV_URL+"/pay/get/info", String.class);
    }


    @Resource
    private DiscoveryClient discoveryClient;

    /**
     * 使用 DiscoveryClient 动态获取所有上线的服务列表
     * @return
     */
    @GetMapping("/consumer/discovery")
    public String discoveryClient(){
        List<String> services = discoveryClient.getServices();
        for (String item:services) {
            System.out.println(item);
        }
        System.out.println("=====================");

        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        for (ServiceInstance item:instances) {
            System.out.println(item.getUri());
        }
        return instances.get(0).getServiceId()+":"+instances.get(0).getPort();
    }

}
