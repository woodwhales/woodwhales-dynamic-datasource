package cn.woowhales.dynamic.aop;

import cn.woowhales.dynamic.datasource.DataSourceHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author woodwhales on 2021-09-09 22:45
 * @Description
 */
@Order(1)
@Aspect
@Component
@Slf4j
public class ControllerAspect {

    @Before("@annotation(mapping)")
    public void pointcut(GetMapping mapping) {
        handler();
    }

    @Before("@annotation(mapping) ")
    public void pointcut(PostMapping mapping) {
        handler();
    }

    @Before("@annotation(mapping)")
    public void pointcut(DeleteMapping mapping) {
        handler();
    }

    @Before("@annotation(mapping) ")
    public void pointcut(PutMapping mapping) {
        handler();
    }

    public void handler() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String requestURI = request.getRequestURI();
        String method = request.getMethod();
        log.info("request for [{}] [{}]", method, requestURI);

        String dbType = request.getHeader("dbType");
        // 这里可以通过请求头从基础数据库获取请求者可以访问的数据源
        DataSourceHolder.loadDataSource(dbType);
    }

    @AfterReturning("@annotation(mapping)")
    public void after(GetMapping mapping) {
        DataSourceHolder.cleanDataSource();
    }

    @AfterReturning("@annotation(mapping)")
    public void after(PostMapping mapping) {
        DataSourceHolder.cleanDataSource();
    }

    @AfterReturning("@annotation(mapping)")
    public void after(DeleteMapping mapping) {
        DataSourceHolder.cleanDataSource();
    }

    @AfterReturning("@annotation(mapping)")
    public void after(PutMapping mapping) {
        DataSourceHolder.cleanDataSource();
    }
}
