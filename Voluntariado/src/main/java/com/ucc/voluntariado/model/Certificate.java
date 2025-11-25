package com.ucc.voluntariado.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Document(collection = "certificates")
public class Certificate {

    @Id
    private String id;

    @NotBlank(message = "El ID de usuario es obligatorio")
    private String userId;

    @NotBlank(message = "El ID de actividad es obligatorio")
    private String activityId;

    @NotNull(message = "La fecha de emisión es obligatoria")
    private LocalDate issuedDate;

    public Certificate() {
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

    public LocalDate getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(LocalDate issuedDate) {
        this.issuedDate = issuedDate;
    }
}