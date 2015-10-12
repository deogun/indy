package se.omegapoint.deogun.mh;

import org.junit.Test;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.WrongMethodTypeException;

import static java.lang.invoke.MethodHandles.lookup;
import static java.lang.invoke.MethodType.methodType;
import static org.junit.Assert.assertEquals;

public class StrictnessTest {

    @Test(expected = WrongMethodTypeException.class)
    public void should_not_match_polymorphic_signature_1() throws Throwable {
        final MethodHandle lengthOf = lookup().findStatic(MyClass.class, "lengthOf", methodType(int.class, new Class[]{String.class}));

        assertEquals(13, lengthOf.invokeExact("Hello OpKoKo!"));
    }

    @Test(expected = WrongMethodTypeException.class)
    public void should_not_match_polymorphic_signature_2() throws Throwable {
        final MethodHandle lengthOf = lookup().findStatic(MyClass.class, "lengthOf", methodType(int.class, new Class[]{String.class}));

        final Integer result = (Integer) lengthOf.invokeExact("Hello OpKoKo!");
        assertEquals(Integer.valueOf(13), result);
    }

    @Test(expected = WrongMethodTypeException.class)
    public void should_not_match_polymorphic_signature_3() throws Throwable {
        final MethodHandle lengthOf = lookup().findStatic(MyClass.class, "lengthOf", methodType(int.class, new Class[]{String.class}));

        final Object result = lengthOf.invokeExact("Hello OpKoKo!");
        assertEquals(13, result);
    }

    @Test
    public void should_match_polymorphic_signature() throws Throwable {
        final MethodHandle lengthOf = lookup().findStatic(MyClass.class, "lengthOf", methodType(int.class, new Class[]{String.class}));

        final int result = (int) lengthOf.invokeExact("Hello OpKoKo!");
        assertEquals(13, result);
    }

    @Test
    public void should_use_auto_boxing_1() throws Throwable {
        final MethodHandle lengthOf = lookup().findStatic(MyClass.class, "lengthOf", methodType(int.class, new Class[]{String.class}));

        assertEquals(13, lengthOf.invoke("Hello OpKoKo!"));
    }

    @Test
    public void should_use_auto_boxing_2() throws Throwable {
        final MethodHandle lengthOf = lookup().findStatic(MyClass.class, "lengthOf", methodType(int.class, new Class[]{String.class}));

        final Integer result = (Integer) lengthOf.invoke("Hello OpKoKo!");

        assertEquals(Integer.valueOf(13), result);
    }

    @Test
    public void should_use_auto_boxing_3() throws Throwable {
        final MethodHandle lengthOf = lookup().findStatic(MyClass.class, "lengthOf", methodType(int.class, new Class[]{String.class}));

        final Object result = lengthOf.invoke("Hello OpKoKo!");

        assertEquals(13, result);
    }
}
