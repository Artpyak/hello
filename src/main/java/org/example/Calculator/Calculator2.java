package org.example.Calculator;

import org.example.Calculator.operation.Operation;
import org.example.Calculator.operation.OperationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class Calculator2 {
    private final static Logger logger = LoggerFactory.getLogger(Calculator2.class);

    private final OperationService operationService;

    public Calculator2(OperationService operationService) {
        this.operationService = operationService;
    }

    private final Scanner scanner = new Scanner(System.in);

    public void launch() {
        boolean finish = false;

        while (!finish) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            CalculationData data = scan();
            try {
                calculation(data);
                finish = true;
            } catch (Throwable e) {
                error(data, e);
            }
        }
    }

    private CalculationData scan() {
        System.out.print("Введите первое число: ");
        int num1 = scanner.nextInt();

        System.out.print("Выберите операцию: (+, -, *, /) ");
        char operation = scanner.next().charAt(0);

        System.out.print("Введите второе число: ");
        int num2 = scanner.nextInt();

        return new CalculationData(num1, num2, operation);
    }


    private void calculation(CalculationData data) throws Throwable {
        Operation operation = operationService.chooseOperation(data.getOperation());
        int result = operation.operate(data.getNum1(), data.getNum2());
        System.out.println(
                "Result of operation: " +
                        data.getNum1() +
                        data.getOperation() +
                        data.getNum2() + "=" + result
        );
    }

    private void error(CalculationData data, Throwable e) {
        if (data.getOperation() == '/' && data.getNum2() == 0) {
            logger.error("Дурак? Тебе кто раздрешил делить на ноль?!", e);
        } else {
            logger.error("Some exception: ", e);
        }
    }

    private static class CalculationData {
        private final int num1;
        private final int num2;
        private final char operation;

        public CalculationData(int num1, int num2, char operation) {
            this.num1 = num1;
            this.num2 = num2;
            this.operation = operation;
        }

        public int getNum1() {
            return num1;
        }

        public int getNum2() {
            return num2;
        }

        public char getOperation() {
            return operation;
        }
    }
}
