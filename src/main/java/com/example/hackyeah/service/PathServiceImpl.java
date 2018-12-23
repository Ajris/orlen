package com.example.hackyeah.service;

import com.example.hackyeah.entity.Crossroad;
import com.example.hackyeah.entity.PathFinderWrapper;
import com.example.hackyeah.entity.Road;
import com.example.hackyeah.repository.CrossroadRepository;
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

    @Autowired
    private CrossroadRepository crossroadRepository;

    @Override
    public List<Crossroad> findPath(PathFinderWrapper pathFinderWrapper) {
        Crossroad start = crossroadRepository.findById(pathFinderWrapper.getStart().getId()).get();
        Crossroad end = crossroadRepository.findById(pathFinderWrapper.getEnd().getId()).get();

        List<Crossroad> solution = new ArrayList<>();
        Set<Crossroad> visited = new HashSet<>();
        Stack<Crossroad> stack = new Stack<>();
        Map<Crossroad, Crossroad> successorPredecessor = new HashMap<>();
        successorPredecessor.put(start, Crossroad.builder().id("-1").build());

        visited.add(start);
        stack.add(start);

        while(!stack.empty()){
            Crossroad current = stack.pop();

            if(current.equals(end)){
                while(!current.getId().equals("-1")){
                    solution.add(current);
                    current = successorPredecessor.get(current);
                }
                return solution;
            }

            List<Crossroad> crossroads = crossroadRepository.findById(current.getId())
                    .get()
                    .getConnectedRoads()
                    .stream()
                    .map(a -> List.of(a.getEnd(), a.getStart()))
                    .flatMap(List::stream)
                    .collect(Collectors.toList());

            for(Crossroad crossroad : crossroads){
                if(!visited.contains(crossroad)){
                    successorPredecessor.put(crossroad, current);
                    stack.push(crossroad);
                    visited.add(crossroad);
                }
            }
        }

        return solution;
    }
}
