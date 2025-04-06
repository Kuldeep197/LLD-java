package org.java.rate_limiter;

import org.java.rate_limiter.strategy.FixedWindowRateLimiter;
import org.java.rate_limiter.strategy.LeakyBucketRateLimiter;
import org.java.rate_limiter.strategy.SlidingWindowRateLimiter;
import org.java.rate_limiter.strategy.TokenBucketRateLimiter;

public class Launcher {

    public static void main(String[] args) throws InterruptedException {
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


        FixedWindowRateLimiter fixedWindowRateLimiter = new FixedWindowRateLimiter(3, 1000l);
        System.out.println(fixedWindowRateLimiter.allowRequest("UserId"));
        System.out.println(fixedWindowRateLimiter.allowRequest("UserId"));
        System.out.println(fixedWindowRateLimiter.allowRequest("UserId"));
        System.out.println(fixedWindowRateLimiter.allowRequest("UserId"));
        System.out.println(fixedWindowRateLimiter.allowRequest("UserId"));
        System.out.println(fixedWindowRateLimiter.allowRequest("UserId"));
        System.out.println(fixedWindowRateLimiter.allowRequest("UserId1"));
        Thread.sleep(1000);
        System.out.println(fixedWindowRateLimiter.allowRequest("UserId"));


        SlidingWindowRateLimiter slidingWindowRateLimiter = new SlidingWindowRateLimiter(3,1000l);System.out.println(fixedWindowRateLimiter.allowRequest("UserId"));
        System.out.println(slidingWindowRateLimiter.allowRequest("UserId"));
        System.out.println(slidingWindowRateLimiter.allowRequest("UserId"));
        System.out.println(slidingWindowRateLimiter.allowRequest("UserId"));
        System.out.println(slidingWindowRateLimiter.allowRequest("UserId"));
        System.out.println(slidingWindowRateLimiter.allowRequest("UserId"));
        System.out.println(slidingWindowRateLimiter.allowRequest("UserId1"));
        Thread.sleep(1000);
        System.out.println(slidingWindowRateLimiter.allowRequest("UserId"));
    }
}
