package se.omegapoint.deogun.indy.bytecode;

import java.io.File;
import java.io.FileOutputStream;

import static se.omegapoint.deogun.indy.bytecode.ByteCodeFactory.byteCodeOf;

public class FileGenerator {

    private static final String BOOTSTRAP_CLASS_NAME = "se/omegapoint/deogun/indy/BootstrapFactory";
    private static final String CLASS_NAME = "se/omegapoint/deogun/indy/ClassWithInvokedynamic";

    public static void main(String[] args) throws Exception {
        final FileOutputStream fos = new FileOutputStream(new File("target/classes/" + CLASS_NAME + ".class"));

        fos.write(byteCodeOf(CLASS_NAME, BOOTSTRAP_CLASS_NAME, "bootstrap"));

        fos.close();
    }

}
