package com.spring.blog.Config.WebSocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.*;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;
//通过实现 WebSocketConfigurer 类并覆盖相应的方法进行 websocket 的配置。
// 我们主要覆盖 registerWebSocketHandlers 这个方法。
// 通过向 WebSocketHandlerRegistry 设置不同参数来进行配置。
// 其中 addHandler方法添加我们的 ws 的 handler 处理类，第二个参数是你暴露出的 ws 路径。
// addInterceptors添加我们写的拦截器。setAllowedOrigins这个是关闭跨域校验。
//1.@Configuration：注解标识该类为Spring的配置类
//
// 2.@EnableWebSocket：开启注解接收和发送消息
@Configuration
@EnableWebSocket
public class WebsocketConfig implements WebSocketConfigurer {
    //如果使用了springboot启动项目，则需要bean注入，而如果使用了外置tomcat容器，则并不要bean注入，否侧会报错
    @Bean
    public ServerEndpointExporter serverEndpoint() {
        return new ServerEndpointExporter();
    }

    @Autowired
    private MyWsAuthHandler httpAuthHandler;
    @Autowired
    private MyWsInterceptor myInterceptor;

    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
       registry
                //自定义的websocket服务,这里都可以定义多个
               .addHandler(httpAuthHandler,"ws")
                //设置拦截器
                .addInterceptors(myInterceptor)
                //设置登录用户检查
                //.setHandshakeHandler(myPrincipalHandshakeHandler)
                //关闭跨域校验
               .setAllowedOrigins("*");
    }

/*
1.@Configuration：注解标识该类为Spring的配置类

2.@EnableWebSocket：开启注解接收和发送消息

3.实现WebSocketConfigurer接口，重写registerWebSocketHandlers方法，这是一个核心实现方法，
配置websocket入口，允许访问的域、注册Handler、定义拦截器。客户端通过“/myHandler/{ID}”直接访问Handler核心类，
进行socket的连接、接收、发送等操作，这里由于还加了个拦截器，所以建立新的socket访问时，都先进来拦截器再进去Handler类，
————————————————
 */
}
