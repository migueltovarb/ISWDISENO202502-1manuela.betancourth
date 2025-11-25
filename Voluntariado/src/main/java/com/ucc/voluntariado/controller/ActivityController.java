package com.ucc.voluntariado.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.ucc.voluntariado.model.Activity;
import com.ucc.voluntariado.service.ActivityService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/activities")
@CrossOrigin(origins = "*")
public class ActivityController {

    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @GetMapping
    public List<Activity> listarTodos() {
        return activityService.listarTodos();
    }

    @GetMapping("/{id}")
    public Activity obtenerPorId(@PathVariable String id) {
        return activityService.buscarPorId(id);
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Activity activity,
                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return manejarErroresValidacion(bindingResult);
        }
        Activity creado = activityService.crear(activity);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable String id,
                                        @Valid @RequestBody Activity activity,
                                        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return manejarErroresValidacion(bindingResult);
        }
        Activity actualizado = activityService.actualizar(id, activity);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        activityService.eliminar(id);
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