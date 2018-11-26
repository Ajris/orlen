package com.example.hackyeah.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RouteCombineWrapper {
    private Crossroad c1;
    private Crossroad c2;
    private Road r1;
}
