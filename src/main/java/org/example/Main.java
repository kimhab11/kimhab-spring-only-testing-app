package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void testRunnable(){
        TestRunnable testRunnable = new TestRunnable();
        Thread thread = new Thread(testRunnable);
        thread.start();
    }
    public static void main(String[] args) {
        System.out.println("Hello world!");
        testRunnable();
    }
}