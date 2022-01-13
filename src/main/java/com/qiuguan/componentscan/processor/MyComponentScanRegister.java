package com.qiuguan.componentscan.processor;

import com.qiuguan.componentscan.ann.MyComponent2;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.boot.autoconfigure.AutoConfigurationPackages;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author created by qiuguan on 2022/1/13 17:29
 *
 * @see org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration
 */
public class MyComponentScanRegister implements ImportBeanDefinitionRegistrar, BeanFactoryAware {

    private BeanFactory beanFactory;

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

        /**
         * ClassPathBeanDefinitionScanner 这个类是spring包扫描中一个非常非常重要的类
         * 可以继承它，或者直接 new, 像扫描 @Component 注解就是通过它的构造器指定的
         */
        //ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(registry, false);
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(registry);

        /**
         * 指定扫描的规则，非常重要，自定义中最主要的就是在这里指定自定义扫描规则
         */
        scanner.addIncludeFilter(new AnnotationTypeFilter(MyComponent2.class));

        /**
         * 获取主配置类所在的包
         * @see org.springframework.boot.autoconfigure.AutoConfigurationPackage
         * @SpringBootApplication --> @EnableAutoConfiguration --> @AutoConfigurationPackage
         *
         * 可以参考mybatis 的 MapperScannerRegistrar
         *
         * ========
         * 这个方法是 ClassUtils.getPackageName(declaringClass); 获取主配置类的包路径
         * declaringClass 是主配置类, 但我这里获取不到，所以使用下面的方法
         *
         * 注意：这个 AutoConfigurationPackages.get(this.beanFactory) 获取路径的方法
         * 随着我这当前类的导入的位置不同，可能会报错，现在是放到了主配置类上没有问题，我测试发现
         * 如果放到某一个配置类上导入，就报错
         */
        List<String> packages = AutoConfigurationPackages.get(this.beanFactory);

        scanner.scan(StringUtils.toStringArray(packages));
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
}
