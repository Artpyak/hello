package org.example.fruit;

public class Banana implements Fruit {
    private int age;

    public Banana() {}
//    public Banana(int age) {
//        this.age = age;
//    }

    @Override
    public String color() {
        if (age <= 1) {
            return "green";
        }
        return "yellow";
    }

    public void setAge(int age) {
        this.age = age;
    }
}
