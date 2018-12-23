package com.example.hackyeah.service;

import com.example.hackyeah.entity.Crossroad;
import com.example.hackyeah.entity.Road;
import com.example.hackyeah.entity.RoadAdderWrapper;
import com.example.hackyeah.repository.CrossroadRepository;
import com.example.hackyeah.repository.RoadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        Crossroad crossroad1 = crossroadRepository.findById(roadAdderWrapper.getC1().getId()).get();
        Crossroad crossroad2 = crossroadRepository.findById(roadAdderWrapper.getC2().getId()).get();

        Optional.ofNullable(crossroad1.getConnectedRoads())
                .ifPresentOrElse(roads -> roads.add(roadAdderWrapper.getR1()),
                        () -> {
                            List<Road> r = new ArrayList<>();
                            r.add(roadAdderWrapper.getR1());
                            crossroad1.setConnectedRoads(r);
                        });


        Optional.ofNullable(crossroad2.getConnectedRoads())
                .ifPresentOrElse(roads -> roads.add(roadAdderWrapper.getR1()),
                        () -> {
                            List<Road> r = new ArrayList<>();
                            r.add(roadAdderWrapper.getR1());
                            crossroad2.setConnectedRoads(r);
                        });

        crossroadRepository.save(crossroad1);
        crossroadRepository.save(crossroad2);
    }
}
