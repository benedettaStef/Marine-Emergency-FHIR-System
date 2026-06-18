package com.benedetta.marine_emergency_fhir_system.service;

import com.benedetta.marine_emergency_fhir_system.model.Condition;
import com.benedetta.marine_emergency_fhir_system.repository.ConditionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConditionService {

    private final ConditionRepository repository;

    public ConditionService(ConditionRepository repository) {
        this.repository = repository;
    }

    public Condition save(Condition condition) {
        return repository.save(condition);
    }

    public List<Condition> getByPatientId(Long patientId) {
        return repository.findByPatientId(patientId);
    }
}
