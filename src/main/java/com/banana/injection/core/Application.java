package com.banana.injection.core;

import com.banana.injection.factory.BeanFactory;
import com.banana.injection.service.CarService;

public class Application {
    public static void run() {
        System.out.println("Hello Banana");
        BeanFactory beanFactory = new BeanFactory();
        beanFactory.init();

        CarService bean = beanFactory.getBean(CarService.class);
        bean.work();
    }
}
