package com.qiuguan.componentscan.scan;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.lang.annotation.Annotation;

/**
 * @author created by qiuguan on 2022/1/13 18:45
 *
 * 可以直接使用 ClassPathBeanDefinitionScanner， 我这里继承它
 */
public class MyComponentClassPathScanner extends ClassPathBeanDefinitionScanner {

    /**
     * 如果想让 @MyComponentScan 注解去扫描制定的注解类，则通过这个参数制定
     */
    private Class<? extends Annotation> annotationClass;

    public MyComponentClassPathScanner(BeanDefinitionRegistry registry) {
        super(registry, false);
    }

    public void registerFilters() {
        boolean acceptAll = true;
        if (annotationClass != null) {
            addIncludeFilter(new AnnotationTypeFilter(annotationClass));
            acceptAll = false;
        }


        if(acceptAll) {
            //这表示所有的都要扫描
            addIncludeFilter((metadataReader, metadataReaderFactory) -> true);
        }

        // exclude package-info.java
        addExcludeFilter((metadataReader, metadataReaderFactory) -> {
            String className = metadataReader.getClassMetadata().getClassName();
            return className.endsWith("package-info");
        });
    }

    /**
     * 这个候选条件，将最终确定是否创建bean 定义信息
     * 我这里是，只有final 修饰的类，才可以创建bean 定义信息，其他的就不需要了
     */
    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return beanDefinition.getMetadata().isFinal();
    }

    public Class<? extends Annotation> getAnnotationClass() {
        return annotationClass;
    }

    public void setAnnotationClass(Class<? extends Annotation> annotationClass) {
        this.annotationClass = annotationClass;
    }
}
