package com.qiuguan.componentscan.ann;

import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author created by qiuguan on 2022/1/13 17:04
 *
 * 在自定义注解上标注 {@link Component} 注解,
 * @see org.springframework.stereotype.Service
 * @see org.springframework.stereotype.Repository
 * @see org.springframework.stereotype.Controller
 *
 * @see com.qiuguan.componentscan.service.OrderService
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface MyComponent {

    /**
     * bean name， 如果不指定，默认就是类名首字母小写，本质上和 @Component 有关，请看代码判断逻辑
     *
     * 扫描完成后，会处理bean定义信息，这里会解析beanName
     * @see ClassPathBeanDefinitionScanner#doScan (280行）
     * @see AnnotationBeanNameGenerator#determineBeanNameFromAnnotation#isStereotypeWithNameValue
     *
     */
    String value() default "";
}
