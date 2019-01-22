package com.example.hackyeah.service.path;

import com.example.hackyeah.entity.Crossroad;
import com.example.hackyeah.entity.PathFinderWrapper;
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


        Set<Crossroad> visited = new HashSet<>();
        Stack<Crossroad> crossroadsToVisit = new Stack<>();
        Map<Crossroad, Crossroad> successorPredecessor = new HashMap<>();
        successorPredecessor.put(start, FAKE_FIRST_PREDECESSOR);

        visited.add(start);
        crossroadsToVisit.add(start);

        while (!crossroadsToVisit.empty()) {
            Crossroad current = crossroadsToVisit.pop();

            if (current.equals(end)) {
                return createSolution(successorPredecessor, current);
            }

            for (Crossroad crossroad : getNeighbours(current)) {
                if (!visited.contains(crossroad)) {
                    successorPredecessor.put(crossroadService.findById(crossroad.getId()), current);
                    crossroadsToVisit.push(crossroadService.findById(crossroad.getId()));
                    visited.add(crossroad);
                }
            }
        }
        return new ArrayList<>();
    }

    private List<Crossroad> getNeighbours(Crossroad current) {
        return crossroadService.findById(current.getId())
                .getConnectedRoads()
                .stream()
                .map(a -> List.of(a.getEnd(), a.getStart()))
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    private List<Crossroad> createSolution(Map<Crossroad, Crossroad> successorPredecessor, Crossroad current) {
        List<Crossroad> solution = new ArrayList<>();
        while (!current.equals(FAKE_FIRST_PREDECESSOR)) {
            solution.add(current);
            current = successorPredecessor.get(current);
        }
        return solution;
    }
}
