package com.example.hackyeah.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RouteSolverWrapper {
    private Crossroad start;
    private Crossroad end;
    private Vehicle vehicle;
}
