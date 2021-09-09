package cn.woowhales.dynamic.annotation;

import cn.woowhales.dynamic.enums.DataSourceEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 数据源选择注解
 * @author woodwhales
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DataSourceSelector {
    DataSourceEnum value();
}