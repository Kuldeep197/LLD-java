package org.java.rate_limiter.strategy;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LeakyBucketRateLimiter implements RateLimiter{

    private Integer capacity;
    private Integer leakRate;
    private Queue<String> requestQueue;
    private ScheduledExecutorService executorService;

    public LeakyBucketRateLimiter(int capacity, int leakRate){
        this.capacity = capacity;
        this.leakRate = leakRate;
        this.requestQueue = new LinkedList<>();
        this.executorService = Executors.newScheduledThreadPool(1);
        startLeaking();
    }

    public void startLeaking(){
        executorService.scheduleAtFixedRate(() -> {
            if(!requestQueue.isEmpty()) {
                String request = requestQueue.poll();
                processRequest(request);
            }
        },0, 1000/leakRate, TimeUnit.MILLISECONDS);
    }

    public void processRequest(String request){
        System.out.println("processing request " + request);
    }

    public boolean submitRequest(String request){
        if(requestQueue.size() >= capacity){
            System.out.println("Can not accept more requests");
            return false;
        }

        requestQueue.offer(request);
        System.out.println("Request accepted: " + request);
        return true;
    }


    public void shutdown(){
        executorService.shutdown();
    }


}
