package com.example;

import java.io.*;
import java.nio.file.Files;
import java.util.Objects;
import java.util.Random;

public class App {
    static native int call(int a, int b);

    static {
        String os = System.getProperty("os.name").toLowerCase();
        String resourcePath;
        String libFileName;
        if (os.contains("win")) {
            resourcePath = "/native/app.dll";
            libFileName = "app.dll";
        } else if (os.contains("mac")) {
            resourcePath = "/native/libapp.dylib";
            libFileName = "libapp.dylib";
        } else { // Linux / Unix
            resourcePath = "/native/libapp.so";
            libFileName = "libapp.so";
        }

        File tempDir = new File(System.getProperty("java.io.tmpdir"));
        File tempFile = new File(tempDir, libFileName);
        if (tempFile.exists()) {
            tempFile.delete();
        }
        try (
                InputStream in = Objects.requireNonNull(App.class.getResourceAsStream(resourcePath));
                OutputStream out = Files.newOutputStream(tempFile.toPath());
        ) {
            byte[] buffer = new byte[1024];
            int len;
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        tempFile.setExecutable(true);
        tempFile.deleteOnExit();

        System.load(tempFile.getAbsolutePath());
    }

    public static void main(String[] args) {
        Random rand = new Random();
        int a = rand.nextInt(100);
        int b = rand.nextInt(100);
        System.out.println("call native function adding " + a + " and " + b);
        int result = call(a, b);
        System.out.println("the result is " + result);
    }
}
