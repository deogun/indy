package se.omegapoint.deogun.indy;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class Driver {
    public static void main(String[] args) throws Throwable {
        final MethodType methodType = MethodType.methodType(void.class);

        final MethodHandles.Lookup lookup = MethodHandles.lookup();

        final MethodHandle methodHandle = lookup.findVirtual(ClassWithInvokedynamic.class, "indy", methodType);

        final ClassWithInvokedynamic invoker = new ClassWithInvokedynamic();
        methodHandle.invokeExact(invoker);
        methodHandle.invokeExact(invoker);
        methodHandle.invokeExact(invoker);
    }
}
