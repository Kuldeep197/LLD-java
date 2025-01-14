package org.java.rate_limiter;

import org.java.rate_limiter.strategy.RateLimiter;
import org.java.rate_limiter.strategy.TokenBucketRateLimiter;

public class Launcher {

    public static void main(String[] args) {
        TokenBucketRateLimiter tokenBucketRateLimiter = new TokenBucketRateLimiter(10, 5);
        System.out.println("Is Requests allowed with " + 10 + " requests "+ tokenBucketRateLimiter.allowRequest(10));
        System.out.println("Is Requests allowed with " + 20 + " requests "+ tokenBucketRateLimiter.allowRequest(20));
        System.out.println("Is Requests allowed with " + 5 + " requests "+ tokenBucketRateLimiter.allowRequest(5));
    }
}
