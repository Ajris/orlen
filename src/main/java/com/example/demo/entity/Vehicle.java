package com.example.demo.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Vehicle {
    private Integer height;
    private Integer width;
    private Integer weight;
}
