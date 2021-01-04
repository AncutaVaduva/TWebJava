package com.example.lab10.exception;

public class OrderAlreadyCancelled extends RuntimeException {
    public OrderAlreadyCancelled() {
        super("The order is already canceled!");
    }
}
