package com.example;

import java.util.Random;

public class App {
    static native int call(int a, int b);

    static {
        System.out.println(System.getProperty("user.dir"));
        System.load("C:\\Users\\User\\Documents\\Java\\JNITest\\build\\Debug\\app.dll");
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
