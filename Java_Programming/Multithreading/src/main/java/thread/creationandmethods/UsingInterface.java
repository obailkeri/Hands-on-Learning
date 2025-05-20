package thread.creationandmethods;

public class UsingInterface implements Runnable {
    @Override
    public void run() {
        for(int i=0; i<20; i++) {
            if(i == 5) {
                try {
                    System.out.println("zzz... " + Thread.currentThread().getName() + " is sleeping ...zzz");
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    System.out.println("Interface thread got InterruptedException : " + e);
                }
            }
            System.out.println("Thread " + Thread.currentThread().getName() + " : " + i + " is running...");
        }
    }
}
