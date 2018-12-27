package com.example.hackyeah.service.path;

import com.example.hackyeah.entity.Crossroad;
import com.example.hackyeah.entity.PathFinderWrapper;

import java.util.List;

public interface PathService {
    List<Crossroad> findPath(PathFinderWrapper pathFinderWrapper);
}
