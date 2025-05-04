package org.java.multithreading;

public class MyThread implements Runnable{

    public static void main(String[] args) {
        MyThread runnable = new MyThread();

        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);

        thread1.start();
        thread2.start();

    }

    @Override
    public void run() {
        for(int i=0;i<5;i++){
            System.out.println("In thread "+ Thread.currentThread().getName() + " with iteration " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
