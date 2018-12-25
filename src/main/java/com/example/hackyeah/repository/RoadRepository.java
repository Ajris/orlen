package com.example.hackyeah.repository;

import com.example.hackyeah.entity.Road;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoadRepository extends MongoRepository<Road, String> {
}
