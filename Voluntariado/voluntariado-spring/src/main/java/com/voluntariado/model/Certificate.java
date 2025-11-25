package com.voluntariado.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "certificates")
public class Certificate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private User usuario;

    @ManyToOne
    @JoinColumn(name = "actividad_id", nullable = false)
    private Activity actividad;

    private String titulo;

    private int horas;

    @Column(name = "fecha_emision")
    private LocalDate fechaEmision;

    @Enumerated(EnumType.STRING)
    private CertificateStatus estado = CertificateStatus.DISPONIBLE;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUsuario() { return usuario; }
    public void setUsuario(User usuario) { this.usuario = usuario; }

    public Activity getActividad() { return actividad; }
    public void setActividad(Activity actividad) { this.actividad = actividad; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public int getHoras() { return horas; }
    public void setHoras(int horas) { this.horas = horas; }

    public LocalDate getFechaEmision() { return fechaEmision; }
    public void setFechaEmision(LocalDate fechaEmision) { this.fechaEmision = fechaEmision; }

    public CertificateStatus getEstado() { return estado; }
    public void setEstado(CertificateStatus estado) { this.estado = estado; }
}