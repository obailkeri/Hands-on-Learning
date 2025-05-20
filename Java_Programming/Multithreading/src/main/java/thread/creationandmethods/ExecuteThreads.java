package thread.creationandmethods;

public class ExecuteThreads {

    public static void main(String[] args) throws InterruptedException {
        // ---- Creating threads ----

        UsingClass thread1 = new UsingClass("Thread1");

        UsingInterface intr = new UsingInterface();
        Thread thread2 = new Thread(intr, "Thread2");

        // Lambda expression for Interfaces that have SAM (single abstract method)
        Runnable t3 = () -> {
            for (int i = 0; i < 20; i++) {
                System.out.println("Thread " + Thread.currentThread().getName() + " : " + i + " is running...");
            }
        };
        Thread thread3 = new Thread(t3, "thread3");

        Thread thread4 = new Thread(() -> System.out.println("Thread4 is executed !"));

        // ---- Trying out thread methods ----
        System.out.println("\n==== Thread methods ====\n");

        // Sets thread priority but does not guarantee always
        thread1.setPriority(Thread.MAX_PRIORITY);
        // Thread in is Runnable state. When JVM picks for execution thread will be in Running state
        thread1.start();
        System.out.println("Thread 1 state : " + thread1.getState());

        // JVM doesn't wait for thread2 to get completed, because it is Daemon thread
        thread2.setDaemon(true);
        thread2.start();

        Thread.sleep(5);
        thread3.start();

        thread4.start();

        try {
            // Main thread waits for thread1 to get completed and then implements finally block
            thread1.join();
        } catch (InterruptedException e) {
            System.out.println("Interrupted exception : " + e);
        } finally {
            System.out.println("Printed after thread1 completed execution !");
        }
    }
}
