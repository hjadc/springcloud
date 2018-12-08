#spring cloud练习

###1.Ribbon
    Ribbon是基于客户端的负载均衡
    开启负载均衡与配置分配策略 com/huju/mycloud/config/ConfigBean.java:17 
    自定义轮询策略(每台机器访问5次) com/huju/myrule/RandomRule_HJ.java:16

### 项目说明
    1.microservice-api  -- 这是一个公用的包,目前就定义了一个公用的实体类
    2.microservice-consumer-dept-80 -- 这是面向客户端的工程,任何实现都是调用的远程服务
    3.microservice-eureka-7001  -- 这是Eureka集群节点一
    4.microservice-eureka-7002  -- 这是Eureka集群节点二
    5.microservice-eureka-7003  -- 这是Eureka集群节点三
    6.microservice-provider-dept-8001   -- 这是服务提供方集群节点一
    7.microservice-provider-dept-8002   -- 这是服务提供方集群节点二
    8.microservice-provider-dept-8003   -- 这是服务提供方集群节点三
    9.microservice-consumer-dept-feign  -- 这是面向客户端的工程,用feign来实现远程调用
    
### 项目启动

    准备工作:
    1.启动3个数据库 clouddb01.clouddb02.clouddb03
    2.修改hosts文件:
        # 搭建eureka集群
        127.0.0.1 eureka7001.com
        127.0.0.1 eureka7002.com
        127.0.0.1 eureka7003.com
    
    1.启动Eureka集群1.2.3       http://localhost:7001/ 可以进入Eureka的管理界面
    2.启动服务提供方1.2.3
    3.启动消费端
    4.访问 localhost:80/consumer/dept/list
    
### 搭建feign (其实现是注解加接口,底层好像是web service,feign的底层继承了Ribbon)
    1.在服务消费端和提供端添加feign的依赖
    2.在消费端的接口打上 @FeignClient(value = "MICROSERVICECLOUD-DEPT") 注解
    3.在每个抽象方法上打上 @RequestMapping(value = "/dept/list",method = RequestMethod.GET) 注解