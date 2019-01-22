package com.example.hackyeah.entity;

import com.example.hackyeah.validator.RoadDataInRange;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoadWrapper {
    private Crossroad startingCrossroad;
    private Crossroad endingCrossroad;

    @RoadDataInRange
    private Road road;
}
