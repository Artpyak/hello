package org.example.Calculator;

public class MasterCalculator {
    public static void main(String[] args) {
        Calculator2 myCalculator = new Calculator2();
        myCalculator.scan();
        myCalculator.error();
        myCalculator.calculation();
    }

}
