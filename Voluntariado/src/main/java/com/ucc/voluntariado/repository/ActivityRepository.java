package com.ucc.voluntariado.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ucc.voluntariado.model.Activity;

@Repository
public interface ActivityRepository extends MongoRepository<Activity, String> {

}