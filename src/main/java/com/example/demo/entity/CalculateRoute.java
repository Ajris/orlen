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
        Set<Crossroad> visited = new HashSet<>();
        daWai.put(start, null);
        visited.add(start);
        queue.add(start);

        while (!queue.isEmpty()) {
            Crossroad v = queue.get(0);
            queue.remove(0);
            if (compareIt(v, finish)) {
                System.out.println(daWai.size());
                return daWai;
            }
            for (Road road : crossroads.stream()
                    .filter(v1 -> compareIt(v1, v))
                    .findAny()
                    .get()
                    .getConnectedRoads()) {
                Crossroad u;
                if (compareIt(road.getEnd(), v)) {
                    u = road.getStart();
                } else {
                    u = road.getEnd();
                }
                System.out.println(u);
                if (visited.stream().filter(x -> compareIt(x, u)).count() <= 0) {
                    System.out.println("=>" + u);
                    daWai.put(u, v);
                    queue.add(u);
                    visited.add(u);
                }
            }
        }
        return null;
    }

    private boolean compareIt(Crossroad a1, Crossroad a2) {
        return a1.getId().equals(a2.getId());
    }
}