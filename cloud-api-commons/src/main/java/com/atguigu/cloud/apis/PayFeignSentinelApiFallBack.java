package com.atguigu.cloud.apis;

import com.atguigu.cloud.resp.ResultData;
import com.atguigu.cloud.resp.ReturnCodeEnum;
import org.springframework.stereotype.Component;

/**
 * ClassName: PayFeignSentinelApiFallBack
 * Package: com.atguigu.cloud.apis
 *
 * @Author 刘新雨
 * @Create 2024/7/2 16:25
 * @Version 1.0
 * Description:
 */
@Component
public class PayFeignSentinelApiFallBack implements PayFeignSentinelApi
{
    @Override
    public ResultData getPayByOrderNo(String orderNo)
    {
        return ResultData.fail(ReturnCodeEnum.RC500.getCode(),"对方服务宕机或不可用，FallBack服务降级o(╥﹏╥)o");
    }
}