package org.java.multithreading;

import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumer {

    private final Queue<String> messages = new LinkedList<>();
    private final int capacity = 5;


    public synchronized void publish() throws InterruptedException {
        while(messages.size() == capacity) {
            wait();
        }
        System.out.println("Publishing message : " + Thread.currentThread().getName());
        messages.add("message");
        notifyAll();
    }

    public synchronized void consume() throws InterruptedException {
        while(messages.isEmpty()){
            wait();
        }
        System.out.println("Consuming message : " + Thread.currentThread().getName() + " message - " + messages.poll());
        notifyAll();

    }
    public static void main(String...args){
        ProducerConsumer producerConsumer = new ProducerConsumer();

        Thread producer = new Thread(() -> {
           try{
               while (true) {
                   producerConsumer.publish();
                   Thread.sleep(3000);
               }
           } catch (InterruptedException ex){
               Thread.currentThread().interrupt();
           }
        });

        Thread consumer1 = new Thread(() -> {
            try{
                producerConsumer.consume();
                Thread.sleep(3000);
            } catch (InterruptedException ex){
                Thread.currentThread().interrupt();
            }
        });

        Thread consumer2 = new Thread(() -> {
            try{
                producerConsumer.consume();
                Thread.sleep(3000);
            } catch (InterruptedException ex){
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        consumer1.start();
        consumer2.start();

    }
}
