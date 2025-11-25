package com.ucc.voluntariado.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ucc.voluntariado.model.Certificate;

@Repository
public interface CertificateRepository extends MongoRepository<Certificate, String> {

}