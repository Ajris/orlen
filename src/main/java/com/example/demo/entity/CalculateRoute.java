package com.example.demo.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CalculateRoute {
    private Vehicle vehicle;
    public CalculateRoute(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
    public HashMap<Crossroad, Crossroad> route(Crossroad start, Crossroad finish, ArrayList<Crossroad> crossroads) {
        HashMap<Crossroad, Crossroad> daWai = new HashMap<>();
        List<Crossroad> queue = new ArrayList<>();
        List<Crossroad> visited = new ArrayList<>(); //change to HashMap
        daWai.put(start, null);
        visited.add(start);
        queue.add(start);
        while(!queue.isEmpty()) {
            Crossroad v = queue.get(0);
            queue.remove(0);
            if(v.equals(finish)) {
                return daWai;
            }
            for(Road road : v.getConnectedRoads()) {
                Crossroad u;
                Direction direction;
                if(road.getEnd().equals(v)) {
                    u = road.getStart();
                    direction = Direction.FORWARD;
                } else {
                    u = road.getEnd();
                    direction = Direction.BACKWARDS;
                }
                for(Crossroad cr : crossroads) {
                    if(u.equals(cr)) u = cr;
                }
                if(vehicle.canPass(road, direction)) {
                    boolean flag = true;
                    for (Crossroad crossroad : visited) {
                        if (crossroad.equals(u)) flag = false;
                    }
                    if(flag) {
                        daWai.put(u, v);
                        queue.add(u);
                        visited.add(u);
                    }
                }
            }
        }
        //Check if route exists
        return null;
    }

}
