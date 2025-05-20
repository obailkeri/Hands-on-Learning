package thread.creationandmethods;

public class UsingClass extends Thread {

    public UsingClass(String threadName) {
        super(threadName);
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            if (i == 10) {
                // Suggests JVM to pick other threads for execution
                Thread.yield();
            }
            System.out.println("Thread " + Thread.currentThread().getName() + " : " + i + " is running...");
        }
    }
}
