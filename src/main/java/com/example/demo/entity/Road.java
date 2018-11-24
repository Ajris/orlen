package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "road")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Road {
    @Id
    private String id;

    private Crossroad start;
    private Crossroad end;
    private Double width;
    private Double length;
    private Double height;
}
