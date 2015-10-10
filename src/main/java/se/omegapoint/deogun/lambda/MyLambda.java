package se.omegapoint.deogun.lambda;

import java.util.function.Predicate;

public class MyLambda {
    String field = "Hej";

    void someMethod() {
        Predicate<String> predicate = p -> field.length() < 6;
//        MyPredicate predicate = new MyPredicate() {
//            @Override
//            public boolean test(final String value) {
//                return value.length() < 6;
//            }
//        };
        eval(predicate);
        field = null;
        eval(predicate);
    }

    void eval(Predicate<String> predicate) {
        System.out.println(predicate.test("Då"));
    }

    void eval(MyPredicate predicate) {
        System.out.println(predicate.test("Då"));
    }

    public static void main(String[] arg) {
        new MyLambda().someMethod();
    }

    private interface MyPredicate {
        boolean test(String value);
    }
}
