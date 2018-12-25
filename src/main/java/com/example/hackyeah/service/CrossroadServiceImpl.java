package com.example.hackyeah.service;

import com.example.hackyeah.entity.Crossroad;
import com.example.hackyeah.entity.Road;
import com.example.hackyeah.repository.CrossroadRepository;
import com.example.hackyeah.repository.RoadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
        Crossroad currentCrossroad = crossroadRepository.findById(crossroad.getId())
                .orElseThrow(NullPointerException::new);

        List<Road> connectedRoads = roadRepository.findAll()
                .stream()
                .filter(road -> Optional.ofNullable(currentCrossroad.getConnectedRoads())
                        .orElse(new ArrayList<>()).contains(road))
                .collect(Collectors.toList());

        connectedRoads.forEach(road -> {
            if (road.getEnd().equals(currentCrossroad)) {
                road.setEnd(crossroad);
            } else if (road.getStart().equals(currentCrossroad)) {
                road.setStart(crossroad);
            }
        });

        Crossroad crossroadToSave = Crossroad.builder()
                .id(crossroad.getId())
                .latitude(crossroad.getLatitude())
                .longitude(crossroad.getLongitude())
                .connectedRoads(connectedRoads)
                .build();

        roadRepository.saveAll(connectedRoads);

        return crossroadRepository.save(crossroadToSave);
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
    public Crossroad save(Crossroad crossroad) {
        return crossroadRepository.save(crossroad);
    }

    @Override
    public Crossroad findById(String id) {
        return crossroadRepository.findById(id)
                .orElseThrow(NullPointerException::new);
    }
}
