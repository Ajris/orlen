package com.example.hackyeah.repository;

import com.example.hackyeah.entity.Road;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoadRepository extends MongoRepository<Road, String> {
}
