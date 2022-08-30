package org.example;

import org.example.animal.Animal;
import org.example.animal.Monkey;
import org.example.fruit.Banana;
import org.example.fruit.Cucumber;

public class AnimalTest {
    public static void main(String[] args) {
        Integer age;
        try {

            age = Integer.valueOf(args[0]);
        } catch (NumberFormatException ex) {
            System.out.println("give me a number!!!");
            age = new Integer(0);
        }
        Animal monkey = new Monkey();
        Banana banana = new Banana();
        banana.setAge(age);
        monkey.voice(banana);
    }
}
