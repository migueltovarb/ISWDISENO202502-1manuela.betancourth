package com.voluntariado.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "activities")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(length = 1000)
    private String descripcion;

    private LocalDate fecha;

    private String ubicacion;

    private int cupos;

    @Column(name = "cupos_disponibles")
    private int cuposDisponibles;

    @Enumerated(EnumType.STRING)
    private ActivityType tipo = ActivityType.PRESENCIAL;

    private String categoria;

    private String duracion;

    @Enumerated(EnumType.STRING)
    private ActivityStatus estado = ActivityStatus.BORRADOR;

    @ManyToOne
    @JoinColumn(name = "coordinador_id")
    private User coordinador;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @ElementCollection
    @CollectionTable(name = "activity_postulantes", joinColumns = @JoinColumn(name = "activity_id"))
    @Column(name = "postulante_id")
    private List<Long> postulantes;

    @ElementCollection
    @CollectionTable(name = "activity_participantes", joinColumns = @JoinColumn(name = "activity_id"))
    @Column(name = "participante_id")
    private List<Long> participantes;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }

    public int getCupos() { return cupos; }
    public void setCupos(int cupos) { this.cupos = cupos; }

    public int getCuposDisponibles() { return cuposDisponibles; }
    public void setCuposDisponibles(int cuposDisponibles) { this.cuposDisponibles = cuposDisponibles; }

    public ActivityType getTipo() { return tipo; }
    public void setTipo(ActivityType tipo) { this.tipo = tipo; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public String getDuracion() { return duracion; }
    public void setDuracion(String duracion) { this.duracion = duracion; }

    public ActivityStatus getEstado() { return estado; }
    public void setEstado(ActivityStatus estado) { this.estado = estado; }

    public User getCoordinador() { return coordinador; }
    public void setCoordinador(User coordinador) { this.coordinador = coordinador; }

    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public List<Long> getPostulantes() { return postulantes; }
    public void setPostulantes(List<Long> postulantes) { this.postulantes = postulantes; }

    public List<Long> getParticipantes() { return participantes; }
    public void setParticipantes(List<Long> participantes) { this.participantes = participantes; }
}