package com.chigirh.eh.rem.infra.config;

import com.chigirh.eh.rem.domain.error.DataAccessError;
import org.apache.ibatis.exceptions.IbatisException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DataAccessInterceptor {

    @AfterThrowing(pointcut = "@annotation(com.chigirh.eh.rem.infra.config.DataAccess)", throwing = "ex")
    public void errorInterceptor(JoinPoint joinPoint, DataAccessException ex) {
        var signature = (MethodSignature) joinPoint.getSignature();
        var method = signature.getMethod();
        DataAccess annotation = method.getAnnotation(DataAccess.class);
        throw new DataAccessError(annotation.process(), ex);
    }

    @AfterThrowing(pointcut = "@annotation(com.chigirh.eh.rem.infra.config.DataAccess)", throwing = "ex")
    public void errorInterceptor(JoinPoint joinPoint, IbatisException ex) {
        var signature = (MethodSignature) joinPoint.getSignature();
        var method = signature.getMethod();
        DataAccess annotation = method.getAnnotation(DataAccess.class);
        throw new DataAccessError(annotation.process(), ex);
    }

}
