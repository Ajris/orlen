package com.example.hackyeah.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
public class RoadWrapper {
    private Crossroad startingCrossroad;
    private Crossroad endingCrossroad;
    private Road road;
}
