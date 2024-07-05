package com.atguigu.cloud.service;

import com.atguigu.cloud.entities.Order;

/**
 * ClassName: OrderService
 * Package: com.atguigu.cloud.service
 *
 * @Author 刘新雨
 * @Create 2024/7/3 20:06
 * @Version 1.0
 * Description:
 */
public interface OrderService {

    /**
     * 创建订单
     */
    void create(Order order);
}
