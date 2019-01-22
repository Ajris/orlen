package com.example.hackyeah.service.crossroad;

import com.example.hackyeah.entity.Crossroad;

import java.util.List;

public interface CrossroadService {
    List<Crossroad> findAll();

    Crossroad addCrossroad(Crossroad crossroad);

    void changePlace(Crossroad crossroad);

    Crossroad findById(String id);

    void deleteById(String id);
}
