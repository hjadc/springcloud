package com.huju.mycloud;

import com.huju.myrule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * 部门的消费者
 * Created by huju on 2018/11/25.
 */

@SpringBootApplication
@EnableEurekaClient
// 对 MICROSERVICECLOUD-DEPT 服务使用自定义的分配策略
@RibbonClient(name = "MICROSERVICECLOUD-DEPT", configuration = MySelfRule.class)
public class DeptConsumer80_App {

    public static void main(String[] args) {
        SpringApplication.run(DeptConsumer80_App.class, args);
    }
}
