package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * 兜底的类。需要 实现接口。
 */
@Component
public class PaymentFallbackService implements PaymentHystrixService{
  @Override
  public String paymentInfo_OK(Integer id) {
    return "---PaymentFallbackService fall paymentInfo_OK,  o   k ";
  }

  @Override
  public String paymentInfo_TimeOut(Integer id) {
    return "---PaymentFallbackService fall paymentInfo_TimeOut,  timeout ";

  }
}
