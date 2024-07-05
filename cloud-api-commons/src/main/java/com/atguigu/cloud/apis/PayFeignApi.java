package com.atguigu.cloud.apis;

import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName: PayFeignApi
 * Package: com.atguigu.cloud.apis
 *
 * @Author 刘新雨
 * @Create 2024/6/27 10:05
 * @Version 1.0
 * Description: 对外暴露的接口（相当于封装了controller）
 */
//@FeignClient(value = "cloud-payment-service") //公司内部访问可以直接访问微服务
@FeignClient(value = "cloud-gateway") //若放置公网上，应该访问网关
public interface PayFeignApi {

    /**
     * 新增一条支付流水记录
     * @param payDTO
     * @return
     */
    @PostMapping("/pay/add")
    public ResultData addPay(@RequestBody PayDTO payDTO);

    /**
     * 删除一条支付流水记录
     * @param id
     * @return
     */
    @DeleteMapping("/pay/del/{id}")
    public ResultData deleteById(@PathVariable("id") Integer id);

    /**
     * 更新一条支付流水记录
     * @param payDTO
     * @return
     */
    @PutMapping("/pay/update")
    public ResultData<String> updatePay(@RequestBody PayDTO payDTO);

    /**
     * 根据ID查询一条支付流水记录
     * @param id
     * @return
     */
    @GetMapping("/pay/get/{id}")
    public ResultData getById(@PathVariable("id") Integer id);

    /**
     * 查询全部流水记录
     * @return
     */
    @GetMapping("/pay/getAll")
    public ResultData getAll();

    /**
     * 测试openfeign自带的负载均衡
     * @return
     */
    @GetMapping("/pay/get/info")
    public String testLoadBalanced();


    /**
     * Resilience4j CircuitBreaker 的例子
     * @param id
     * @return
     */
    @GetMapping(value = "/pay/circuit/{id}")
    public String myCircuit(@PathVariable("id") Integer id);


    /**
     * Resilience4j BulkHead例子
     * @param id
     * @return
     */
    @GetMapping("pay/bulkhead/{id}")
    public String myBulkHead(@PathVariable("id") Integer id);


    /**
     *Reilience4j ratelimit 例子
     */
    @GetMapping("pay/ratelimit/{id}")
    public String myRateLimit(@PathVariable("id") Integer id);


    /**
     * Micrometer(Sleuth)进行链路监控的例子
     * @param id
     * @return
     */
    @GetMapping(value = "/pay/micrometer/{id}")
    public String myMicrometer(@PathVariable("id") Integer id);


    /**
     * GateWay进行网关测试案例01
     * @param id
     * @return
     */
    @GetMapping(value = "/pay/gateway/get/{id}")
    public ResultData getById2(@PathVariable("id") Integer id);

    /**
     * GateWay进行网关测试案例02
     * @return
     */
    @GetMapping(value = "/pay/gateway/info")
    public ResultData<String> getGatewayInfo();
}
