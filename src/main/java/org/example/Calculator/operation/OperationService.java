package org.example.Calculator.operation;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class OperationService {
    private final static List<Operation> operations = Arrays.asList(
            new MinusOperation(),
            new SummaryOperation(),
            new MultiplyOperation(),
            new DivideOperation()
    );

    public Operation chooseOperation(char operation) throws OperationException {
//        Operation current = null;
//        for (Operation op : operations) {
//            boolean currentOperation = op.isCurrentOperation(operation);
//            if (currentOperation) {
//                current = op;
//                break;
//            }
//        }
//        if (current == null) {
//            throw new OperationException("Your operation is not supported! See you later!!!");
//        }
//        return current;


        return operations.stream()
                .filter(op -> op.isCurrentOperation(operation))
                .findFirst()
                .orElseThrow(()-> new OperationException("Your operation is not supported! See you later!!!"));
    }
}
