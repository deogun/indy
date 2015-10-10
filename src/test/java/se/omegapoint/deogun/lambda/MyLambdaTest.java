package se.omegapoint.deogun.lambda;

import org.junit.Test;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class MyLambdaTest {
    @Test
    public void should_print_hashcode_of_lambda() {
        Predicate<String> predicateA = p -> p.length() < 10;
        Predicate<String> predicateB = p -> p.length() < 10;

        Consumer<String> consumer = c -> System.out.println(c);

        System.out.println(predicateA.hashCode());

        System.out.println(predicateB.hashCode());

        System.out.println(predicateA.equals(predicateB));

        consumer.accept("hello");
    }

    @Test
    public void should() {
        MyTestPredicate<String> predicateA = value -> value.length() < 10;
        MyTestPredicate<String> predicateB = value -> value.length() < 10;

        System.out.println(predicateA.hashCode());

        System.out.println(predicateB.hashCode());

        System.out.println(predicateA.equals(predicateB));
    }

    private interface MyTestPredicate<T> {
        boolean test(T value);
    }

    String message = "Hello";

    @Test
    public void should_evaluate() {

        Consumer<String> consumer = p -> System.out.println(p + message);

        new A().apply(consumer);
        message = "bye";
        new B().apply(consumer);
    }

    private static class A {
        private String message = "A";

        public void apply(Consumer<String> consumer) {
            consumer.accept(message);
        }
    }

    private static class B {
        private String message = "B";

        public void apply(Consumer<String> consumer) {
            consumer.accept(message);
        }
    }

}