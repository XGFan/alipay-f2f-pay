package com.xulog.alipay.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestConfig {
    /**
     * 基础信息
     */
    String value();

    /**
     * 是否需要额外证书
     */
    boolean useCert() default false;

    /**
     * 是否生成表单由前台提交
     */
    boolean submitByForm() default false;

    /**
     * 支付宝的返回值无需设置
     * 微信,银联,CP的需要配置
     */
    Class returnType() default Object.class;


    StringType rsType() default StringType.JSON;


    enum StringType {
        JSON,
        XML,
        QUERY,
        CUSTOM
    }
}
