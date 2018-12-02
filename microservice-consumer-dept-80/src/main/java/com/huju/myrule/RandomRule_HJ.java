package com.huju.myrule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.Server;

import java.util.List;
import java.util.Random;

/**
 * 自定义Ribbon的负载均衡机制,正常轮询且每台机器访问5次
 * Created by huju on 2018/12/2.
 */
public class RandomRule_HJ extends AbstractLoadBalancerRule {

    private int total = 0;          // 总共被调用的次数,目前要求每台被调用5次
    private int currantIndex = 0;   // 当前提供方服务的机器号


    public Server choose(ILoadBalancer lb, Object key) {
        if (lb == null) {
            return null;
        }

        Server server = null;
        while (server == null) {
            if (Thread.interrupted()) {
                return null;
            }

            // 活着的机器
            List<Server> upServers = lb.getReachableServers();
            // 所有的机器
            List<Server> allServers = lb.getAllServers();

            int serverCount = allServers.size();
            if (serverCount == 0) {
                return null;
            }

            /*int index = random.nextInt(serverCount);
            server = upServers.get(index);*/

            if (total < 5) {
                server = upServers.get(currantIndex);
                total++;
            } else {
                total = 0;
                currantIndex++;
                if (currantIndex >= upServers.size()) {
                    currantIndex = 0;
                }
            }

            if (server == null) {
                Thread.yield();
                continue;
            }

            if (server.isAlive()) {
                return (server);
            }

            server = null;
            Thread.yield();

        }
        return server;
    }

    @Override
    public Server choose(Object key) {
        return choose(getLoadBalancer(), key);
    }


    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }


}
