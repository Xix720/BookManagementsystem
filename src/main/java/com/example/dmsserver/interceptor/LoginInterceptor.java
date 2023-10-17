package com.example.dmsserver.interceptor;

import com.example.dmsserver.commons.exception.GraceException;
import com.example.dmsserver.commons.result.ResponseStatusEnum;
import com.example.dmsserver.commons.util.JwtUtils;
import com.example.dmsserver.commons.vo.LoginUserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    private static final Logger LOG = LoggerFactory.getLogger(LogInterceptor.class);

    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LOG.info("登录拦截器开始......");
//        return true;

        // OPTIONS请求不做校验,
        // 前后端分离的架构, 前端会发一个OPTIONS请求先做预检, 对预检请求不做校验
        if(request.getMethod().toUpperCase().equals("OPTIONS")){
            return true;
        }

        //获取header的token参数
        String token = request.getHeader("Authorization");
        LOG.info("登录校验开始，token：{}", token);
        if (token == null || token.isEmpty()) {
            LOG.info( "token为空，请求被拦截" );
            GraceException.display(ResponseStatusEnum.TOKEN_ISNULL);
            return false;
        }
        //页面刷新时不做校验
        if(token.equals("refresh")){
            return true;
        }
        String id = jwtUtils.getClaimByToken(token).getSubject();
        LOG.info("subject: {}",id);
        Object object = redisTemplate.opsForValue().get(id);
        if (object == null) {
            LOG.warn( "token无效，请求被拦截" );
            GraceException.display(ResponseStatusEnum.TOKEN_EXPIRED);
            return false;
        } else {
            LoginUserVO loginUserVO = (LoginUserVO)object;
            LOG.info("已登录：{}", loginUserVO);
            return true;
        }
    }
}
