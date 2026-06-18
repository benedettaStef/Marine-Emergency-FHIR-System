package com.benedetta.marine_emergency_fhir_system.service;


import com.benedetta.marine_emergency_fhir_system.model.Observation;
import com.benedetta.marine_emergency_fhir_system.repository.ObservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObservationService {

    private final ObservationRepository repository;

    public ObservationService(ObservationRepository repository) {
        this.repository = repository;
    }

    public Observation save(Observation obs) {
        return repository.save(obs);
    }

    public List<Observation> getByPatientId(Long patientId) {
        return repository.findByPatientId(patientId);
    }
}
