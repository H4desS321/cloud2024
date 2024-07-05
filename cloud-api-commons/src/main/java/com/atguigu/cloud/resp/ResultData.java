package com.atguigu.cloud.resp;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * ClassName: ResultData
 * Package: com.atguigu.cloud.resp
 *
 * @Author 刘新雨
 * @Create 2024/6/25 15:25
 * @Version 1.0
 * Description:
 */
@Data
@Accessors(chain = true) //允许链式编程
public class ResultData<T> {
    private String code;
    private String message;
    private T data;
    private Long timeStamp;

    public ResultData() {
        this.timeStamp = System.currentTimeMillis();
    }

    /**
     * 成功
     * @param data
     * @return
     * @param <T>
     */
    public static <T> ResultData<T> success(T data){
        ResultData resultData = new ResultData<>();
        resultData.setCode(ReturnCodeEnum.RC200.getCode());
        resultData.setMessage(ReturnCodeEnum.RC200.getMessage());
        resultData.setData(data);
        return resultData;
    }


    /**
     * 失败 手动填入code和message
     * @param code
     * @param message
     * @return
     * @param <T>
     */
    public static <T> ResultData<T> fail(String code,String message){
        ResultData resultData = new ResultData<>();
        resultData.setCode(code);
        resultData.setMessage(message);
        resultData.setData(null);
        return resultData;
    }
}
