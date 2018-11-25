package com.example.demo.entity;

public class Vehicle {
    private int height;
    private int width;
    private int weight;
    public boolean canPass(Road road, Direction direction) {
/*        if(road.isClosed(direction)) return false;
        if(this.width > road.getWidth()) return false;
        if(this.height > road.getHeight()) return false;
        if(this.weight > road.getMaxLoad()) return false;*/
        return true;
    }
}
