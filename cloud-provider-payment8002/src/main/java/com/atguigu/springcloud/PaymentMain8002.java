package com.atguigu.springcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@MapperScan("com.atguigu.springcloud.dao")
@EnableEurekaClient
public class PaymentMain8002 {

  public static void main(String[] args) {
    SpringApplication.run(PaymentMain8002.class,args);
    System.out.println("ヾ(◍°∇°◍)ﾉﾞ    bootdo启动成功      ヾ(◍°∇°◍)ﾉﾞ\n" +
        " ______                    _   ______            \n" +
        "|_   _ \\                  / |_|_   _ `.          \n" +
        "  | |_) |   .--.    .--. `| |-' | | `. \\  .--.   \n" +
        "  |  __'. / .'`\\ \\/ .'`\\ \\| |   | |  | |/ .'`\\ \\ \n" +
        " _| |__) || \\__. || \\__. || |, _| |_.' /| \\__. | \n" +
        "|_______/  '.__.'  '.__.' \\__/|______.'  '.__.'  ");
  }
}
