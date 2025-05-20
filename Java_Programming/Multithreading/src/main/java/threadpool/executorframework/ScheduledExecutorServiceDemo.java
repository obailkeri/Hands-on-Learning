package threadpool.executorframework;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServiceDemo {
    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(3);
        executorService.scheduleAtFixedRate(() -> System.out.println("Checking after every 5 seconds -> Time = "
                        + System.currentTimeMillis()/1000),
                2, 5, TimeUnit.SECONDS);
        Thread.sleep(15000);
        executorService.shutdownNow();
        System.out.println("\nScheduler is shut down... Bye!");
    }
}
