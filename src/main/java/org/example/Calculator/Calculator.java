package org.example.Calculator;

import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите первое число: ");
        int num1 = scanner.nextInt();

        System.out.print("Выберите операцию: (+, -, *, /) ");
        char operation = scanner.next().charAt(0);

        System.out.print("Введите второе число: ");
        int num2 = scanner.nextInt();

        if (operation == '/' && num2 == 0) {
            System.err.println("Дурак? Тебе кто раздрешил делить на ноль?!");
        }
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


}
