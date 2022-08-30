package org.example.animal;

import org.example.fruit.Banana;
import org.example.fruit.Fruit;

public class Monkey implements Animal{
    @Override
    public void voice(Fruit fruit) {
        if (fruit.color() == "yellow") {
            System.out.println("Uuuhhhhhhhhhhh");
        } else {
            System.out.println("Bnnneeeee");
        }
    }
}
