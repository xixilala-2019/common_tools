#### 1.导入到idea的项目报Could not create connection to database server

参考https://blog.csdn.net/qq_41999034/article/details/102545395解决

mysql 驱动包和本地安装的要一致

我电脑上的是 8.0.11

在pom.xml里配置

```xml
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <scope>runtime</scope>
            <version>8.0.11</version>
        </dependency>
```

#### 2.报错JedisConnectionException: Could not get a resource from the pool

本地没有安装 redis ,安装一个就可以了

#### 3.启动项目报错 

UnsatisfiedDependencyException: Error creating bean with name 'sqlSessionFactory

是因为数据库的配置没有搞好，搞成以下就可以

```
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid-spring-boot-starter</artifactId>
    <version>1.1.9</version>
</dependency>
```

之前的配置是 

```
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid</artifactId>
    <version>1.1.3</version>
</dependency>
```

#### 4.springboot 集成 mapper , lombok 一直不生效

试过网上的方法，springbootApplication注解加参数/idea设置/版本都不行，

最后发现，没有@Data注解

