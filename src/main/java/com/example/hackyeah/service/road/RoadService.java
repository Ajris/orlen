package com.example.hackyeah.service.road;

import com.example.hackyeah.entity.Road;
import com.example.hackyeah.entity.RoadWrapper;

import java.util.List;

public interface RoadService {
    List<Road> findAll();

    Road save(Road road);

    Road findById(String id);

    void deleteById(String id);

    void updateCrossroads(RoadWrapper roadAdderWrapper);
}
