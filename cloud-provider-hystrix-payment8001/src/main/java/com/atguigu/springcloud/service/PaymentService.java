package com.atguigu.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {


  /**
   *
   * 正常访问
   * @param id
   * @return
   */
  public String paymentInfo_OK(Integer id){
    return  "线程池："+Thread.currentThread().getName()+" paymentInfo_ok,id:" +
        +id +"\t" +"哈哈哈";
  }


  /**
   * 设置超时访问
   * @param id
   * @HystrixCommand: 出现异常超时 调用这个方法
   *  服务降级 一般设置在客服端
   *  1,超时异常.
   *  2,计算异常 .
   *  当前服务不可用来,做服务降级.兜底的方案都是 paymentInfo_TimeOutHandler
   * @return
   */

  @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = {
      @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
  })
  public String paymentInfo_TimeOut(Integer id){
  // 1,超时异常
    int timeOut =3;
    try {
      TimeUnit.SECONDS.sleep(timeOut);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
   //2 ,计算异常
    //int age =10/0;

    return  "线程池："+Thread.currentThread().getName()+" paymentInfo_TimeOut,id:" +
        +id  +"哈哈哈" +"耗时(秒):";
  }



  public String paymentInfo_TimeOutHandler(Integer id){
    return  "线程池："+Thread.currentThread().getName()+" paymentInfo_TimeOut,id:" +
        +id  +"服务繁忙,请稍后再试";
  }


  /**
   *  服务熔断
   */

  @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
      @HystrixProperty(name="circuitBreaker.enabled",value = "true"),//是否开启短路器
      @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value = "10"),//请求次数
      @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value = "1000"),//时间窗口期
      @HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value = "60"),//失败率达到多少跳闸
  })
  public String  paymentCiruitBreaker(@PathVariable("id") Integer id){
    if(id<0){
      throw new RuntimeException("*********** id 不能为负数！！！");
    }
    // 随机生成uuid
    String serialNumber = IdUtil.simpleUUID();
    return  Thread.currentThread().getName()+",调用成功，流水号： "+serialNumber;

  }


  public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id ){
    return "id 不能为负数，请稍后再试。 id："+id;
  }














}
