package com.atguigu.cloud.controller;

import com.atguigu.cloud.entities.Order;
import com.atguigu.cloud.resp.ResultData;
import com.atguigu.cloud.service.OrderService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.transform.Result;

/**
 * ClassName: OrderController
 * Package: com.atguigu.cloud.controller
 *
 * @Author 刘新雨
 * @Create 2024/7/3 21:00
 * @Version 1.0
 * Description:
 */
@RestController
public class OrderController {

    @Resource
    private OrderService orderService;

    /**
     * 创建订单
     * @param order
     * @return
     */
    @GetMapping("/order/create")
    public ResultData<Order> create(Order order){
        orderService.create(order);
        return ResultData.success(order);
    }
}
