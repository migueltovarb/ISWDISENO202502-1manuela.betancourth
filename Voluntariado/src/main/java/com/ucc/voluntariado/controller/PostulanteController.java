package com.ucc.voluntariado.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.ucc.voluntariado.model.Postulante;
import com.ucc.voluntariado.service.PostulanteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/postulantes")
@CrossOrigin(origins = "*")
public class PostulanteController {

    private final PostulanteService postulanteService;

    public PostulanteController(PostulanteService postulanteService) {
        this.postulanteService = postulanteService;
    }

    @GetMapping
    public List<Postulante> listarTodos() {
        return postulanteService.listarTodos();
    }

    @GetMapping("/{id}")
    public Postulante obtenerPorId(@PathVariable String id) {
        return postulanteService.buscarPorId(id);
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Postulante postulante,
                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return manejarErroresValidacion(bindingResult);
        }
        Postulante creado = postulanteService.crear(postulante);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable String id,
                                        @Valid @RequestBody Postulante postulante,
                                        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return manejarErroresValidacion(bindingResult);
        }
        Postulante actualizado = postulanteService.actualizar(id, postulante);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        postulanteService.eliminar(id);
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