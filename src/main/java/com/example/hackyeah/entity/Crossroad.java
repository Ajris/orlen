package com.example.hackyeah.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "crossroad")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Crossroad {
    @Id
    private String id;

    private Double latitude;
    private Double longitude;

    private List<Road> connectedRoads = new ArrayList<>();
}
