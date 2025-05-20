package thread.concurrency;

/*
    Always gives output equal to 3000
    Makes use of intrinsic lock present in all the Objects to handle race conditions.
    Run the program multiple times to see the result. (Always 3000)
 */
public class Synchronized {
    public static class CriticalResource {
        private int value;

        public CriticalResource() {
            value = 0;
        }

        public synchronized void increment() {
//            Below is synchronized block implementation

//            synchronized(this) {
                value++;
//            }
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