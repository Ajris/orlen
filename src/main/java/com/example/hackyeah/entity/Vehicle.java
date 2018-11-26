package com.example.hackyeah.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Vehicle {
    private Double height;
    private Double width;
    private Double weight;

    public Double getHeight() {
        return height;
    }

    public Double getWidth() {
        return width;
    }

    public Double getWeight() {
        return weight;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }


    public boolean canPass(Road road, Direction direction) {
//        if(road.isClosed(direction)) return false;
//        if(Double.compare(this.width, road.getWidth()) > 0) return false;
//        if(Double.compare(this.height, road.getHeight()) > 0) return false;
//        if(Double.compare(this.weight, road.getMaxLoad()) > 0) return false;
        return true;
    }
}
