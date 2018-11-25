package com.example.demo.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CalculateRoute {
    private Vehicle vehicle;
    public CalculateRoute(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
    public Map<Crossroad, Crossroad> route(Crossroad start, Crossroad finish, List<Crossroad> crossroads) {
        Map<Crossroad, Crossroad> daWai = new HashMap<>();
        List<Crossroad> queue = new ArrayList<>();
        Set<Crossroad> visited = new HashSet<>(); //change to HashMap
        daWai.put(start, null);
        visited.add(start);
        queue.add(start);

        while(!queue.isEmpty()) {
            Crossroad v = queue.get(0);
            queue.remove(0);
            if(compareIt(v, finish)) {
                return daWai;
            }
            for(Road road : v.getConnectedRoads()) {
                Crossroad u;
                Direction direction;
                if(compareIt(road.getEnd(), v)) {
                    u = road.getStart();
                    direction = Direction.FORWARD;
                } else {
                    u = road.getEnd();
                    direction = Direction.BACKWARDS;
                }
                if(vehicle.canPass(road, direction)) {
                    if(!visited.contains(u)) {
                        daWai.put(u, v);
                        queue.add(u);
                        visited.add(u);
                    }
                }
            }
        }
        return null;
    }

    private boolean compareIt(Crossroad a1, Crossroad a2){
        return a1.getId().equals(a2.getId());
    }
}