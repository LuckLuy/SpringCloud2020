package com.atguigu.springcloud.controller;


import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

  @Value("${server.port}")
  private String serverPort;


  @Resource
  private PaymentService paymentService;


  @GetMapping ("/payment/hystrix/ok/{id}")
  public String paymentInfo_OK(@PathVariable("id") Integer id){

    String res = paymentService.paymentInfo_OK(id);

    log.info("*****res :"+res);
    return  res;
  }

  @GetMapping ("/payment/hystrix/timeout/{id}")
  public String paymentInfo_TimeOut(@PathVariable("id")Integer id){
    String res = paymentService.paymentInfo_TimeOut(id);

    log.info("*****res********** :"+res);
    return  res;
  }

  /**
   *  服务熔断
   * @param id
   * @return
   */
  @GetMapping("/payment/circuit/{id}")
  public  String paymentCircuitBreaker(@PathVariable("id") Integer id){
    String result = paymentService.paymentCiruitBreaker(id);
    log.info("0000000  result: "+result);
    return  result;
  }


}
