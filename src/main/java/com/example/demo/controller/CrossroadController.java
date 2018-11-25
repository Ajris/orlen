package com.example.demo.controller;

import com.example.demo.entity.CalculateRoute;
import com.example.demo.entity.Crossroad;
import com.example.demo.entity.Road;
import com.example.demo.repository.CrossroadRepository;
import com.example.demo.repository.RoadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CrossroadController {

    @Autowired
    private CrossroadRepository crossroadRepository;

    @Autowired
    private RoadRepository roadRepository;

    @PutMapping(value = "/setroad")
    public void setRoad(@RequestBody Road road){
        Road toSaveRoad = roadRepository.findById(road.getId()).get();
        toSaveRoad.setWidth(road.getWidth());
        toSaveRoad.setHeight(road.getHeight());
        roadRepository.save(toSaveRoad);
    }

    @PutMapping(value = "/setCrossroad")
    public void setCrossroad(@RequestBody Crossroad crossroad){
        crossroadRepository.save(crossroad);
    }

//    @PutMapping(value = "/calculateNewRoad")
//    public List<Road> getCalculatedRoad(Crossroad crossroad){
//
//    }

    @GetMapping(value = "/crossroads")
    public List<Crossroad> getCrossroads() {
        return crossroadRepository.findAll();
    }

    @GetMapping(value = "/roads")
    public List<Road> getRoads() {
        return roadRepository.findAll();
    }
}
