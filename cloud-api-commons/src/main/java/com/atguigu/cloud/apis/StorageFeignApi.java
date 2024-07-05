package com.atguigu.cloud.apis;

import com.atguigu.cloud.resp.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.xml.transform.Result;

/**
 * ClassName: StorageFeignApi
 * Package: com.atguigu.cloud.apis
 *
 * @Author 刘新雨
 * @Create 2024/7/3 19:27
 * @Version 1.0
 * Description:
 */
@FeignClient("seata-storage-service")
public interface StorageFeignApi {

    /**
     * 扣减库存
     * @param productId
     * @param count
     * @return
     */
    @PostMapping(value = "/storage/decrease")
    ResultData decrease(@RequestParam("productId") Long productId,@RequestParam("count") Integer count);
}
