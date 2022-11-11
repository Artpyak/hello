package org.example.Calculator.operation;

public class DivideOperation implements Operation {
    @Override
    public int operate(int num1, int num2) {
        return num1 / num2;
    }

    @Override
    public boolean isCurrentOperation(char operation) {
        return operation == '/';
    }
}
