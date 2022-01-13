package com.qiuguan.componentscan;

import com.qiuguan.componentscan.ann.MyComponentScan;
import com.qiuguan.componentscan.bean.OrderBean;
import com.qiuguan.componentscan.bean.PaymentBean;
import com.qiuguan.componentscan.processor.MyComponentScanRegister;
import com.qiuguan.componentscan.service.OrderService;
import com.qiuguan.componentscan.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;

/**
 * @author created by qiuguan on 2022/1/13 17:08
 */
@MyComponentScan(basePackages = "com.qiuguan.componentscan.bean")
@Import(MyComponentScanRegister.class)
@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ioc = SpringApplication.run(MainApplication.class, args);

        System.out.println("容器启动成功...........................");

        testImport(ioc);

        testComponentScan(ioc);

    }

    /**
     * @see @MyComponentScan(basePackages = "com.qiuguan.componentscan.bean")
     *
     */
    private static void testComponentScan(ConfigurableApplicationContext ioc) {
        PaymentBean paymentBean = ioc.getBean(PaymentBean.class);
        System.out.println(paymentBean);


        OrderBean orderBean = ioc.getBean(OrderBean.class);
        System.out.println(orderBean);
    }

    /**
     * @see @Import(MyComponentScanRegister.class)
     */
    private static void testImport(ConfigurableApplicationContext ioc) {
        OrderService orderService = ioc.getBean(OrderService.class);
        System.out.println(orderService);

        UserService userService = ioc.getBean(UserService.class);
        System.out.println(userService);
    }
}
