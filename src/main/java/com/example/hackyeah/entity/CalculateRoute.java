package com.example.hackyeah.entity;

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
                return daWai;
            }
            System.out.println(v);
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
                if (visited.stream().filter(x -> compareIt(x, v)).count() <= 0) {
                    daWai.put(v, u);
                    queue.add(v);
                    visited.add(v);
                }
            }
        }
        return null;
    }

    private boolean compareIt(Crossroad a1, Crossroad a2) {
        return a1.getId().equals(a2.getId());
    }
}