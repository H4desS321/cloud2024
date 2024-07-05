package com.atguigu.cloud.service;

import org.apache.ibatis.annotations.Param;

/**
 * ClassName: AccountService
 * Package: com.atguigu.cloud.service
 *
 * @Author 刘新雨
 * @Create 2024/7/3 22:17
 * @Version 1.0
 * Description:
 */
public interface AccountService {
    /**
     * 扣除账户
     * @param userId
     * @param money
     */
    void decrease(@Param("userId") Long userId,@Param("money") Long money);
}
