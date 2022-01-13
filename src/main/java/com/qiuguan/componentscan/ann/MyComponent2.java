package com.qiuguan.componentscan.ann;

import java.lang.annotation.*;

/**
 * @author created by qiuguan on 2022/1/13 17:21
 *
 * 这个注解需要经过spring 容器扫描才可以生效
 * 参考 spring-mybatis 或  mybatis-starter
 *
 * @see com.qiuguan.componentscan.processor.MyComponentScanRegister
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyComponent2 {

}
