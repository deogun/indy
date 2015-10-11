package se.omegapoint.deogun.methodhandles;

import org.junit.Test;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.WrongMethodTypeException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class MyClassTest {
    @Test
    public void should_invoke_lengthOf() throws Throwable {
        final MethodType methodType = MethodType.methodType(int.class, new Class<?>[]{String.class});

        final MethodHandles.Lookup lookup = MethodHandles.lookup();

        final MethodHandle methodHandle = lookup.findStatic(MyClass.class, "lengthOf", methodType);

        final int result = (int) methodHandle.invokeExact("Wazzup Duke!! What's the meaning of life??");

        assertEquals(42, result);
    }

    @Test(expected = WrongMethodTypeException.class)
    public void should_fail_due_to_compiler_hint() throws Throwable {
        final MethodType methodType = MethodType.methodType(int.class, new Class<?>[]{String.class});

        final MethodHandles.Lookup lookup = MethodHandles.lookup();

        final MethodHandle methodHandle = lookup.findStatic(MyClass.class, "lengthOf", methodType);

        //methodHandle.invokeExact("count"); //expected (String)int but found (String)void
        final Integer result = (Integer) methodHandle.invokeExact("count"); //expected (String)int but found (String)Integer
    }

    @Test
    public void should_allow_autoboxing() throws Throwable {
        final MethodType methodType = MethodType.methodType(int.class, new Class<?>[]{String.class});

        final MethodHandles.Lookup lookup = MethodHandles.lookup();

        final MethodHandle methodHandle = lookup.findStatic(MyClass.class, "lengthOf", methodType);

        final Integer result = (Integer) methodHandle.invoke("Wazzup Duke!! What's the meaning of life??");

        assertThat(result, is(42));
    }

    @Test
    public void should_invoke_print_value() throws Throwable {
        final MethodType methodType = MethodType.methodType(void.class, new Class<?>[]{String.class});

        final MethodHandles.Lookup lookup = MethodHandles.lookup();

        final MethodHandle methodHandle = lookup.findVirtual(MyClass.class, "print", methodType);

        methodHandle.invokeExact(new MyClass(), "Wazzup Duke!! What's the meaning of life??");
    }

    @Test
    public void should_set_field_and_print() throws Throwable {
        final MyClass myClass = new MyClass();

        final MethodHandles.Lookup lookup = MethodHandles.lookup();

        final MethodHandle field = lookup.findSetter(MyClass.class, "message", String.class);

        field.invokeExact(myClass, "Hello OpKoKo 15.2 Tranås!");

        final MethodType noArgMethodType = MethodType.methodType(void.class);

        final MethodHandle method = lookup.findVirtual(MyClass.class, "print", noArgMethodType);

        method.invokeExact(myClass);
    }

    @Test
    public void shows_reflection_vs_method_handles() throws Throwable {
        final MyClass myClass = new MyClass();

        MyClass.class.getMethod("fooBar",String.class).invoke(myClass, "Invoked via reflection");

        final MethodHandles.Lookup lookup = MethodHandles.lookup();
        final MethodType methodType = MethodType.methodType(void.class, new Class<?>[]{String.class});

        final MethodHandle fooBar = lookup.findVirtual(MyClass.class, "fooBar", methodType);
        fooBar.invokeExact(myClass, "Invoked via method handle");
    }
}