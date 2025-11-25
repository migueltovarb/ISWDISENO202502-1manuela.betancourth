package com.ucc.voluntariado.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ucc.voluntariado.model.Postulante;
import com.ucc.voluntariado.repository.PostulanteRepository;

@Service
public class PostulanteService {

    private final PostulanteRepository postulanteRepository;

    public PostulanteService(PostulanteRepository postulanteRepository) {
        this.postulanteRepository = postulanteRepository;
    }

    public List<Postulante> listarTodos() {
        return postulanteRepository.findAll();
    }

    public Postulante buscarPorId(String id) {
        return postulanteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Postulante no encontrado con id: " + id));
    }

    public Postulante crear(Postulante postulante) {
        postulante.setId(null);
        return postulanteRepository.save(postulante);
    }

    public Postulante actualizar(String id, Postulante postulanteActualizado) {
        Postulante existente = buscarPorId(id);

        existente.setUserId(postulanteActualizado.getUserId());
        existente.setActivityId(postulanteActualizado.getActivityId());
        existente.setStatus(postulanteActualizado.getStatus());

        return postulanteRepository.save(existente);
    }

    public void eliminar(String id) {
        Postulante existente = buscarPorId(id);
        postulanteRepository.delete(existente);
    }
}