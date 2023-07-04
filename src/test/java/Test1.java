import org.junit.jupiter.api.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class Test1 {
    @Test
    public void threadPool() {
        System.out.println("---------");

        // Create a new ThreadPoolExecutor
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                5,                   // Core pool size
                10,                  // Maximum pool size
                1,                   // Thread keep-alive time
                TimeUnit.MINUTES,    // Keep-alive time unit
                new LinkedBlockingQueue<>() // Work queue
        );

        // Execute a task
        threadPool.execute(() -> {
            // Task logic
            System.out.println("Task executed!");
        });

        for (int i = 0; i < 20; i++) {
            int finalI = i;
            threadPool.submit(() -> {
                // Perform some task
                System.out.println("Executing task" + finalI + "with " +Thread.currentThread().getName());
            });
        }


        // Shutdown the thread pool
        threadPool.shutdown();

        System.out.println("---------");

    }

    @Test
    void threadPool1() {
        // Create a new ThreadPoolExecutor
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                2,                        // Core pool size
                5,                        // Maximum pool size
                1,                        // Thread keep-alive time
                TimeUnit.SECONDS,         // Keep-alive time unit
                new ArrayBlockingQueue<>(10) // Work queue
        );

        // Set rejection handler for when the queue is full
        threadPool.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());

        // Execute tasks using the thread pool
        for (int i = 0; i < 6; i++) {
            final int taskNumber = i;
            threadPool.execute(() -> {
                System.out.println("Executing Task " + taskNumber + " in Thread: " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000); // Simulate task execution
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println("Completed Task " + taskNumber + " in Thread: " + Thread.currentThread().getName());
            });
        }

        // Shutdown the thread pool gracefully
        threadPool.shutdown();
        try {
            if (!threadPool.awaitTermination(5, TimeUnit.SECONDS)) {
                // If the tasks do not complete within the specified timeout,
                // forcefully terminate the remaining tasks
                threadPool.shutdownNow();
            }
        } catch (InterruptedException e) {
            threadPool.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    @Test
    void runnable(){

    }
}
