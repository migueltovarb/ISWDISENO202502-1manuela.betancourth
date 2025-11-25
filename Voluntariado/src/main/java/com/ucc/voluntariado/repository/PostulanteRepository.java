package com.ucc.voluntariado.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ucc.voluntariado.model.Postulante;

@Repository
public interface PostulanteRepository extends MongoRepository<Postulante, String> {

}