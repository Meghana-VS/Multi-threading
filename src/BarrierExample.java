import java.util.concurrent.*;

public class BarrierExample {
    public static void main(String[] args) {
        int threadCount = 3;

        CyclicBarrier barrier = new CyclicBarrier(threadCount, () -> {
            System.out.println("All workers reached the barrier. Let's proceed to next step!\n");
        });

        for(int i=1; i<=threadCount; i++){
            int id = i;
            new Thread(() -> {
                try {
                    System.out.println("Worker "+id+" is working...");
                    Thread.sleep(1000 * id);
                    System.out.println("Worker "+id+" reached the barrier.");
                    barrier.await();    // wait at barrier
                    System.out.println("Worker "+id+" is proceeding.");
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
