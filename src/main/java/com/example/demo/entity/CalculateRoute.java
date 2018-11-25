package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

public class CalculateRoute {
    public List<Crossroad> route(Crossroad start, Crossroad finish) {
        List<Crossroad> visited = new ArrayList<>();
        List<Crossroad> queue = new ArrayList<>();
        List<Crossroad> daWai = new ArrayList<>();
        queue.add(start);
        visited.add(start);
        while(!queue.isEmpty()) {
            Crossroad v = queue.get(0);
            queue.remove(0);
            if(v.equals(finish)) {
                return daWai;
            }
            for(Road road : v.getConnectedRoads()) {
                Crossroad u;
                if(road.getEnd().equals(v)) {
                    u = road.getStart();
                } else {
                    u = road.getEnd();
                }
                for(Crossroad crossroad : visited) {
                    if(crossroad.equals(u)) break;
                }
                daWai.add(v);
                queue.add(u);
                visited.add(u);
            }
        }
        return null;
    }
}
