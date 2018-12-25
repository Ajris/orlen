package com.example.hackyeah.service;

import com.example.hackyeah.entity.Crossroad;
import com.example.hackyeah.repository.CrossroadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrossroadServiceImpl implements CrossroadService {

    private final CrossroadRepository crossroadRepository;

    @Autowired
    public CrossroadServiceImpl(CrossroadRepository crossroadRepository) {
        this.crossroadRepository = crossroadRepository;
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
