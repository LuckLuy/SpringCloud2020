package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope/* 添加刷新注解 */
public class ConfigClientController {

  /**
   *  获取配置文件中的config 路径 获取yml中的信息。
    */
  @Value("${config.info}")
  private String configInfo;


  /**
   *  返回 配置文件中对应的 yml文件的信息
   *  如：dev：master branch,springcloud-config/config-dev.yml version=1
   * @return
   */
  @GetMapping("/configInfo")
  public String getConfigInfo() {
    return configInfo;
  }

}
