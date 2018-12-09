package com.huju.mycloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Created by huju on 2018/12/9.
 */

@SpringBootApplication
@EnableZuulProxy
public class Zuul_9527_StartSpringCloudApp
{
    public static void main(String[] args)
    {
        SpringApplication.run(Zuul_9527_StartSpringCloudApp.class, args);
    }
}