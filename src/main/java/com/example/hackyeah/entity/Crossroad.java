package com.example.hackyeah.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;

@Document(collection = "crossroad")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Crossroad {
    @Id
    private String id;

    private Double latitude;
    private Double longitude;

    private List<Road> connectedRoads;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Crossroad crossroad = (Crossroad) o;
        return Objects.equals(id, crossroad.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
