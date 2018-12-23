package com.example.hackyeah.service;

import com.example.hackyeah.entity.Crossroad;
import com.example.hackyeah.entity.PathFinderWrapper;

import java.util.List;

public interface PathService {
    List<Crossroad> findPath(PathFinderWrapper pathFinderWrapper);
}
