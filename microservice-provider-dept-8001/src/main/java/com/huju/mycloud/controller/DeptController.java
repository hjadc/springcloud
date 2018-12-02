package com.huju.mycloud.controller;

import com.huju.mycloud.entities.Dept;
import com.huju.mycloud.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by huju on 2018/11/25.
 */

@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    // 暴漏接口
    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value = "/dept/add", method = RequestMethod.POST)
    public boolean add(@RequestBody Dept dept) {
        return deptService.add(dept);
    }

    @RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
    public Dept get(@PathVariable("id") Long id) {
        System.out.println("节点一... 提莫队长正在待命!!!");
        return deptService.get(id);
    }

    @RequestMapping(value = "/dept/list", method = RequestMethod.GET)
    public List<Dept> list() {
        System.out.println("节点一... 德玛西亚!!!");
        return deptService.list();
    }


    @RequestMapping(value = "/dept/discovery", method = RequestMethod.GET)
    public Object discovery() {

        List<String> services = client.getServices();
        System.out.println("*********************** 服务:" + services);

        List<ServiceInstance> serviceInstanceList = client.getInstances("MICROSERVICECLOUD-DEPT");
        for (ServiceInstance serviceInstance : serviceInstanceList) {
            System.out.println(serviceInstance.getServiceId() + "\t" + serviceInstance.getHost() + "\t" + serviceInstance.getUri());
        }
        return this.client;
    }
}
