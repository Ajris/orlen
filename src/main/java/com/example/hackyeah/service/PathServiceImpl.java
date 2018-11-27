package com.example.hackyeah.service;

import com.example.hackyeah.entity.Crossroad;
import com.example.hackyeah.entity.PathFinderWrapper;
import com.example.hackyeah.repository.CrossroadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PathServiceImpl implements PathService {

    @Autowired
    private CrossroadRepository crossroadRepository;

    @Override
    public List<Crossroad> getAll(PathFinderWrapper pathFinderWrapper) {
        return crossroadRepository.findAll();
    }
}
