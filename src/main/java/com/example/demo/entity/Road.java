package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
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
    private Double maxLoad;
    private boolean closedForward;
    private boolean closedBackwards;

    public void setClosedForward(boolean closedForward) {
        this.closedForward = closedForward;
    }

    public void setClosedBackwards(boolean closedBackwards) {
        this.closedBackwards = closedBackwards;
    }

    public boolean isClosed(Direction direction) {
        if(direction == Direction.FORWARD) return closedForward;
        else return  closedBackwards;
    }

    public Crossroad getStart() {
        return start;
    }

    public void setStart(Crossroad start) {
        this.start = start;
    }

    public Crossroad getEnd() {
        return end;
    }

    public void setEnd(Crossroad end) {
        this.end = end;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getMaxLoad() {
        return maxLoad;
    }

    public void setMaxLoad(Double maxLoad) {
        this.maxLoad = maxLoad;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
