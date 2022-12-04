package com.banana.injection.service;


import com.banana.injection.annotation.InjectByType;
import com.banana.injection.annotation.InjectValue;
import com.banana.injection.annotation.Service;

@Service
public class CarService {

    @InjectValue(propertyName = "label")
    private String name;

    @InjectByType
    private Mechanic mechanic;

    public CarService() {
        System.out.println("We are opened. Welcome!!!");
    }

    public void work() {
        mechanic.work();
        System.out.println("Name " + name);
    }
}
