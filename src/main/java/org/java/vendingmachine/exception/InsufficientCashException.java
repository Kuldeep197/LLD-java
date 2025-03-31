package org.java.vendingmachine.exception;

public class InsufficientCashException extends RuntimeException {

    public InsufficientCashException(String message){
        super(message);
    }
}
