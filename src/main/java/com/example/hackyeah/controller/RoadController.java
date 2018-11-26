package com.example.hackyeah.controller;

import com.example.hackyeah.entity.Crossroad;
import com.example.hackyeah.entity.Road;
import com.example.hackyeah.entity.RoadAdderWrapper;
import com.example.hackyeah.repository.CrossroadRepository;
import com.example.hackyeah.repository.RoadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoadController {

    @Autowired
    private CrossroadRepository crossroadRepository;

    @Autowired
    private RoadRepository roadRepository;

    @PutMapping(value = "/road/addRoad")
    public void addRoad(@RequestBody RoadAdderWrapper routeCombineWrapper) {
        Crossroad toSave1 = crossroadRepository.findById(routeCombineWrapper.getC1().getId()).get();
        Crossroad toSave2 = crossroadRepository.findById(routeCombineWrapper.getC2().getId()).get();

        Road road = roadRepository.save(routeCombineWrapper.getR1());

        toSave1.getConnectedRoads().add(road);
        toSave2.getConnectedRoads().add(road);
        crossroadRepository.save(toSave1);
        crossroadRepository.save(toSave2);
    }

    @PutMapping(value = "/road/setroad")
    public void setRoad(@RequestBody Road road) {
        Road toSaveRoad = roadRepository.findById(road.getId()).get();
        toSaveRoad.setWidth(road.getWidth());
        toSaveRoad.setHeight(road.getHeight());
        roadRepository.save(toSaveRoad);
    }

    @PutMapping(value = "/road/deleteRoad")
    public void deleteRoute(@RequestBody Road road) {
        roadRepository.delete(road);
    }

    @GetMapping(value = "/road/roads")
    public List<Road> getRoads() {
        return roadRepository.findAll();
    }
}
