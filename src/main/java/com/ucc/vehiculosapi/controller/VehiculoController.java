package com.ucc.vehiculosapi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.ucc.vehiculosapi.model.Vehiculo;
import com.ucc.vehiculosapi.service.VehiculoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/vehiculos")
@CrossOrigin(origins = "*")
public class VehiculoController {

    private final VehiculoService vehiculoService;

    public VehiculoController(VehiculoService vehiculoService) {
        this.vehiculoService = vehiculoService;
    }

    @GetMapping
    public List<Vehiculo> listarTodos() {
        return vehiculoService.listarTodos();
    }

    @GetMapping("/{id}")
    public Vehiculo obtenerPorId(@PathVariable String id) {
        return vehiculoService.buscarPorId(id);
    }

    @GetMapping("/placa/{placa}")
    public Vehiculo obtenerPorPlaca(@PathVariable String placa) {
        return vehiculoService.buscarPorPlaca(placa);
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Vehiculo vehiculo,
                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return manejarErroresValidacion(bindingResult);
        }
        Vehiculo creado = vehiculoService.crear(vehiculo);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable String id,
                                        @Valid @RequestBody Vehiculo vehiculo,
                                        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return manejarErroresValidacion(bindingResult);
        }
        Vehiculo actualizado = vehiculoService.actualizar(id, vehiculo);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        vehiculoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    private ResponseEntity<Map<String, String>> manejarErroresValidacion(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(error ->
                errores.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errores);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> manejarRuntime(RuntimeException ex) {
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
    }
}
