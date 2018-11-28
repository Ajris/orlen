package com.example.hackyeah.service;

import com.example.hackyeah.entity.Crossroad;
import com.example.hackyeah.entity.Road;
import com.example.hackyeah.entity.RoadAdderWrapper;

import java.util.List;

public interface RoadService {
    List<Road> findAll();
    Road save(Road crossroad);
    Road findById(String id);
    void deleteById(String id);
    void updateCrossroads(RoadAdderWrapper roadAdderWrapper);
}
