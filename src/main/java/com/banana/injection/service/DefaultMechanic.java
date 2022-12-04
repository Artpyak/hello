package com.banana.injection.service;

import com.banana.injection.annotation.SecondConstructor;
import com.banana.injection.annotation.Service;

@Service
public class DefaultMechanic implements Mechanic {
    public DefaultMechanic() {
        System.out.println("Where is my money, Bitch???!!!");
    }

    @SecondConstructor
    public void prepare() {
        System.out.println("I need to drink before work");
    }

    @Override
    public void work() {
        System.out.println("I'm tyred. I'm going to smoke!");
    }
}
