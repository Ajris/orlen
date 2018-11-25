package com.example.demo.controller;

import com.example.demo.entity.CalculateRoute;
import com.example.demo.entity.Crossroad;
import com.example.demo.entity.Road;
import com.example.demo.entity.RouteCombineWrapper;
import com.example.demo.entity.RouteSolverWrapper;
import com.example.demo.repository.CrossroadRepository;
import com.example.demo.repository.RoadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class CrossroadController {

    @Autowired
    private CrossroadRepository crossroadRepository;

    @Autowired
    private RoadRepository roadRepository;

    @PutMapping(value = "/addCrossroad")
    public void addCrossroad(@RequestBody Crossroad crossroad) {
        System.out.println(crossroad);
        crossroadRepository.save(crossroad);
    }

    @PutMapping(value = "/findRoute")
    public List<Crossroad> createRoute(@RequestBody RouteSolverWrapper routeSolverWrapper) {
        CalculateRoute calculateRoute = new CalculateRoute(routeSolverWrapper.getVehicle());
        Crossroad start = crossroadRepository.findById(routeSolverWrapper.getStart().getId()).get();
        Crossroad end = crossroadRepository.findById(routeSolverWrapper.getEnd().getId()).get();
        Map<Crossroad, Crossroad> map = calculateRoute.route(start,
                end,
                crossroadRepository.findAll());
        System.out.println(map);

        return convertRoute(map, end);
    }

    public List<Crossroad> convertRoute(Map<Crossroad, Crossroad> hashMap, Crossroad lastCrossroad) {
        List<Crossroad> list = new ArrayList<>();
        while (lastCrossroad != null) {
            list.add(lastCrossroad);
            lastCrossroad = hashMap.get(lastCrossroad);
        }
        return list;
    }

    @PutMapping(value = "/addRoad")
    public void addRoad(@RequestBody RouteCombineWrapper routeCombineWrapper) {
        System.out.println(routeCombineWrapper.getC1());
        System.out.println(routeCombineWrapper.getC2());
        System.out.println(routeCombineWrapper.getR1());


        Crossroad toSave1 = crossroadRepository.findById(routeCombineWrapper.getC1().getId()).get();
        Crossroad toSave2 = crossroadRepository.findById(routeCombineWrapper.getC2().getId()).get();

        Road road = roadRepository.save(routeCombineWrapper.getR1());

        toSave1.getConnectedRoads().add(road);
        toSave2.getConnectedRoads().add(road);
        crossroadRepository.save(toSave1);
        crossroadRepository.save(toSave2);
    }

    @PutMapping(value = "/setroad")
    public void setRoad(@RequestBody Road road) {
        Road toSaveRoad = roadRepository.findById(road.getId()).get();
        toSaveRoad.setWidth(road.getWidth());
        toSaveRoad.setHeight(road.getHeight());
        roadRepository.save(toSaveRoad);
    }

    //    @PostConstruct
    public void justAddRoutes() {
        List<Crossroad> crossroads = crossroadRepository.findAll();
        Road road = new Road();
        for (int i = 0; i < crossroads.size(); i++) {
            double maximus = 1000000;

            for (int j = i + 1; j < crossroads.size(); j++) {
                double cos = Math.pow((crossroads.get(j).getLatitude() - crossroads.get(i).getLatitude()), 2)
                        + Math.pow(crossroads.get(j).getLongitude() - crossroads.get(i).getLongitude(), 2);
                if (cos < maximus) {
                    road = Road.builder()
                            .start(crossroads.get(i))
                            .end(crossroads.get(j))
                            .build();

                    maximus = cos;
                }

            }

            roadRepository.save(road);
        }
    }

    @PutMapping(value = "/setCrossroad")
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

    @PutMapping(value = "/deleteRoute")
    public void deleteRoute(@RequestBody Road road) {
        roadRepository.delete(road);
    }

    //        @PostConstruct
    public void niechGmaciozamknieryja() {
        List<Road> currentRoads = roadRepository.findAll();
        currentRoads
                .forEach(road -> {
                    System.out.println(road);
                    if (road.getStart().getId() != null)
                        crossroadRepository.findById(road.getStart().getId())
                                .ifPresent(crossroad -> {
                                    List<Road> roads = new ArrayList<>();
                                    roads.add(road);
                                    crossroad.setConnectedRoads(roads);
                                    crossroadRepository.save(crossroad);
                                });
                    if (road.getEnd().getId() != null)
                        crossroadRepository.findById(road.getEnd().getId())
                                .ifPresent(crossroad -> {
                                    List<Road> roads = new ArrayList<>();
                                    roads.add(road);
                                    crossroad.setConnectedRoads(roads);
                                    crossroadRepository.save(crossroad);
                                });

                });
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
