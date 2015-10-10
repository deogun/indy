package se.omegapoint.deogun.intro;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class SimpleClass {
    public List<Integer> findAllEvenNumbers(final List<Integer> data) {
        return data.stream()
                .filter(d -> d % 2 == 0)
                .collect(toList());
    }
}
