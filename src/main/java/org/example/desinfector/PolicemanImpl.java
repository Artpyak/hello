package org.example.desinfector;

import javax.annotation.PostConstruct;

public class PolicemanImpl implements Policeman {

    @InjectByTy
    private Recommendator recommendator;

    @PostConstruct
    public void init() {
        System.out.println(recommendator.getClass());
    }
    @Override
    public void makePeopleLeaveRoom() {
        System.out.println("Все расходимся!");

    }
}
