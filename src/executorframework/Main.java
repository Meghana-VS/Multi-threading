package executorframework;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(9);
        for (int i=1; i<10; i++){
            int finalI = i;
            executor.submit(()-> {
                long result = factorial(finalI);
                System.out.println(result);
            });
        }
        executor.shutdown();
        try{
            while (!executor.awaitTermination(50, TimeUnit.SECONDS)){
                executor.shutdownNow();
            }
        }catch (InterruptedException e){
            executor.shutdownNow();
        }
    }

    private static long factorial(int n){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        long result = 1;
        for(int i=1; i<=n; i++){
            result *= i;
        }
        return result;
    }
}
