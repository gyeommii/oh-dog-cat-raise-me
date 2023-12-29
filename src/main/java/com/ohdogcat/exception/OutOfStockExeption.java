package com.ohdogcat.exception;

public class OutOfStockExeption extends RuntimeException{
    public OutOfStockExeption(String message) {
        super(message);
    }
}
