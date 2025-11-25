package com.ucc.voluntariado.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ucc.voluntariado.model.Activity;
import com.ucc.voluntariado.repository.ActivityRepository;

@Service
public class ActivityService {

    private final ActivityRepository activityRepository;

    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public List<Activity> listarTodos() {
        return activityRepository.findAll();
    }

    public Activity buscarPorId(String id) {
        return activityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Actividad no encontrada con id: " + id));
    }

    public Activity crear(Activity activity) {
        activity.setId(null);
        return activityRepository.save(activity);
    }

    public Activity actualizar(String id, Activity activityActualizado) {
        Activity existente = buscarPorId(id);

        existente.setTitle(activityActualizado.getTitle());
        existente.setDescription(activityActualizado.getDescription());
        existente.setDate(activityActualizado.getDate());
        existente.setLocation(activityActualizado.getLocation());

        return activityRepository.save(existente);
    }

    public void eliminar(String id) {
        Activity existente = buscarPorId(id);
        activityRepository.delete(existente);
    }
}