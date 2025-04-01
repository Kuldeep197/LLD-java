package org.java.rate_limiter;

import org.java.rate_limiter.strategy.LeakyBucketRateLimiter;
import org.java.rate_limiter.strategy.RateLimiter;
import org.java.rate_limiter.strategy.TokenBucketRateLimiter;

public class Launcher {

    public static void main(String[] args) {
        TokenBucketRateLimiter tokenBucketRateLimiter = new TokenBucketRateLimiter(10, 5);
        System.out.println("Is Requests allowed with " + 10 + " requests "+ tokenBucketRateLimiter.allowRequest(10));
        System.out.println("Is Requests allowed with " + 20 + " requests "+ tokenBucketRateLimiter.allowRequest(20));
        System.out.println("Is Requests allowed with " + 5 + " requests "+ tokenBucketRateLimiter.allowRequest(5));


        LeakyBucketRateLimiter leakyBucketRateLimiter = new LeakyBucketRateLimiter(3,1);
        System.out.println(leakyBucketRateLimiter.submitRequest("Request 1"));
        System.out.println(leakyBucketRateLimiter.submitRequest("Request 2"));
        System.out.println(leakyBucketRateLimiter.submitRequest("Request 3"));
        System.out.println(leakyBucketRateLimiter.submitRequest("Request 4"));
        System.out.println(leakyBucketRateLimiter.submitRequest("Request 5"));
        System.out.println(leakyBucketRateLimiter.submitRequest("Request 6"));
        System.out.println(leakyBucketRateLimiter.submitRequest("Request 7"));
        leakyBucketRateLimiter.shutdown();
    }
}
