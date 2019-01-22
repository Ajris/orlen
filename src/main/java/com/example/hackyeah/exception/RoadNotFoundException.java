package com.example.hackyeah.exception;

public class RoadNotFoundException extends RuntimeException {
    public RoadNotFoundException() {
        super("Road not in database");
    }
}
