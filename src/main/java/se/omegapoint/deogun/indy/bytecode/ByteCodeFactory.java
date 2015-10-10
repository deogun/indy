package se.omegapoint.deogun.indy.bytecode;

import org.objectweb.asm.*;

import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

import static org.objectweb.asm.Opcodes.*;

class ByteCodeFactory {

    public static byte[] byteCodeOf(final String className, final String bootstrapFactoryClassName, final String bootstrapMethodName) throws Exception {

        final ClassWriter cw = new ClassWriter(0);

        classDeclaration(className, cw);

        createDefaultConstructor(cw);

        createMethodWithInvokedynamic(bootstrapFactoryClassName, bootstrapMethodName, cw);

        cw.visitEnd();

        return cw.toByteArray();
    }

    private static void classDeclaration(final String className, final ClassWriter cw) {
        cw.visit(V1_7, ACC_PUBLIC + ACC_SUPER, className, null, "java/lang/Object", null);
    }

    private static void createDefaultConstructor(final ClassWriter cw) {
        final MethodVisitor mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);

        mv.visitCode();
        mv.visitVarInsn(ALOAD, 0);
        mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
        mv.visitInsn(RETURN);
        mv.visitMaxs(1, 1);
        mv.visitEnd();
    }

    private static void createMethodWithInvokedynamic(final String bootstrapFactoryClassName, final String bootstrapMethodName, final ClassWriter cw) {
        final MethodVisitor mv = cw.visitMethod(ACC_PUBLIC, "indy", "()V", null, null);

        final MethodType mt = MethodType.methodType(CallSite.class, MethodHandles.Lookup.class, String.class, MethodType.class);

        final Handle bootstrap = new Handle(H_INVOKESTATIC, bootstrapFactoryClassName, bootstrapMethodName, mt.toMethodDescriptorString());

        mv.visitCode();
        mv.visitInvokeDynamicInsn("message", "()V", bootstrap);
        mv.visitInsn(RETURN);
        mv.visitMaxs(0, 1);
        mv.visitEnd();
    }
}
