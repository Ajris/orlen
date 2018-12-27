package com.example.hackyeah.controller;

import com.example.hackyeah.entity.Crossroad;
import com.example.hackyeah.service.CrossroadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CrossroadController {

    private final CrossroadService crossroadService;

    @Autowired
    public CrossroadController(CrossroadService crossroadService) {
        this.crossroadService = crossroadService;
    }

    @GetMapping(value = "/crossroads")
    public List<Crossroad> getCrossroads() {

        return crossroadService.findAll();
    }

    @PostMapping(value = "/crossroads")
    public void addCrossroad(@RequestBody Crossroad crossroad) {
        crossroadService.save(crossroad);
    }

    @DeleteMapping(value = "/crossroads/{id}")
    public void deleteCrossroad(@PathVariable String id) {
        crossroadService.deleteById(id);
    }

    @PutMapping(value = "/crossroads")
    public void setCrossroad(@RequestBody Crossroad crossroad) {
        crossroadService.changePlace(crossroad);
    }
}
