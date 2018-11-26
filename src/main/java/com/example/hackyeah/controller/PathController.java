package com.example.hackyeah.controller;

import com.example.hackyeah.entity.Crossroad;
import com.example.hackyeah.entity.PathFinderWrapper;
import com.example.hackyeah.repository.CrossroadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PathController {

    @Autowired
    private CrossroadRepository crossroadRepository;

    @PutMapping(value = "/findRoute")
    public List<Crossroad> createRoute(@RequestBody PathFinderWrapper routeSolverWrapper) {
        Crossroad start = crossroadRepository.findById(routeSolverWrapper.getStart().getId()).get();
        Crossroad end = crossroadRepository.findById(routeSolverWrapper.getEnd().getId()).get();

        return crossroadRepository.findAll();
    }
}
