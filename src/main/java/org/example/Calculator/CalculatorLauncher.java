package org.example.Calculator;

import org.example.Calculator.operation.OperationService;

public class CalculatorLauncher {
    public static void main(String[] args) {
        OperationService operationService = new OperationService();
        Calculator2 myCalculator = new Calculator2(operationService);
        myCalculator.launch();
    }
}
