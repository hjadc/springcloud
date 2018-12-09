package com.huju.mycloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * HystrixDashboard 图形化监控服务
 *
 * http://localhost:9001/hystrix
 *
 * Created by huju on 2018/12/9.
 */
@SpringBootApplication
@EnableHystrixDashboard // 开启图形化仪表盘的监控的注解
public class DeptConsumer_DashBoard_App {

    public static void main(String[] args) {
        SpringApplication.run(DeptConsumer_DashBoard_App.class, args);
    }

}
