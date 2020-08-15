package com.atguigu.springcloud.config;


import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *  配置路由网关转发
 */
//@Configuration
public class GateWayConfig {


  @Bean
  public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder){

    // 获取路由对象 yml 中的配置对象
    RouteLocatorBuilder.Builder routes =routeLocatorBuilder.routes();
    // 设置路由对象
    routes.route("path_route_atguigu",
        r-> r.path("/guonei")
            .uri("http://news.baidu.com/guonei")).build();

    return  routes.build();
  }
//


  @Bean
  public RouteLocator customRouteLocator2(RouteLocatorBuilder routeLocatorBuilder){

    // 获取路由对象 yml 中的配置对象
    RouteLocatorBuilder.Builder routes =routeLocatorBuilder.routes();
    // 设置路由对象
    routes.route("path_route_guoji",
        r-> r.path("/guoji")
            .uri("http://news.baidu.com/guoji")).build();

    return  routes.build();
  }
}
