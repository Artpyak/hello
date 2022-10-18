package Iakov_Fain.My_First_Project.Pet_and_Car.Fish;

import Iakov_Fain.My_First_Project.Pet_and_Car.Pet;

public class Fish extends Pet {
    int currentDepth = 0;

    public int dive(int howDeep) {
        currentDepth = currentDepth + howDeep;
        if (currentDepth > 100){
            System.out.println("Я маленькая рыбка "+
                    " и не могу плавать глубже 100 метров");
            currentDepth=currentDepth - howDeep;
        }else {

            System.out.println("Ныряю на глубину "
                    + howDeep + " футов");
            System.out.println("Я на глубине "
                    + currentDepth + " футов ниже уровня моря");
        }
            return currentDepth;


    }
    public String say(String something){
        return "Ты чё не знаешь, что рыбы не разговаривают?";
    }
}
