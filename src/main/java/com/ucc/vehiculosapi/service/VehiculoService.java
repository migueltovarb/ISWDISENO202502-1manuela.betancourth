package com.ucc.vehiculosapi.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ucc.vehiculosapi.model.Vehiculo;
import com.ucc.vehiculosapi.repository.VehiculoRepository;


@Service
public class VehiculoService {

    private final VehiculoRepository vehiculoRepository;

    public VehiculoService(VehiculoRepository vehiculoRepository) {
        this.vehiculoRepository = vehiculoRepository;
    }

    public List<Vehiculo> listarTodos() {
        return vehiculoRepository.findAll();
    }

    public Vehiculo buscarPorId(String id) {
        return vehiculoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehículo no encontrado con id: " + id));
    }

    public Vehiculo buscarPorPlaca(String placa) {
        return vehiculoRepository.findByPlacaIgnoreCase(placa)
                .orElseThrow(() -> new RuntimeException("Vehículo no encontrado con placa: " + placa));
    }

    public Vehiculo crear(Vehiculo vehiculo) {
        if (vehiculoRepository.existsByPlacaIgnoreCase(vehiculo.getPlaca())) {
            throw new RuntimeException("Ya existe un vehículo con la placa: " + vehiculo.getPlaca());
        }
        vehiculo.setId(null);
        vehiculo.setFechaRegistro(LocalDateTime.now());
        return vehiculoRepository.save(vehiculo);
    }

    public Vehiculo actualizar(String id, Vehiculo vehiculoActualizado) {
        Vehiculo existente = buscarPorId(id);

        if (StringUtils.hasText(vehiculoActualizado.getPlaca())
                && !vehiculoActualizado.getPlaca().equalsIgnoreCase(existente.getPlaca())
                && vehiculoRepository.existsByPlacaIgnoreCase(vehiculoActualizado.getPlaca())) {
            throw new RuntimeException("Ya existe un vehículo con la placa: " + vehiculoActualizado.getPlaca());
        }

        existente.setPlaca(vehiculoActualizado.getPlaca());
        existente.setMarca(vehiculoActualizado.getMarca());
        existente.setLinea(vehiculoActualizado.getLinea());
        existente.setModelo(vehiculoActualizado.getModelo());
        existente.setPrecio(vehiculoActualizado.getPrecio());
        existente.setColor(vehiculoActualizado.getColor());

        return vehiculoRepository.save(existente);
    }

    public void eliminar(String id) {
        Vehiculo existente = buscarPorId(id);
        vehiculoRepository.delete(existente);
    }
}
