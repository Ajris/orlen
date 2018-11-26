package com.example.hackyeah.repository;

import com.example.hackyeah.entity.Crossroad;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrossroadRepository extends MongoRepository<Crossroad, String> {

}
