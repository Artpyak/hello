package Iakov_Fain.My_First_Project.Pet_and_Car.Car;

public class Car {
    static int distance;

    public static void start() {
        System.out.println("Начало");
    }

    public static void stop() {
        System.out.println("Остановка");
    }

    public static int drive() {
        int howlong = 3;
        distance = howlong * 60;
        System.out.println("Расстояние " + distance + " Км");
        return distance;
    }

}
