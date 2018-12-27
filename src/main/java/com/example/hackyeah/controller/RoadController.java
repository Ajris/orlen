package com.example.hackyeah.controller;

import com.example.hackyeah.entity.Road;
import com.example.hackyeah.entity.RoadAdderWrapper;
import com.example.hackyeah.service.road.RoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoadController {

    private final RoadService roadService;

    @Autowired
    public RoadController(RoadService roadService) {
        this.roadService = roadService;
    }

    @PostMapping(value = "/roads")
    public void addRoad(@RequestBody RoadAdderWrapper routeCombineWrapper) {
        roadService.save(routeCombineWrapper.getR1());
        roadService.updateCrossroads(routeCombineWrapper);
    }

    @PutMapping(value = "/roads")
    public void setRoad(@RequestBody Road road) {
        roadService.save(road);
    }

    @DeleteMapping(value = "/roads/{id}")
    public void deleteRoute(@PathVariable String id) {
        roadService.deleteById(id);
    }

    @GetMapping(value = "/roads")
    public List<Road> getRoads() {
        return roadService.findAll();
    }
}
