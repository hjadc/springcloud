package com.huju.mycloud.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RetryRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Ribbon的负载均衡策略有七个,默认是轮询的
 * Created by huju on 2018/11/25.
 */
@Configuration
public class ConfigBean {

    @Bean
    @LoadBalanced   // 开启Ribbon的负载均衡
    public RestTemplate getRestTemplate(){

        return new RestTemplate();
    }

    /**
     * 使用随机分配的负载均衡策略替换掉轮询
     * 需要什么策略就new 什么算法
     * @return
     */
    @Bean
    public IRule myRule(){

        // new RoundRobinRule();   轮询
        // new RandomRule();       随机
        // new RetryRule();        重试机制,(默认轮询,当遇到一个服务多次连接失败后自动跳过该服务继续轮询别的服务)
        return new RoundRobinRule();
    }
}
