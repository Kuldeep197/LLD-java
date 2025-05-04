package org.java.multithreading;

import java.util.concurrent.Semaphore;

public class ReadWriteLockImpl {
    private final Semaphore mutex = new Semaphore(1);
    private final Semaphore writeLock = new Semaphore(1);
    private Integer count = 0;

    public void readLock() throws InterruptedException {
        mutex.acquire();
        count++;
        if(count == 1){
            writeLock.acquire();
        }
        mutex.release();
    }

    public void releaseReadLock() throws InterruptedException {
        mutex.acquire();
        count--;
        if(count == 0){
            writeLock.release();
        }
        mutex.release();
    }

    public void writeLock() throws InterruptedException {
        writeLock.acquire();
    }

    public void releaseWriteLock(){
        writeLock.release();
    }

    public static void main(String[] args) {
        ReadWriteLockImpl readWriteLock = new ReadWriteLockImpl();

        Runnable readTask = () -> {
          try{
              System.out.println(Thread.currentThread().getName() + " trying to get lock");
              readWriteLock.readLock();
              System.out.println(Thread.currentThread().getName() + " is Reading");
              Thread.sleep(2000);
              System.out.println(Thread.currentThread().getName() + " finished Reading");
              readWriteLock.releaseReadLock();
          } catch (InterruptedException ex){
              Thread.currentThread().interrupt();
          }
        };

        Runnable writeTask = () -> {
            try{
                System.out.println(Thread.currentThread().getName() + " trying to get lock");
                readWriteLock.writeLock();
                System.out.println(Thread.currentThread().getName() + " is Writing");
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName() + " finished Writing");
                readWriteLock.releaseWriteLock();
            } catch (InterruptedException ex){
                Thread.currentThread().interrupt();
            }
        };
        Thread thread1 = new Thread(readTask, "Reader-1");
        Thread thread2 = new Thread(readTask, "Reader-2");
        Thread thread3= new Thread(writeTask, "Writer-1");

        thread1.start();
        thread3.start();
        thread2.start();
    }
}
