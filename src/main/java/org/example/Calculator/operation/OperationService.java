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
        Optional<Operation> first = operations.stream()
                .filter(op -> op.isCurrentOperation(operation))
                .findFirst();

        if (first.isEmpty()) {
            throw new OperationException("Your operation is not supported! See you later!!!");
        }

        return first.get();
    }
}
