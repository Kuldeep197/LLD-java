package org.java.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class BarrierSynchronizationPattern {

    private Integer count;
    private final Integer parties;
    private final Semaphore mutex = new Semaphore(1);
    private final Semaphore barrier = new Semaphore(0);


    public BarrierSynchronizationPattern(Integer parties) {
        this.parties = parties;
        this.count = parties;
    }

    public void await() throws InterruptedException {
        mutex.acquire();
        count--;
        if(count == 0){
            barrier.release(parties-1);
            System.out.println("Releasing all threads");
            count = parties;
            mutex.release();
        } else{
            mutex.release();
            System.out.println("Thread " + Thread.currentThread().getName() + " waiting at barrier");
            barrier.acquire();
        }
    }

    public static void main(String[] args) {
        BarrierSynchronizationPattern barrier = new BarrierSynchronizationPattern(5);
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for(int i=0;i<5;i++){
            executorService.submit(() -> {
                try{
                    System.out.println("Thread " + Thread.currentThread().getName() + " initiating implementation");
                    Thread.sleep(1000);
                    barrier.await();
                    System.out.println("Thread " + Thread.currentThread().getName() + " after 1st barrier");
                    Thread.sleep(1000);
                    barrier.await();
                    System.out.println("Thread " + Thread.currentThread().getName() + " after 2nd barrier");
                } catch (InterruptedException ex){
                    Thread.currentThread().interrupt();
                }
            });
        }
        executorService.shutdown();

        try{
            if(executorService.awaitTermination(10, TimeUnit.MILLISECONDS)){
                executorService.shutdownNow();
            }
        } catch (InterruptedException ex){
            executorService.shutdownNow();
        }
    }
}
