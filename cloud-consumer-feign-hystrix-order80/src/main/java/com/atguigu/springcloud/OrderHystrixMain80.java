package com.atguigu.springcloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableHystrix /* 激活 服务降级*/
public class OrderHystrixMain80 {
  public static void main(String[] args) {
    SpringApplication.run(OrderHystrixMain80.class,args);
    System.out.println("feign 启动了");
  }

}
