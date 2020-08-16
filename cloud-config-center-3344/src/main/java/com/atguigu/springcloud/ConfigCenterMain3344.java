package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import javax.security.auth.login.Configuration;

@SpringBootApplication
@EnableConfigServer
public class ConfigCenterMain3344 {

  public static void main(String[] args) {
    SpringApplication.run(ConfigCenterMain3344.class,args);

    System.err.println("config 启动成功！！！");
  }
}
