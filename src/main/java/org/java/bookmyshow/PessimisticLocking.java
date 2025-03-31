package org.java.bookmyshow;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PessimisticLocking {

    Map<String, Lock> locks = new ConcurrentHashMap<>();
    public boolean bookTicket(String seatId, String userId){
        Lock lock = locks.computeIfAbsent(seatId, s -> new ReentrantLock());
        boolean acquired = false;
        try{
            acquired = lock.tryLock();

            if(acquired){
                //check if seat is already booked.
                // book seat
                return true;
            }
        } catch (Exception e){
            Thread.currentThread().interrupt();
        }finally {
            if(acquired) {
                lock.unlock();
            }
        }
        return false;
    }
}
