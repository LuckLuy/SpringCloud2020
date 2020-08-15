package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient /* 注册进Eureka*/
public class GateWayMain9527 {
  public static void main(String[] args) {
    SpringApplication.run(GateWayMain9527.class,args);
    System.err.println("9527 启动了。。。。。");
  }
}
