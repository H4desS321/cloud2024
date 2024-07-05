package com.atguigu.cloud.controller;

import com.atguigu.cloud.entities.Storage;
import com.atguigu.cloud.resp.ResultData;
import com.atguigu.cloud.service.StorageService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: StorageController
 * Package: com.atguigu.cloud.controller
 *
 * @Author 刘新雨
 * @Create 2024/7/3 21:41
 * @Version 1.0
 * Description:
 */
@RestController
public class StorageController {
    @Resource
    private StorageService storageService;

    @RequestMapping("/storage/decrease")
    public ResultData decrease(Long productId,Integer count){
        storageService.decrease(productId,count);
        return ResultData.success("扣减库存成功");
    }
}
