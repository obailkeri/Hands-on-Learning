package thread.executorframework;

import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        Callable<String> c1 = () -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("task 1 -> Thread " + Thread.currentThread().getName() + " is running with i = " + i);
            }
            return "Hello1";
        };

        Callable<String> c2 = () -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("task 2 -> Thread " + Thread.currentThread().getName() + " is running with i = " + i);
            }
            return "Hello2";
        };

        Callable<String> c3 = () -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("task 3 -> Thread " + Thread.currentThread().getName() + " is running with i = " + i);
            }
            return "Hello3";
        };

        executor.submit(c3);

        String str = executor.invokeAny(Arrays.asList(c1, c2, c3));
        System.out.println("Winner thread => " + str);

        executor.shutdown();
        System.out.println("Shut Down..!");
    }
}
