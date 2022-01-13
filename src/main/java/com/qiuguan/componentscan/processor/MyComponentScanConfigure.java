package com.qiuguan.componentscan.processor;

import com.qiuguan.componentscan.ann.MyComponentScan;
import com.qiuguan.componentscan.scan.MyComponentClassPathScanner;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author created by qiuguan on 2022/1/13 18:30
 */
public class MyComponentScanConfigure implements ImportBeanDefinitionRegistrar  {


    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        AnnotationAttributes scanAttrs = AnnotationAttributes
                .fromMap(importingClassMetadata.getAnnotationAttributes(MyComponentScan.class.getName()));

        if (scanAttrs != null) {
            registerBeanDefinitions(scanAttrs, registry);
        }
    }


    /**
     * @see @MapperScan
     * @see ClassPathMapperScanner
     * @param scanAttrs
     * @param registry
     */
    private void registerBeanDefinitions(AnnotationAttributes scanAttrs, BeanDefinitionRegistry registry) {
        MyComponentClassPathScanner scanner = new MyComponentClassPathScanner(registry);

        List<String> basePackages = new ArrayList<>();
        basePackages.addAll(
                Arrays.stream(scanAttrs.getStringArray("value"))
                        .filter(StringUtils::hasText)
                        .collect(Collectors.toList()));

        basePackages.addAll(
                Arrays.stream(scanAttrs.getStringArray("basePackages"))
                        .filter(StringUtils::hasText)
                        .collect(Collectors.toList()));


        //不去重也可以，后面spring不会重复处理的
        Set<String> basePackageSet = new HashSet<>(basePackages);

        scanner.registerFilters();

        //去扫描指定的注解
        //scanner.setAnnotationClass();

        scanner.scan(StringUtils.toStringArray(basePackageSet));
    }
}
