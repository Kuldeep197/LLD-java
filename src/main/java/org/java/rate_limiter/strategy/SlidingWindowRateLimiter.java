package org.java.rate_limiter.strategy;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class SlidingWindowRateLimiter implements RateLimiter{

    private Integer requestCap;
    private Long windowSize;
    private Map<String, WindowCounter> counterMap;


    public SlidingWindowRateLimiter(Integer requestCap, Long windowSize){
        this.requestCap = requestCap;
        this.windowSize = windowSize;
        counterMap = new HashMap<>();
    }

    public boolean allowRequest(String userId){
        Long currentTime = System.currentTimeMillis();
        WindowCounter counter = counterMap.computeIfAbsent(userId, k -> new WindowCounter());
        return counter.processRequest(currentTime);
    }
    private class WindowCounter{
        private Queue<Long> requestTimeStamps = new ConcurrentLinkedQueue<>();

        public boolean processRequest(Long currentTime){
            Long cutOffTime = currentTime - windowSize;
            if(!requestTimeStamps.isEmpty() && requestTimeStamps.peek()<= cutOffTime){
                requestTimeStamps.poll();
            }
            if(requestTimeStamps.size() < requestCap){
                System.out.println("Processing request");
                requestTimeStamps.offer(currentTime);
                return true;
            }
            System.out.println("can't process the request");
            return false;
        }
    }
}
