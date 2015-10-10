package se.omegapoint.deogun.methodhandles;

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
}
