package executorframework;

import java.util.concurrent.*;

public class CallableExample {
    public static void main(String[] args) {
        //1. Create an ExecutorService
        ExecutorService executor = Executors.newSingleThreadExecutor();

        //2. Create a Callable task [returns a result(Future)]
        Callable<Integer> task = () -> {
            System.out.println("Calculating 10 * 20...");
            Thread.sleep(1000);
            return 10 * 20;
        };

        //3. Submit Callable(task) and get a Future
        Future<Integer> future = executor.submit(task);

        try{
            //4. Do other work if needed, then get the result
            System.out.println("Waiting for the result...");
            Integer result = future.get();
            System.out.println("Result: "+result);
        }catch (InterruptedException | ExecutionException e){
            e.printStackTrace();
        }finally {
            executor.shutdown();
        }
    }
}
