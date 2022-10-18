package org.example.Calculator;

import java.util.Scanner;

public class Calculator2 {

    Scanner scanner = new Scanner(System.in);

    int num1;
    int num2;
    char operation;

    public void scan() {


        System.out.print("Введите первое число: ");
        num1 = scanner.nextInt();

        System.out.print("Выберите операцию: (+, -, *, /) ");
        operation = scanner.next().charAt(0);

        System.out.print("Введите второе число: ");
        num2 = scanner.nextInt();

    }


    public void calculation() {

        if (operation == '+') {
            System.out.println(num1 + " + " + num2 + " = " + (num1 + num2));
        } else if (operation == '-') {
            System.out.println(num1 + " - " + num2 + " = " + (num1 - num2));
        } else if (operation == '*') {
            System.out.println(num1 + " x " + num2 + " = " + (num1 * num2));
        } else if (operation == '/') {
            System.out.println(num1 + " / " + num2 + " = " + (num1 / num2));
        } else {
            System.err.println("Использйте только данные операции: +, -, *, /");
        }
    }

    public void error() {
        if (operation == '/' && num2 == 0) {
            System.err.println("Дурак? Тебе кто раздрешил делить на ноль?!");
        }

    }

}
