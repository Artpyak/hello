package com.banana.injection.core;

import com.banana.injection.factory.ObjectFactory;
import com.banana.injection.service.CarService;

public class Application {
    public static void run() {
        System.out.println("Hello Banana");
        ObjectFactory objectFactory = new ObjectFactory();
        objectFactory.init();

        CarService bean = objectFactory.getBean(CarService.class);
        bean.work();
    }
}
