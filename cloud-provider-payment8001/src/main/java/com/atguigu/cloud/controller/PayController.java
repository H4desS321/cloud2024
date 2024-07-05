package com.atguigu.cloud.controller;

import com.atguigu.cloud.entities.Pay;
import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import com.atguigu.cloud.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * ClassName: PayController
 * Package: com.atguigu.cloud.controller
 *
 * @Author 刘新雨
 * @Create 2024/6/25 10:13
 * @Version 1.0
 * Description:
 */
@RestController
@Slf4j
@Tag(name = "支付微服务模块",description = "支付CRUD")
public class PayController {
    @Resource
    private PayService payService;

    /**
     * 增加支付记录
     * @return
     */
    @PostMapping("/pay/add")
    @Operation(summary = "新增",description = "新增支付流水方法，json串做参数")
    public ResultData<String> addPay(@RequestBody Pay pay) {
        log.info(pay.toString());
        Integer rows = payService.add(pay);
        return ResultData.success("记录添加成功，返回值:" + rows);
    }

    /**
     * 删除记录
     * @param id
     * @return
     */
    @DeleteMapping("/pay/del/{id}")
    @Operation(summary = "删除",description = "删除支付流水方法")
    public ResultData<Integer> deleteById(@PathVariable("id") Integer id){
        Integer rows = payService.delete(id);
        return ResultData.success(rows);
    }

    /**
     * 更改记录
     * @param payDTO 前端传来的数据
     * @return
     */
    @PutMapping("/pay/update")
    @Operation(summary = "修改",description = "修改支付流水方法")
    public ResultData<String> updatePay(@RequestBody PayDTO payDTO){
        Pay pay = new Pay();
        BeanUtils.copyProperties(payDTO,pay);
        Integer rows = payService.update(pay);
        return ResultData.success("记录修改成功，返回值:" + rows);
    }

    /**
     * 按照主键查询
     * @param id
     * @return
     */
    @GetMapping("/pay/get/{id}")
    @Operation(summary = "按照ID查找流水",description = "查询支付流水方法")
    public ResultData<Pay> getById(@PathVariable("id") Integer id){
        if (id < 0){
            throw new RuntimeException("id不能为负数");
        }
        try {
            TimeUnit.SECONDS.sleep(62);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Pay pay = payService.getById(id);
        return ResultData.success(pay);
    }

    /**
     * 查询全部记录
     * @return
     */
    @GetMapping("/pay/getAll")
    @Operation(summary = "查找全部流水",description = "查询全部支付流水方法")
    public ResultData<List<Pay>> getAll(){
        List<Pay> payList = payService.getAll();
        return ResultData.success(payList);
    }

    @Value("${server.port}")
    private String port;
    /**
     * 测试环境：看能否调到配置中心的配置文件中的信息
     */
    @GetMapping("/pay/get/info")
    public String getInfoByConsul(@Value("${atguigu.info}") String atguiguInfo){
        return "atguiguInfo : " + atguiguInfo + "port : " + port;
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
