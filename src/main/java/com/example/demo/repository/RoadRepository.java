package com.example.demo.repository;

import com.example.demo.entity.Road;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoadRepository extends MongoRepository<Road, String> {

}
