package com.example.hackyeah.service.road;

import com.example.hackyeah.entity.Crossroad;
import com.example.hackyeah.entity.Road;
import com.example.hackyeah.entity.RoadWrapper;
import com.example.hackyeah.exception.RoadNotFoundException;
import com.example.hackyeah.repository.CrossroadRepository;
import com.example.hackyeah.repository.RoadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoadServiceImpl implements RoadService {

    private final RoadRepository roadRepository;

    private final CrossroadRepository crossroadRepository;

    @Autowired
    public RoadServiceImpl(RoadRepository roadRepository, CrossroadRepository crossroadRepository) {
        this.roadRepository = roadRepository;
        this.crossroadRepository = crossroadRepository;
    }

    @Override
    public List<Road> findAll() {
        return roadRepository.findAll();
    }

    @Override
    public Road save(Road road) {
        return roadRepository.save(road);
    }

    @Override
    public Road findById(String id) {
        return roadRepository.findById(id)
                .orElseThrow(RoadNotFoundException::new);
    }

    @Override
    public void deleteById(String id) {
        roadRepository.deleteById(id);
    }

    @Override
    public void updateCrossroads(RoadWrapper roadAdderWrapper) {
        Crossroad startingCrossroad = crossroadRepository.findById(roadAdderWrapper.getStartingCrossroad().getId()).get();
        Crossroad endingCrossroad = crossroadRepository.findById(roadAdderWrapper.getEndingCrossroad().getId()).get();

        startingCrossroad.getConnectedRoads()
                .add(roadAdderWrapper.getRoad());

        endingCrossroad.getConnectedRoads()
                .add(roadAdderWrapper.getRoad());

        crossroadRepository.save(startingCrossroad);
        crossroadRepository.save(endingCrossroad);
    }
}
