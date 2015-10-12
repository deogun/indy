package se.omegapoint.deogun.mh;

import org.junit.Test;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;

import static java.lang.invoke.MethodHandles.lookup;
import static java.lang.invoke.MethodType.methodType;
import static org.junit.Assert.assertEquals;

public class BasicMethodHandleTest {
    @Test
    public void should_compute_length_of_string() throws Throwable {
        final MethodHandle lengthOf = lookup().findStatic(MyClass.class, "lengthOf", methodType(int.class, new Class[]{String.class}));

        assertEquals(13, lengthOf.invoke("Hello OpKoKo!"));
    }

    @Test
    public void should_invoke_print_with_argument() throws Throwable {
        final MethodHandle print = lookup().findVirtual(MyClass.class, "print", methodType(void.class, new Class<?>[]{String.class}));

        print.invoke(new MyClass(), "Hello Hello");
    }

    @Test
    public void should_set_message_field_and_print() throws Throwable {
        final MyClass myClass = new MyClass();
        final MethodHandles.Lookup lookup = lookup();

        final MethodHandle field = lookup.findSetter(MyClass.class, "message", String.class);
        field.invoke(myClass, "Hello OpKoKo 15.2 Tranås!");

        final MethodHandle print = lookup.findVirtual(MyClass.class, "print", methodType(void.class));
        print.invoke(myClass);
    }
}
