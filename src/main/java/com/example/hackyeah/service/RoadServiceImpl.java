package com.example.hackyeah.service;

import com.example.hackyeah.entity.Road;
import com.example.hackyeah.entity.RoadAdderWrapper;
import com.example.hackyeah.repository.CrossroadRepository;
import com.example.hackyeah.repository.RoadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoadServiceImpl implements RoadService {

    @Autowired
    private RoadRepository roadRepository;

    @Autowired
    private CrossroadRepository crossroadRepository;

    @Override
    public List<Road> findAll() {
        return roadRepository.findAll();
    }

    @Override
    public Road save(Road crossroad) {
        return roadRepository.save(crossroad);
    }

    @Override
    public Road findById(String id) {
        return roadRepository.findById(id)
                .orElseThrow(NullPointerException::new);
    }

    @Override
    public void deleteById(String id) {
        roadRepository.deleteById(id);
    }

    @Override
    public void updateCrossroads(RoadAdderWrapper roadAdderWrapper) {
        roadAdderWrapper.getC1().getConnectedRoads().add(roadAdderWrapper.getR1());
        roadAdderWrapper.getC2().getConnectedRoads().add(roadAdderWrapper.getR1());
        crossroadRepository.save(roadAdderWrapper.getC1());
        crossroadRepository.save(roadAdderWrapper.getC2());
    }
}
