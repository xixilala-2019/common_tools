1.导入到idea的项目报Could not create connection to database server

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

2.报错JedisConnectionException: Could not get a resource from the pool

本地没有安装 redis ,安装一个就可以了

