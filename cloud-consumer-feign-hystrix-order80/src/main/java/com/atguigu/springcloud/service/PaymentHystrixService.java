package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *  @FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT",
 *  fallback = PaymentFallbackService.class)
 *  给整个接口添加 兜底的类。类里有各个接口的方法
 */
@Component
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT",fallback = PaymentFallbackService.class)
public interface PaymentHystrixService {



  @GetMapping(value = "/payment/hystrix/ok/{id}")
  public String paymentInfo_OK (@PathVariable("id") Integer id);


  @GetMapping(value = "/payment/hystrix/timeout/{id}")
  public String paymentInfo_TimeOut (@PathVariable("id") Integer id);


}
