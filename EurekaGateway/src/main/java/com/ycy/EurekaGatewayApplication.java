package com.ycy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
//允许zuul代理，配置session存储后立刻刷新设置刷新模式为立刻刷新，否则可能获取不到session。
//@EnableRedisHttpSession(redisFlushMode = RedisFlushMode.IMMEDIATE)
public class EurekaGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaGatewayApplication.class, args);
    }

}
