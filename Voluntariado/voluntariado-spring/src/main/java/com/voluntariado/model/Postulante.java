package com.voluntariado.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "postulantes")
public class Postulante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private User usuario;

    @ManyToOne
    @JoinColumn(name = "actividad_id", nullable = false)
    private Activity actividad;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus estado = ApplicationStatus.PENDIENTE;

    @Column(name = "fecha_postulacion")
    private LocalDateTime fechaPostulacion = LocalDateTime.now();

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUsuario() { return usuario; }
    public void setUsuario(User usuario) { this.usuario = usuario; }

    public Activity getActividad() { return actividad; }
    public void setActividad(Activity actividad) { this.actividad = actividad; }

    public ApplicationStatus getEstado() { return estado; }
    public void setEstado(ApplicationStatus estado) { this.estado = estado; }

    public LocalDateTime getFechaPostulacion() { return fechaPostulacion; }
    public void setFechaPostulacion(LocalDateTime fechaPostulacion) { this.fechaPostulacion = fechaPostulacion; }
}