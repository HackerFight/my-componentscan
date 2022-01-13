## 1. @MyComponent 注解
 参考
 * @Service
 * @Repository
 * @Service <br>
 注解，本质上都是 @Component 注解，所以我的这个@MyComponent 注解本质上也是 @Component 注解
 
 
 ## 2. @MyComponent2 注解
 这个注解是我真正意义上自定义的注解，那么如何生效呢？<br>
 请看 MyComponentScanRegister 类
 
 
 ## 3. @MyComponentScan 注解
 这个注解也是真正意义上的自定义注解，它又如何生效呢？<br>
 请看 MyComponentScanConfigure
 
 
 ## 4.说明
 自定义注解如果生效，其实离不开spring 扫描功能的 ClassPathBeanDefinitionScanner 类
 这个类继承了 ClassPathScanningCandidateComponentProvider 类 <br>
 * 定义了扫描规则，就是Filter <br>
 ~~~
   @ComponentScan(basePackages = "com.qiuguan.componentscan.bean", includeFilters = {
            @ComponentScan.Filter(type= FilterType.ANNOTATION,classes={Controller.class})
    }, useDefaultFilters = false)
 ~~~
  或者
  ~~~   
     <context:component-scan base-package="com.qiuguan.componentscan">
         <context:include-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
         <context:exclude-filter type="annotation" expression="com.qiuguan.componentscan.ann.MyComponent"/>
     </context:component-scan>
  ~~~ 

 * 还指定最终的刷选规则 <br>
 就比如前面过滤出来，要创建bean 定义信息，但是还需要经过这一步帅选后才最终决定是否创建，比如mybatis 会去校验，只有接口才符合需求，才会去创建bean 定义对象
