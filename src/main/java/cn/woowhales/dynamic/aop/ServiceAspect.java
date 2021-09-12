package cn.woowhales.dynamic.aop;

import cn.woowhales.dynamic.annotation.DataSourceSelector;
import cn.woowhales.dynamic.datasource.DataSourceHolder;
import cn.woowhales.dynamic.enums.DataSourceEnum;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author woodwhales on 2021-09-09 23:58
 * @Description
 */
@Order(1)
@Aspect
@Component
@Slf4j
public class ServiceAspect {

    @Pointcut("execution(* cn.woowhales.dynamic.biz..*(..))")
    public void pointcut() {
    }

    @Around("@annotation(dataSourceSelector)")
    public Object around(ProceedingJoinPoint joinPoint, DataSourceSelector dataSourceSelector) throws Throwable {
        return execute(joinPoint, dataSourceSelector);
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object target = joinPoint.getTarget();
        DataSourceSelector dataSourceSelector = target.getClass().getAnnotation(DataSourceSelector.class);
        return execute(joinPoint, dataSourceSelector);
    }

    private Object execute(ProceedingJoinPoint joinPoint, DataSourceSelector dataSourceSelector) throws Throwable {
        if(Objects.isNull(dataSourceSelector)) {
            return joinPoint.proceed();
        }

        DataSourceEnum dataSourceEnum = dataSourceSelector.value();
        if(Objects.isNull(dataSourceEnum)) {
            throw new RuntimeException("未指定数据源");
        }
        DataSourceHolder.loadDataSource(dataSourceEnum.name());
        try {
            return joinPoint.proceed();
        } finally {
            DataSourceHolder.cleanDataSource();
        }
    }

}
