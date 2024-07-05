package com.atguigu.cloud.service;

/**
 * ClassName: StorageService
 * Package: com.atguigu.cloud.service.impl
 *
 * @Author 刘新雨
 * @Create 2024/7/3 21:37
 * @Version 1.0
 * Description:
 */
public interface StorageService {
    /**
     * 扣减库存
     */
    void decrease(Long productId,Integer count);
}
