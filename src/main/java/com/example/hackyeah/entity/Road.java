package com.example.hackyeah.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "road")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Road {

    @Id
    private String id;

    private Crossroad start;
    private Crossroad end;

    private Double width;
    private Double height;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Road road = (Road) o;
        return Objects.equals(id, road.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
