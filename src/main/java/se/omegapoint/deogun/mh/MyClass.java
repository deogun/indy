package se.omegapoint.deogun.mh;

import java.lang.invoke.MethodHandles;

class MyClass extends MySuperClass {
    String message;

    static int lengthOf(final String value) {
        return value.length();
    }

    void print(final String value) {
        System.out.println(value);
    }

    void print() {
        System.out.println(message);
    }

    static MethodHandles.Lookup lookup() {
        return MethodHandles.lookup();
    }

    private void printInternal() {
        System.out.println("Printing private message");
    }
}
