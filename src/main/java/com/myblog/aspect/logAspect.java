package com.myblog.aspect;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 黄龙
 * @Date: 2021/08/09/12:37
 * @Description:
 */

@Slf4j
@Aspect
@Component
public class logAspect {
    @Pointcut("execution(* com.myblog.controller.*.*(..))")
    public void log(){
    }
    
    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();
        String classMethod = joinPoint.getSignature().getDeclaringType()+"."+joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        RequsetLog requsetLog = new RequsetLog(url,ip,classMethod,args);


        log.info("request: {}",requsetLog);
    }

    @After("log()")
    public void doAfter(){

    }

    @AfterReturning(returning = "result",pointcut = "log()")
    public void doAfterReturn(Object result){
        log.info("result: {}",result);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    private class RequsetLog{
        private String url;
        private String ip;
        private String classMethod;
        private Object[] args;
    }
}
