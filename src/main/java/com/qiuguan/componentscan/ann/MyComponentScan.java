package com.qiuguan.componentscan.ann;

import com.qiuguan.componentscan.processor.MyComponentScanConfigure;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * @author created by qiuguan on 2022/1/13 17:23
 *
 * 这个注解和 {@link MyComponent} 和 {@link MyComponent2} 注解没有关系
 * 不是用来扫描这两个注解的
 *
 * 和 {@link @MapperScan } 注解类似，@MapperScan 注解也不是扫描 @Mapper 的
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(MyComponentScanConfigure.class)
public @interface MyComponentScan {

    String[] value() default {};

    @AliasFor("value")
    String[] basePackages() default {};
}
