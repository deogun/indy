package se.omegapoint.deogun.intro;

import java.util.function.Consumer;

public class Evaluation {

    public static void main(String[] args) {
        final Consumer<String> consumer = p -> System.out.println("Hello World");
        System.out.println("sfsdf");
        consumer.accept("sdfsdfsf");
    }
}
