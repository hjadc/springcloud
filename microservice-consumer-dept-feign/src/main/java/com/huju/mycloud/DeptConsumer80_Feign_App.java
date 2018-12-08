package com.huju.mycloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * 部门的消费者
 * Created by huju on 2018/11/25.
 */

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages= {"com.huju.mycloud"})
@ComponentScan("com.huju.mycloud")   // 扫描的包
public class DeptConsumer80_Feign_App {

    public static void main(String[] args) {
        SpringApplication.run(DeptConsumer80_Feign_App.class, args);
    }
}
