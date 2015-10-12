package se.omegapoint.deogun.bytecode;

import java.util.List;
import java.util.function.BinaryOperator;

import static java.util.Arrays.asList;

public class Evaluation {
    int factor = 100;

    public void foo() {
        final List<Integer> values = asList(1, 2, 3, 4, 5);

        System.out.println(values.stream().reduce(0, stateful()));
        System.out.println(values.stream().reduce(0, stateless()));
    }

    private BinaryOperator<Integer> stateful() {
        return (a, b) -> a + b + factor;
    }

    private BinaryOperator<Integer> stateless() {
        return (a, b) -> a + b;
    }
}
