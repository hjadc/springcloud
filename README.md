#spring cloud练习

###1.整合swagger-ui
###2.整合redis
    修改redis的默认序列化为json(默认为jdk)
    练习各种存储方式使用的方法
###3.读取配置文件的值和参数校验的使用
    注入自定义的bean
    读取多配置文件
    练习yml文件数据的各种写法
###4.slf4日志

###5.crud练习
    整合mybatis
    配置Druid数据源与Druid监控
    thymeleaf模板的使用
    国际化
    配置默默人首页
    spring mvc的权限拦截
    抽取公用的菜单列表
    完成增删改查
   
###6.定制异常页面
###7.注册servlet三大件

### 项目说明
    1.microservice-api  -- 这是一个公用的包,目前就定义了一个公用的实体类
    2.microservice-consumer-dept-80 -- 这是面向客户端的工程,任何实现都是调用的远程服务
    3.microservice-eureka-7001  -- 这是Eureka集群节点一
    4.microservice-eureka-7002  -- 这是Eureka集群节点二
    5.microservice-eureka-7003  -- 这是Eureka集群节点三
    6.microservice-provider-dept-8001   -- 这是服务提供方集群节点一
    7.microservice-provider-dept-8002   -- 这是服务提供方集群节点二
    8.microservice-provider-dept-8003   -- 这是服务提供方集群节点三
    
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