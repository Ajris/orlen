package com.example.hackyeah.service.crossroad;

import com.example.hackyeah.entity.Crossroad;
import com.example.hackyeah.entity.Road;
import com.example.hackyeah.exception.CrossroadNotFoundException;
import com.example.hackyeah.repository.CrossroadRepository;
import com.example.hackyeah.repository.RoadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Service
public class CrossroadServiceImpl implements CrossroadService {

    private final CrossroadRepository crossroadRepository;

    private final RoadRepository roadRepository;

    @Autowired
    public CrossroadServiceImpl(CrossroadRepository crossroadRepository, RoadRepository roadRepository) {
        this.crossroadRepository = crossroadRepository;
        this.roadRepository = roadRepository;
    }

    @Override
    public Crossroad changePlace(Crossroad crossroad) {
        Crossroad currentCrossroad = findById(crossroad.getId());

        List<Road> connectedRoads = findConnectedRoads(currentCrossroad);

        connectedRoads.forEach(updateRoadPosition(crossroad, currentCrossroad));

        crossroad.setConnectedRoads(connectedRoads);

        return crossroadRepository.save(crossroad);
    }

    private Consumer<Road> updateRoadPosition(Crossroad crossroad, Crossroad currentCrossroad) {
        return road -> {
            if (road.getEnd().equals(currentCrossroad)) {
                road.setEnd(crossroad);
            } else if (road.getStart().equals(currentCrossroad)) {
                road.setStart(crossroad);
            }
        };
    }

    private List<Road> findConnectedRoads(Crossroad currentCrossroad) {
        return roadRepository.findAll()
                .stream()
                .filter(road -> currentCrossroad.getConnectedRoads().contains(road))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(String id) {
        crossroadRepository.deleteById(id);
    }

    @Override
    public List<Crossroad> findAll() {
        return crossroadRepository.findAll();
    }

    @Override
    public Crossroad addCrossroad(Crossroad crossroad) {
        crossroad.setConnectedRoads(new ArrayList<>());
        return crossroadRepository.save(crossroad);
    }

    @Override
    public Crossroad findById(String id) {
        return crossroadRepository.findById(id)
                .orElseThrow(CrossroadNotFoundException::new);
    }
}
