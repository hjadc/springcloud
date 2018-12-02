package com.huju.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * 自定义Ribbon的分配策略
 * 注意:这个自定义配置类不能放在 @ComponentScan 所扫描的当前包以及子包下
 * Created by huju on 2018/12/2.
 */

@Configuration
public class MySelfRule {

    @Bean
    public IRule myRule(){

        // new RoundRobinRule();   轮询
        // new RandomRule();       随机
        // new RetryRule();        重试机制,(默认轮询,当遇到一个服务多次连接失败后自动跳过该服务继续轮询别的服务)
        return new RandomRule();
    }
}
