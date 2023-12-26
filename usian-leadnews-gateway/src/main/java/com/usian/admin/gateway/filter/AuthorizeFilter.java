package com.usian.admin.gateway.filter;

import com.usian.admin.gateway.utils.AppJwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Log4j2
public class AuthorizeFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //1.获取请求对象和响应对象
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        //2.配置放行资源，登录资源是需要放行的
        if(request.getURI().getPath().contains("/login/in")){
            //放行
            return chain.filter(exchange);
        }

        //3.获取当前请求的jwt
        HttpHeaders headers = request.getHeaders();
        String token = headers.getFirst("token");

        //4.判断客户端是否携带token
        if(StringUtils.isEmpty(token)){
            //如果为空，说明token不存在
            response.setStatusCode(HttpStatus.UNAUTHORIZED); //401
            //完成响应
            return response.setComplete();
        }

        //5.验证token逻辑
        /*
        [1]验证下token当前是否过期
        [2]是否合法
         */
        try {
            Claims claims = AppJwtUtil.getClaimsBody(token);
            int result = AppJwtUtil.verifyToken(claims);
            if (result == 0 || result == 1){
                //jwt 载荷中存储的信息，可以在服务端取出来，传递到请求中，获取
                Integer id = (Integer)claims.get("id");
                log.info("find userid:{} from uri:{}",id,request.getURI());
                //将用户id传递到下一个过滤器中  (1)ThreadLocal   (2)存储在请求头，这次请求没有结束一直携带
                ServerHttpRequest serverHttpRequest =
                        request.mutate().headers(httpHeaders -> httpHeaders.add("userId", id + ""))
                                .build();
                exchange.mutate().request(serverHttpRequest).build();
            }
        }catch (Exception e){
            e.printStackTrace();
            //给客户端返回错误提示信息
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }

        //6.如果上面没有任何异常产生，放行到下个过滤器
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
