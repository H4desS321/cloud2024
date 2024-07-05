package com.atguigu.cloud.myGateWay;

import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.server.ServerWebExchange;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

/**
 * ClassName: MyRoutePredicateFactory
 * Package: com.atguigu.cloud.myGateWay
 *
 * @Author 刘新雨
 * @Create 2024/6/30 13:35
 * @Version 1.0
 * Description:
 * 需求：自定义配置会员等级 userType
 * 按照青铜/白银/黄金/铂金/钻石和yml配置 会员等级 以适配是否可以访问
 */
@Component
public class MyRoutePredicateFactory extends AbstractRoutePredicateFactory<MyRoutePredicateFactory.Config> {
    public MyRoutePredicateFactory() {
        super(MyRoutePredicateFactory.Config.class);
    }

    /**
     * 这个Config类就是我们的路由断言规则，重要！！
     */
    @Validated
    public static class Config{
        @Setter @Getter @NotEmpty
        private String userType; //按照青铜/白银/黄金/铂金/钻石
    }

    @Override
    public Predicate<ServerWebExchange> apply(MyRoutePredicateFactory.Config config) {
        return new Predicate<ServerWebExchange>() {
            @Override
            public boolean test(ServerWebExchange serverWebExchange) {
                //检查request中的参数，userType是否为指定的值，符合配置就通过
                String userType = serverWebExchange.getRequest().getQueryParams().getFirst("userType");
                if (null == userType){
                    return false;
                }
                //若参数存在，就和config中的数据进行比较
                if (userType.equalsIgnoreCase(config.getUserType())){
                    return true;
                }
                return false;
            }
        };
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList("userType");
    }
}
