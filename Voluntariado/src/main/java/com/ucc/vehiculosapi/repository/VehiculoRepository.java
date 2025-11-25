package com.ucc.vehiculosapi.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ucc.vehiculosapi.model.Vehiculo;

@Repository
public interface VehiculoRepository extends MongoRepository<Vehiculo, String> {

    Optional<Vehiculo> findByPlacaIgnoreCase(String placa);

    boolean existsByPlacaIgnoreCase(String placa);
}
