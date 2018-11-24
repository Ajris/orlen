package com.example.demo.repository;

import com.example.demo.entity.Crossroad;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrossroadRepository extends MongoRepository<Crossroad, String> {

}
