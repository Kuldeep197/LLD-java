package org.java.rate_limiter.strategy;

import java.time.Instant;

public class TokenBucketRateLimiter implements RateLimiter{

    private final Integer capacity;
    private final Integer fillRate;
    private double tokens;
    private Instant lastRefillTimeStamp;

    public TokenBucketRateLimiter(int capacity, int fillRate){
        this.capacity = capacity;
        this.fillRate = fillRate;
        this.tokens = capacity;
        this.lastRefillTimeStamp = Instant.now();
    }

    public synchronized boolean allowRequest(int token){
        refillTokens();
        System.out.println("Current tokens after refill " + this.tokens);
        if(tokens - token < 0) return false; // request now allowed.
        tokens -= tokens;
        return true;
    }

    public void refillTokens(){
        Instant now = Instant.now();

        Double tokenToAdd =((now.toEpochMilli() - lastRefillTimeStamp.toEpochMilli())/1000.0) * fillRate;
        tokens = Math.min(capacity, tokens + tokenToAdd);
        lastRefillTimeStamp = now;
    }

}
