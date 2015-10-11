package se.omegapoint.deogun.indy;

import java.lang.invoke.MethodHandle;

import static java.lang.invoke.MethodHandles.lookup;
import static java.lang.invoke.MethodType.methodType;

public class Driver {
    public static void main(String[] args) throws Throwable {
        final MethodHandle indy = lookup().findVirtual(ClassWithInvokedynamic.class, "indy", methodType(void.class));

        indy.invokeExact(new ClassWithInvokedynamic());
        indy.invokeExact(new ClassWithInvokedynamic());
        indy.invokeExact(new ClassWithInvokedynamic());
    }
}
