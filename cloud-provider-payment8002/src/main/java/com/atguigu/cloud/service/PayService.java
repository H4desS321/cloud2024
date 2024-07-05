package com.atguigu.cloud.service;

import com.atguigu.cloud.entities.Pay;

import java.util.List;

/**
 * ClassName: PayService
 * Package: com.atguigu.cloud.service
 *
 * @Author 刘新雨
 * @Create 2024/6/25 10:01
 * @Version 1.0
 * Description:
 */
public interface PayService {
    //增
    Integer add(Pay pay);
    //删
    Integer delete(Integer id);
    //改
    Integer update(Pay pay);
    //根据id查
    Pay getById(Integer id);
    //查找全部
    List<Pay> getAll();
}
