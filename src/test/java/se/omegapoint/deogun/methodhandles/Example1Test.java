package se.omegapoint.deogun.methodhandles;

import org.junit.Test;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

import static org.junit.Assert.assertEquals;

public class Example1Test {
    @Test
    public void should_count_length_of_string() throws Throwable {
        final MethodType methodType = MethodType.methodType(int.class, new Class[]{String.class});

        final MethodHandles.Lookup lookup = MethodHandles.lookup();

        final MethodHandle methodHandle = lookup.findStatic(MyClass.class, "lengthOf", methodType);

        assertEquals(42, methodHandle.invoke("Waaaz up Duke! What's the meaning of life?"));
    }
}
