package com.example.demo.controller;

import com.example.demo.entity.Crossroad;
import com.example.demo.entity.Road;
import com.example.demo.repository.CrossroadRepository;
import com.example.demo.repository.RoadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CrossroadController {

    @Autowired
    private CrossroadRepository crossroadRepository;

    @Autowired
    private RoadRepository roadRepository;

    @GetMapping(value = "/crossroads")
    public List<Crossroad> getCrossroads() {
        return crossroadRepository.findAll();
    }

    @GetMapping(value = "/roads")
    public List<Road> getRoads() {
        return roadRepository.findAll();
    }
}
