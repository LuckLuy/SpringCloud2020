package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
public class PaymentController {

  @Resource
  private PaymentService paymentService;


  @Value("${server.port}")
  private  String serverPort;


  @Resource /* 通过服务发现自己的信息 */
  private DiscoveryClient discoveryClient;



  @PostMapping(value = "/payment/create")
  public CommonResult create(Payment payment) {
    int rows = paymentService.create(payment);
    log.info("新增成功！"+rows);
    if (rows > 0) {
      return new CommonResult(200, "新增成功.serverPort: "+serverPort, rows);
    } else {
      return new CommonResult(200, "新增失败.", rows);
    }
  }

  //通过 @PathVariable 可以将URL中占位符参数{xxx}绑定到处理器类的方法形参中@PathVariable(“xxx“)
  @GetMapping(value = "/payment/get/{id}")
  public CommonResult getPaymentById(@PathVariable("id") Long id) {
    Payment rs = paymentService.getPaymentById(id);
    if (rs != null) {
      return new CommonResult(200, "查询成功.serverPort: "+serverPort, rs);
    } else {
      return new CommonResult(200, "cx失败.", rs);
    }
  }

  @GetMapping(value = "/payment/discovery")
  public Object discovery(){

    List<String> service = discoveryClient.getServices();
    for(String element : service){
      log.info("* element: "+ element);
    }
    /* ServiceInstance:表示发现系统中服务的实例。 */
    List<ServiceInstance> instances= discoveryClient.getInstances("cloud-payment-service");

    for(ServiceInstance instance : instances){
      log.info(instance.getInstanceId() + "\t" + instance.getHost()+"\t" +
          + instance.getPort()+"\t" +instance.getUri());
    }
    return discoveryClient;
  }


  /* 测试ribbon */

  @GetMapping(value="/payment/lb")
  public String getPaymentLB(){
    return serverPort;
  }


  /* 测试feign */
  @GetMapping(value = "/payment/feign/timeout")
  public String paymentFeignTimeOut(){
    try {
      TimeUnit.SECONDS.sleep(3);
    }catch ( Exception e){
      e.printStackTrace();
    }
    return serverPort;
  }

}
