package com.example.hackyeah.controller;

import com.example.hackyeah.entity.Crossroad;
import com.example.hackyeah.repository.CrossroadRepository;
import com.example.hackyeah.repository.RoadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping(value = "/crossroads")
    public void addCrossroad(@RequestBody Crossroad crossroad) {
        crossroadRepository.save(crossroad);
    }


    @PutMapping(value = "/crossroads")
    public void setCrossroad(@RequestBody Crossroad crossroad1) {
        Crossroad crossroad = crossroadRepository.findById(crossroad1.getId())
                .get();
        crossroad.setLatitude(crossroad1.getLatitude());
        crossroad.setLongitude(crossroad1.getLongitude());
        System.out.println(crossroad);
        if (crossroad.getConnectedRoads() != null)
            crossroad.getConnectedRoads()
                    .forEach((road -> {
                        System.out.println(road);
                        if (road.getEnd().getId().equals(crossroad.getId())) {

//                            road.setEnd(Crossroad.builder()
//                                    .latitude(crossroad1.getLatitude())
//                                    .longitude(crossroad1.getLongitude())
//                                    .build());
                            road.getEnd().setLongitude(crossroad1.getLongitude());
                            road.getEnd().setLatitude(crossroad1.getLatitude());


                        }
                        if (road.getStart().getId().equals(crossroad.getId())) {
//                            road.setStart(Crossroad.builder()
//                                    .latitude(crossroad1.getLatitude())
//                                    .longitude(crossroad1.getLongitude())
//                                    .build());
                            road.getStart().setLongitude(crossroad1.getLongitude());
                            road.getStart().setLatitude(crossroad1.getLatitude());
                        }
                        roadRepository.save(road);
                    }));
        crossroadRepository.save(crossroad);
    }

    @GetMapping(value = "/crossroads")
    public List<Crossroad> getCrossroads() {
        return crossroadRepository.findAll();
    }

}
