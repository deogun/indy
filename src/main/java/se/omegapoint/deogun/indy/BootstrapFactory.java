package se.omegapoint.deogun.indy;

import java.lang.invoke.*;

public class BootstrapFactory {
    public static CallSite bootstrap(final MethodHandles.Lookup caller, final String name, final MethodType type) throws Throwable {
        System.out.println("Bootstrap factory invoked by: " + caller.lookupClass().getSimpleName());

        final MethodHandle method = MethodHandles.lookup().findStatic(MessagePrinter.class, name, type);

        return new ConstantCallSite(method.asType(type));
    }
}
