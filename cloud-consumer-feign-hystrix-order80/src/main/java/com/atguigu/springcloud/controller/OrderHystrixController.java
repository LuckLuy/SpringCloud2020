package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "defaultFallback_Method")
public class OrderHystrixController {

  @Resource
  private PaymentHystrixService paymentHystrixService;


  @GetMapping("/consumer/payment/hystrix/ok/{id}")
  public String paymentInfo_OK (@PathVariable("id") Integer id){
    String result =paymentHystrixService.paymentInfo_OK(id);
    return result;
  }
  /**
   * 取消局部降级，改用全局降级，但是得在局部添加 ：@HystrixCommand 注解。
   */
  /* @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutFallbackMethod",commandProperties = {
      @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
  })*/
  @GetMapping(value = "/consumer/payment/hystrix/timeout/{id}")
  @HystrixCommand
  public String paymentInfo_TimeOut (@PathVariable("id") Integer id){
    int s=1/0;
    String res =paymentHystrixService.paymentInfo_TimeOut(id);
    return  res;
  }
  public String paymentInfo_TimeOutFallbackMethod (@PathVariable("id") Integer id){
    return "客服端服务降级 ........请稍后再试";
  }



  public String defaultFallback_Method (){
    return "客服端服务降级 ........请10秒以后再试！！！！！";
  }

}
