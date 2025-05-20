package thread.concurrency;

/*
    Might give output less than 3000, as multiple threads might update the shred resource at the same time.
    Run the program multiple times to see the result.
 */
public class RaceCondition {
    public static class CriticalResource {
        private int value;

        public CriticalResource() {
            value = 0;
        }

        public void increment() {
            value++;
        }

        public int getValue() {
            return value;
        }
    }

    public static class ThreadFunc extends Thread {
        private final String threadName;
        private final CriticalResource resource;

        public ThreadFunc(String threadName, CriticalResource resource) {
            this.threadName = threadName;
            this.resource = resource;
        }

        @Override
        public void run() {
            for (int i=1; i<=1000; i++){
//                System.out.println("Thread " + threadName + " incremented shared value : " + resource.getValue());
                resource.increment();
            }
        }
    }

    public static void main(String[] args) {
        CriticalResource sharedResource = new CriticalResource();

        ThreadFunc t1 = new ThreadFunc("t1", sharedResource);
        ThreadFunc t2 = new ThreadFunc("t2", sharedResource);
        ThreadFunc t3 = new ThreadFunc("t3", sharedResource);

        System.out.println("===== Race Condition =====");
        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            System.out.println(Thread.interrupted());
        } finally {
            System.out.println("Final value of shared resource value is : " + sharedResource.getValue());
        }
    }
}