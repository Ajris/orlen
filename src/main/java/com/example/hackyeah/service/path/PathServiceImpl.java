package com.example.hackyeah.service.path;

import com.example.hackyeah.entity.Crossroad;
import com.example.hackyeah.entity.PathFinderWrapper;
import com.example.hackyeah.entity.Road;
import com.example.hackyeah.entity.Vehicle;
import com.example.hackyeah.exception.RoadNotFoundException;
import com.example.hackyeah.service.crossroad.CrossroadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

@Service
public class PathServiceImpl implements PathService {

    private static final Crossroad FAKE_FIRST_PREDECESSOR = Crossroad.builder().id("-1").build();
    private final CrossroadService crossroadService;

    @Autowired
    public PathServiceImpl(CrossroadService crossroadService) {
        this.crossroadService = crossroadService;
    }

    @Override
    public List<Crossroad> findPath(PathFinderWrapper pathFinderWrapper) {
        Crossroad start = crossroadService.findById(pathFinderWrapper.getStart().getId());
        Crossroad end = crossroadService.findById(pathFinderWrapper.getEnd().getId());
        Vehicle vehicle = pathFinderWrapper.getVehicle();

        Set<Crossroad> visitedCrossroads = new HashSet<>();
        Stack<Crossroad> crossroadsToProcess = new Stack<>();
        Map<Crossroad, Crossroad> pathBetweenCrossroads = new HashMap<>();
        pathBetweenCrossroads.put(start, FAKE_FIRST_PREDECESSOR);

        crossroadsToProcess.add(start);
        visitedCrossroads.add(start);

        while (!crossroadsToProcess.empty()) {
            Crossroad current = crossroadsToProcess.pop();

            if (current.equals(end)) {
                return createSolution(pathBetweenCrossroads, current);
            }

            for (Crossroad crossroad : getNeighbours(current)) {
                if (!visitedCrossroads.contains(crossroad) && vehicleCanGoThroughRoad(vehicle, getRoadBetweenCrossroads(crossroad, current))) {
                    pathBetweenCrossroads.put(crossroadService.findById(crossroad.getId()), current);
                    crossroadsToProcess.push(crossroadService.findById(crossroad.getId()));
                    visitedCrossroads.add(crossroadService.findById(crossroad.getId()));
                }
            }
        }
        return new ArrayList<>();
    }

    private boolean vehicleCanGoThroughRoad(Vehicle vehicle, Road roadBetweenCrossroads) {
        return vehicle.getHeight() <= roadBetweenCrossroads.getHeight()
                && vehicle.getWidth() <= roadBetweenCrossroads.getWidth();
    }

    private Road getRoadBetweenCrossroads(Crossroad crossroad, Crossroad current) {
        return crossroadService.findById(current.getId())
                .getConnectedRoads()
                .stream()
                .filter(road -> road.getEnd().equals(crossroad) || road.getStart().equals(crossroad))
                .findFirst()
                .orElseThrow(RoadNotFoundException::new);
    }

    private List<Crossroad> getNeighbours(Crossroad current) {
        return crossroadService.findById(current.getId())
                .getConnectedRoads()
                .stream()
                .map(a -> List.of(a.getEnd(), a.getStart()))
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    private List<Crossroad> createSolution(Map<Crossroad, Crossroad> path, Crossroad current) {
        List<Crossroad> solution = new ArrayList<>();
        while (!current.equals(FAKE_FIRST_PREDECESSOR)) {
            solution.add(current);
            current = path.get(current);
        }
        return solution;
    }
}
