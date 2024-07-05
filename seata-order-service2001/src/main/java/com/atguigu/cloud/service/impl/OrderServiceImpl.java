package com.atguigu.cloud.service.impl;

import com.atguigu.cloud.apis.AccountFeignApi;
import com.atguigu.cloud.apis.StorageFeignApi;
import com.atguigu.cloud.entities.Order;
import com.atguigu.cloud.mapper.OrderMapper;
import com.atguigu.cloud.service.OrderService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

/**
 * ClassName: OrderServiceImpl
 * Package: com.atguigu.cloud.service.impl
 *
 * @Author 刘新雨
 * @Create 2024/7/3 20:07
 * @Version 1.0
 * Description:
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;
    @Resource //订单微服务通过OpenFeign去调用库存微服务
    private StorageFeignApi storageFeignApi;
    @Resource //订单微服务通过OpenFeign去调用账户微服务
    private AccountFeignApi accountFeignApi;

    @Override
    @GlobalTransactional(name = "lxy-create-order",rollbackFor = Exception.class)//AT
    public void create(Order order) {
        //xid全局事务id的检查，重要
        String xid = RootContext.getXID();
        //1.新建订单
        log.info("==================>开始新建订单"+"\t"+"xid_order:" +xid);
        //订单新建时，默认订单初始状态为0
        order.setStatus(0);
        int result = orderMapper.insertSelective(order);
        //若插入订单成功后，获得插入mysql的实体对象
        Order orderFromDB = null;
        if (result > 0){
            //从数据库mysql中查出刚插入的数据
            orderFromDB = orderMapper.selectOne(order);
            log.info("-------> 新建订单成功，orderFromDB info: "+orderFromDB);
            System.out.println();
            //2.扣减库存商品数量
            log.info("-------> 订单微服务开始调用Storage库存，做扣减count");
            storageFeignApi.decrease(orderFromDB.getProductId(),orderFromDB.getCount());
            log.info("-------> 订单微服务结束调用Storage库存，做扣减完成");
            System.out.println();
            //3.扣减用户余额
            log.info("-------> 订单微服务开始调用Account账号，做扣减money");
            accountFeignApi.decrease(orderFromDB.getUserId(),orderFromDB.getMoney());
            log.info("-------> 订单微服务结束调用Account账号，做扣减完成");
            System.out.println();
            //4.更改订单状态
            log.info("-------> 修改订单状态");
            orderFromDB.setStatus(1);

            Example example = new Example(Order.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("id",orderFromDB.getId());
            criteria.andEqualTo("status",0);


            //5.将更改状态后的订单信息插入数据库
            int updateResult = orderMapper.updateByExampleSelective(orderFromDB, example);
            log.info("-------> 修改订单状态完成"+"\t"+updateResult);
            log.info("-------> orderFromDB info: "+orderFromDB);
        }
        System.out.println();
        log.info("------------结束新建订单:"+"xid : "+xid);
    }
}
