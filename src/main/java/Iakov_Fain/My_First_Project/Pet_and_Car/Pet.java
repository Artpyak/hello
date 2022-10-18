package Iakov_Fain.My_First_Project.Pet_and_Car;

public class Pet {
    int age;
    float weight;
    float height;
    String color;

    public void sleep() {
        System.out.println("Спокойной ночи! До завтра");
    }

    public void eat() {
        System.out.println("Я очень голоден, давайте перекусим чипсами!");
    }

    public String say(String aWord) {
        String petResponse = "Ну ладно!! " + aWord;
        return petResponse;
    }

}
