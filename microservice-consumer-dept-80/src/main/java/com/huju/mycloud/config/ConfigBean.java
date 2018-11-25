package com.huju.mycloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Created by huju on 2018/11/25.
 */

@Configuration
public class ConfigBean {

    @Bean
    public RestTemplate getRestTemplate(){

        return new RestTemplate();
    }
}
