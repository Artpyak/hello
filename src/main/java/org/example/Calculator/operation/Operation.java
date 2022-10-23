package org.example.Calculator.operation;

public interface Operation {
    int operate(int num1, int num2);

    boolean isCurrentOperation(char operation);
}