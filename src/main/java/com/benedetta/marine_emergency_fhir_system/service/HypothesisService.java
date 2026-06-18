package com.benedetta.marine_emergency_fhir_system.service;


import com.benedetta.marine_emergency_fhir_system.model.Hypothesis;
import com.benedetta.marine_emergency_fhir_system.repository.HypothesisRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HypothesisService {

    private final HypothesisRepository repository;

    public HypothesisService(HypothesisRepository repository) {
        this.repository = repository;
    }

    public Hypothesis save(Hypothesis hypothesis) {
        return repository.save(hypothesis);
    }

    public List<Hypothesis> getByPatientId(Long patientId) {
        return repository.findByPatientId(patientId);
    }
}