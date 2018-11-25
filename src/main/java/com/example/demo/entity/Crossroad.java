package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Document(collection = "crossroad")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Crossroad extends HashMap<Crossroad, Crossroad> {
    @Id
    private String id;

    private Double latitude;
    private Double longitude;

    private List<Road> connectedRoads;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public List<Road> getConnectedRoads() {
        return connectedRoads;
    }

    public void setConnectedRoads(List<Road> connectedRoads) {
        this.connectedRoads = connectedRoads;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Crossroad crossroad = (Crossroad) o;
        return  Objects.equals(latitude, crossroad.latitude) &&
                Objects.equals(longitude, crossroad.longitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), latitude, longitude);
    }
}
