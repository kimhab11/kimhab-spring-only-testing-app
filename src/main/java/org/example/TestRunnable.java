package org.example;


public class TestRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println("Runnable running in a separate thread.");
    }


}
