package org.java.rate_limiter.strategy;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class FixedWindowRateLimiter implements RateLimiter{

    private Integer requestCap;
    private Long windowSize;
    private Map<String, WindowCounter> counterMap;

    public FixedWindowRateLimiter(int cap, Long size){
        this.requestCap = cap;
        this.windowSize = size;
        counterMap = new HashMap<>();
    }

    public boolean allowRequest(String userId){
        Long currentTime = System.currentTimeMillis();
        WindowCounter counter = counterMap.computeIfAbsent(userId, c -> new WindowCounter());
        return counter.processRequest(currentTime);
    }

    private class WindowCounter{
        private Long windowStartTime = System.currentTimeMillis();
        private AtomicInteger counter = new AtomicInteger(0);

        public boolean processRequest(Long currentTime){
            if(currentTime - windowStartTime >= windowSize){
                counter.set(0);
                windowStartTime = currentTime;
            }

            if(counter.get() < requestCap){
                System.out.println("Processing request");
                counter.incrementAndGet();
                return true;
            }
            System.out.println("Not processing request");
            return false;
        }

    }
}
