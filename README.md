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
    
### Hystrix (断路器)
    服务熔断
    1.服务提供方打开注解 @EnableCircuitBreaker
    2.在每个方法上打上注解: @HystrixCommand(fallbackMethod = "processHystrix_Get") 
        processHystrix_Get是一个出现异常时备用的方法

    服务降级 (整体资源快不够了，忍痛将某些服务先关掉，待渡过难关，再开启回来。)
    1.在api模块新建类DeptClientServiceFallbackFactory 实现 FallbackFactory
    2.实现create方法,把每个方法的异常备用类处理好
    3.当这些类调用的服务挂掉了,会自动调用在api模块新建类DeptClientServiceFallbackFactory的方法
    
### Hystrix Dashboard 图形化监控服务
    1.新建微服务
    2.添加依赖 (被监控的服务也需要引入该依赖)
    3.开启注解 @EnableHystrixDashboard 
    4.访问首页 localhost:9001/hystrix
    
    注意:
        1.被监控方也需要导入 spring-boot-starter-actuator
        2.被监控方需要开启Hystrix功能 @EnableCircuitBreaker
        3.因为需要开启了Hystrix功能,所以还需要导入 spring-cloud-starter-hystrix
    测试:
        1.启动 DeptConsumer_DashBoard_App
        2.启动 DeptProvider8001_Hystrix_App
        3.访问 http://localhost:8001/hystrix.stream 测试
        这个时候页面返回的是一对数据,要想用图形界面展示还需要配置
            1.在Hystrix Dashboard主界面的第一个窗口填入要监控的服务地址: http://localhost:8001/hystrix.stream
            2.在Delay窗口填入2000(代表两秒刷新一次),Title随便写(写demo01也行)
        当那个圆越大直线越高 说明访问压力越大
        
### zuul 提供=代理+路由+过滤三大功能
    1.新建一个zuul微服务模块
    2.引入依赖
    3.开启注解 @EnableZuulProxy
    3.修改hosts文件 127.0.0.1  myzuul.com
    
    测试:
        1.开启3个eureka集群
        2.开启一个服务提供方
        3.开启zuul服务
        不用路由测试: http://localhost:8001/dept/get/1
        用路由测试: http://myzuul.com:9527/microservicecloud-dept/dept/get/2
        
    配置服务的代理名称(在zuul的application.yml里配置):
    zuul:
      routes:
        mydept.serviceId: microservicecloud-dept  # 服务的真实地址
        mydept.path: /mydept/**                   # 服务的代理地址 配置后,用这个就代表是microservicecloud-dept
    访问测试: http://myzuul.com:9527/mydept/dept/get/2
    
    问题:
        这个时候真实名称http://myzuul.com:9527/microservicecloud-dept/dept/get/2和代理名称http://myzuul.com:9527/mydept/dept/get/2
        都能访问,可以设置把真实名称的访问禁掉
        zuul:
          ignored-services: microservicecloud-dept    # 已经有了代理名称,就把真实的服务名称给关掉(禁止直接用这个访问)
          # ignored-services: "*"                     # 禁用多个可以用*
          routes:
            mydept.serviceId: microservicecloud-dept  # 服务的真实地址
            mydept.path: /mydept/**                   # 服务的代理地址 配置后,用这个就代表是microservicecloud-dept
    设置统一访问前缀: prefix: /huju                    # 设置统一前缀,这样不能什么接口,前面都必须加上 huju才能访问的到