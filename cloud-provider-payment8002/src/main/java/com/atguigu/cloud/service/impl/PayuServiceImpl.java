package com.atguigu.cloud.service.impl;

import com.atguigu.cloud.entities.Pay;
import com.atguigu.cloud.mapper.PayMapper;
import com.atguigu.cloud.service.PayService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: PayuServiceImpl
 * Package: com.atguigu.cloud.service.impl
 *
 * @Author 刘新雨
 * @Create 2024/6/25 10:05
 * @Version 1.0
 * Description:
 */
@Service
public class PayuServiceImpl implements PayService {
    @Resource
    private PayMapper payMapper;

    @Override
    public Integer add(Pay pay) {
        //若插入数据为null，则会按照数据库默认值插入（insert()的话则不会按照数据库默认值插入，只会插入null）
        return payMapper.insertSelective(pay);
    }

    @Override
    public Integer delete(Integer id) {
        return payMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer update(Pay pay) {
        return payMapper.updateByPrimaryKeySelective(pay);
    }

    @Override
    public Pay getById(Integer id) {
        return payMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Pay> getAll() {
        return payMapper.selectAll();
    }
}
