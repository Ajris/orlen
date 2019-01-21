package com.example.hackyeah.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "road")
@Data
@AllArgsConstructor
@Builder
public class Road {
    @Id
    private String id;

    private Crossroad start;
    private Crossroad end;

    private Double width;
    private Double height;

    public Road() {
    }

    public Road(Crossroad start, Crossroad end, Double width, Double height) {
        this.start = start;
        this.end = end;
        this.width = width;
        this.height = height;
    }

    public Road(Crossroad start, Crossroad end) {
        this.start = start;
        this.end = end;
    }

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
