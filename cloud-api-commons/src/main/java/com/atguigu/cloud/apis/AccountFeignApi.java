package com.atguigu.cloud.apis;

import com.atguigu.cloud.resp.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * ClassName: AccountFeignApi
 * Package: com.atguigu.cloud.apis
 *
 * @Author 刘新雨
 * @Create 2024/7/3 19:31
 * @Version 1.0
 * Description:
 */
@FeignClient(value="seata-account-service")
public interface AccountFeignApi {
    //扣减账户余额
    @PostMapping("/account/decrease")
    ResultData decrease(@RequestParam("userId") Long userId,@RequestParam("money") Long money);
}
