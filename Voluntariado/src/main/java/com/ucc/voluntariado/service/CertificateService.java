package com.ucc.voluntariado.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ucc.voluntariado.model.Certificate;
import com.ucc.voluntariado.repository.CertificateRepository;

@Service
public class CertificateService {

    private final CertificateRepository certificateRepository;

    public CertificateService(CertificateRepository certificateRepository) {
        this.certificateRepository = certificateRepository;
    }

    public List<Certificate> listarTodos() {
        return certificateRepository.findAll();
    }

    public Certificate buscarPorId(String id) {
        return certificateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Certificado no encontrado con id: " + id));
    }

    public Certificate crear(Certificate certificate) {
        certificate.setId(null);
        return certificateRepository.save(certificate);
    }

    public Certificate actualizar(String id, Certificate certificateActualizado) {
        Certificate existente = buscarPorId(id);

        existente.setUserId(certificateActualizado.getUserId());
        existente.setActivityId(certificateActualizado.getActivityId());
        existente.setIssuedDate(certificateActualizado.getIssuedDate());

        return certificateRepository.save(existente);
    }

    public void eliminar(String id) {
        Certificate existente = buscarPorId(id);
        certificateRepository.delete(existente);
    }
}