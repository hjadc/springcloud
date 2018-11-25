package com.huju.mycloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * eureka注册中心
 * Created by huju on 2018/11/25.
 */
@EnableEurekaServer     // 开启Eureka服务 ,接受其他微服务注册进来
@SpringBootApplication
public class EurekaServer7001_App {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServer7001_App.class, args);
    }
}
