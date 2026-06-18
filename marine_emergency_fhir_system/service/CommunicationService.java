package com.benedetta.marine_emergency_fhir_system.service;

import com.benedetta.marine_emergency_fhir_system.model.Communication;
import com.benedetta.marine_emergency_fhir_system.repository.CommunicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommunicationService {

    private final CommunicationRepository repository;

    public CommunicationService(
            CommunicationRepository repository
    ) {
        this.repository = repository;
    }

    public Communication save(Communication communication) {
        return repository.save(communication);
    }

    public List<Communication> getByPatientId(Long patientId) {
        return repository.findByPatientId(patientId);
    }
}
