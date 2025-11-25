package com.ucc.voluntariado.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ucc.voluntariado.model.User;
import com.ucc.voluntariado.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> listarTodos() {
        return userRepository.findAll();
    }

    public User buscarPorId(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
    }

    public User crear(User user) {
        user.setId(null);
        return userRepository.save(user);
    }

    public User actualizar(String id, User userActualizado) {
        User existente = buscarPorId(id);

        existente.setName(userActualizado.getName());
        existente.setEmail(userActualizado.getEmail());
        existente.setPassword(userActualizado.getPassword());
        existente.setRole(userActualizado.getRole());

        return userRepository.save(existente);
    }

    public void eliminar(String id) {
        User existente = buscarPorId(id);
        userRepository.delete(existente);
    }
}