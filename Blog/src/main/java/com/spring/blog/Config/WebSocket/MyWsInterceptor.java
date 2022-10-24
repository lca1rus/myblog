package com.spring.blog.Config.WebSocket;

import com.spring.blog.Constants.RedisKeyConstants;
import com.spring.blog.Service.Impliment.RedisServiceImpl;
import com.spring.blog.uitl.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;
/*
MyInterceptor用来拦截ws请求，通过实现 HandshakeInterceptor 接口来定义握手拦截器，
注意这里与上面 Handler 的事件是不同的，这里是建立握手时的事件，分为握手前与握手后，
而 Handler 的事件是在握手成功后的基础上建立 socket 的连接。所以在如果把认证放在这个步骤相对来说最节省服务器资源。
它主要有两个方法 beforeHandshake 与 afterHandshake，顾名思义一个在握手前触发，一个在握手后触发。

 */
@Component
public class MyWsInterceptor implements HandshakeInterceptor {
    /**
     * 握手前
     * @param request
     * @param response
     * @param wsHandler
     * @param attributes
     * @return
     * @throws Exception
     */
    @Autowired
    RedisServiceImpl redisServiceimpl;//通过redis进行登录成功的验证
//每次进去聊天页面就从token里解码并且拿出name，并且每次进入都放一个用户的name进入redis中，用于统计在线人数
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        System.out.println("握手开始前");
        String jwtToken=request.getHeaders().getFirst("Authorization");
        if (jwtToken != null && !"".equals(jwtToken) && !jwtToken.equals("null")) {//jwtToken不能为null
            Claims claims = JwtUtils.jwtparser(jwtToken);//解码token
            String name = claims.getSubject();//获取当前登录用户名

            //每次用户登录redis中设置登录的情况

            String hash = RedisKeyConstants.USER_STATE;

            redisServiceimpl.saveKVToHash(hash,name,1);
            //每次登录进行设置值为1表示为在线，用websocket来判断是否断线，断线设置为0

            //用户的name当作token
            attributes.put("token",name);
            return true;
        }
        System.out.println("name为空");
        return false;
    }
    /**
     * 握手后
     * @param request
     * @param response
     * @param wsHandler
     * @param exception
     */
    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
        System.out.println("握手完成");
    }
}
