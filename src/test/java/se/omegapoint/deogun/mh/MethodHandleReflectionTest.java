package se.omegapoint.deogun.mh;

import org.junit.Test;

import java.lang.invoke.MethodHandle;

import static java.lang.invoke.MethodHandles.lookup;
import static java.lang.invoke.MethodType.methodType;

public class MethodHandleReflectionTest {

    @Test
    public void should_invoke_print_via_reflection() throws Throwable {
        MyClass.class.getDeclaredMethod("print", String.class).invoke(new MyClass(), "print method invoked via reflection");
    }

    @Test
    public void should_invoke_super_method_via_reflection() throws Throwable {
        MyClass.class.getSuperclass().getDeclaredMethod("superPrint", String.class).invoke(new MyClass(), "Hello");
    }

    @Test(expected = NoSuchMethodException.class)
    public void should_not_find_method() throws Throwable {
        MyClass.class.getDeclaredMethod("superPrint", String.class).invoke(new MyClass(), "Hello");
    }

    @Test
    public void should_invoke_print_via_method_handle() throws Throwable {
        final MethodHandle print = lookup().findVirtual(MyClass.class, "print", methodType(void.class, new Class[]{String.class}));

        print.invoke(new MyClass(), "Hello from Method Handle");
    }

    @Test
    public void should_invoke_super_method_via_method_handle() throws Throwable {
        final MethodHandle superPrint = lookup().findVirtual(MyClass.class, "superPrint", methodType(void.class, new Class[]{String.class}));

        superPrint.invoke(new MyClass(), "Hello from Method Handle");
    }

    @Test
    public void should_gain_access_to_private_method() throws Throwable {
        final MethodHandle printInternal = MyClass.lookup().findVirtual(MyClass.class, "printInternal", methodType(void.class));

        printInternal.invoke(new MyClass());
    }
}
