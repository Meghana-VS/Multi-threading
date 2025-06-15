import java.util.concurrent.CountDownLatch;

public class LatchExample {
    public static void main(String[] args) throws InterruptedException {
        //Create a latch for 3 threads
        CountDownLatch latch = new CountDownLatch(3);

        //create 3 worker threads
        for(int i=1; i<=3; i++){
            int id = i;
            new Thread(() ->{
                System.out.println("Worker "+id+" is doing work...");
                try {
                    Thread.sleep(5000);     //simulate work
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Worker "+id+" finished...");
                latch.countDown();  //reduce count by 1 out of 3
            }).start();
        }
        System.out.println("Main thread waiting for workers...");
        latch.await();   //waits until count reaches 0
        System.out.println("All workers done. Main thread resumes.");
    }
}
