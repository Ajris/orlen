package com.example.hackyeah.entity;

import com.example.hackyeah.validator.VehicleDataInRange;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PathFinderWrapper {
    private Crossroad start;
    private Crossroad end;

    @VehicleDataInRange
    private Vehicle vehicle;
}
