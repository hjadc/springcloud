package com.huju.mycloud.controller;

import com.alibaba.fastjson.JSONObject;
import com.huju.mycloud.entities.Dept;
import com.huju.mycloud.service.DeptService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by huju on 2018/11/25.
 */

@RestController
public class DeptController {

    // 一旦调用服务方法失败并抛出了错误信息后，会自动调用@HystrixCommand标注好的fallbackMethod调用类中的指定方法

    @Autowired
    private DeptService service = null;
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
    // 服务熔断机制,如果服务中出现异常,就调用备用方法
    @HystrixCommand(fallbackMethod = "processHystrix_Get")
    public Dept get(@PathVariable("id") Long id) {
        Dept dept = this.service.get(id);
        if (null == dept) {
            throw new RuntimeException("该ID：" + id + "没有没有对应的信息");
        }
        return dept;
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {

        for (int i = 0; i < 5000; i++) {
            JSONObject forObject = restTemplate.getForObject("http://127.0.0.1:8001/dept/get/1", JSONObject.class);
            System.out.println("第" + i + "次访问" + forObject);
        }
        return "请求成功!";
    }

    public Dept processHystrix_Get(@PathVariable("id") Long id) {
        return new Dept().setDeptno(id)
                .setDname("该ID：" + id + "没有对应的信息,null--@HystrixCommand")
                .setDb_source("no this database in MySQL");
    }

}
