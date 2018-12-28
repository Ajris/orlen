package com.example.hackyeah.exception;

public class CrossroadNotFoundException extends RuntimeException {
    public CrossroadNotFoundException() {
        super("Crossroad not in database");
    }
}
