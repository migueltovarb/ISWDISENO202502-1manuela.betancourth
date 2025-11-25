package com.ucc.voluntariado.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;

@Document(collection = "postulantes")
public class Postulante {

    @Id
    private String id;

    @NotBlank(message = "El ID de usuario es obligatorio")
    private String userId;

    @NotBlank(message = "El ID de actividad es obligatorio")
    private String activityId;

    private String status; // pending, approved, rejected

    public Postulante() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}