package com.voluntariado.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String contraseña;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role rol = Role.VOLUNTARIO;

    private String ciudad;

    @ElementCollection
    @CollectionTable(name = "user_intereses", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "interes")
    private List<String> intereses;

    private String telefono;

    private String tipoDocumento;

    private String numeroDocumento;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private UserStatus estado = UserStatus.ACTIVO;

    @Column(name = "actividades_realizadas")
    private int actividadesRealizadas = 0;

    @Column(name = "horas_acumuladas")
    private int horasAcumuladas = 0;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getContraseña() { return contraseña; }
    public void setContraseña(String contraseña) { this.contraseña = contraseña; }

    public Role getRol() { return rol; }
    public void setRol(Role rol) { this.rol = rol; }

    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }

    public List<String> getIntereses() { return intereses; }
    public void setIntereses(List<String> intereses) { this.intereses = intereses; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getTipoDocumento() { return tipoDocumento; }
    public void setTipoDocumento(String tipoDocumento) { this.tipoDocumento = tipoDocumento; }

    public String getNumeroDocumento() { return numeroDocumento; }
    public void setNumeroDocumento(String numeroDocumento) { this.numeroDocumento = numeroDocumento; }

    public LocalDateTime getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDateTime fechaRegistro) { this.fechaRegistro = fechaRegistro; }

    public UserStatus getEstado() { return estado; }
    public void setEstado(UserStatus estado) { this.estado = estado; }

    public int getActividadesRealizadas() { return actividadesRealizadas; }
    public void setActividadesRealizadas(int actividadesRealizadas) { this.actividadesRealizadas = actividadesRealizadas; }

    public int getHorasAcumuladas() { return horasAcumuladas; }
    public void setHorasAcumuladas(int horasAcumuladas) { this.horasAcumuladas = horasAcumuladas; }
}