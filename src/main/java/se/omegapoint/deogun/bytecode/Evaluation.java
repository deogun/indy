package se.omegapoint.deogun.bytecode;

import java.util.List;

import static java.util.Arrays.asList;

public class Evaluation {
    public static void main(String[] args) {
        final List<Integer> values = asList(1, 2, 3, 4, 5);

        System.out.println(values.stream().reduce(0, (a, b) -> a + b));
    }
}
