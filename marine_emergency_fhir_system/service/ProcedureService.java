package com.benedetta.marine_emergency_fhir_system.service;

import com.benedetta.marine_emergency_fhir_system.model.Procedure;
import com.benedetta.marine_emergency_fhir_system.repository.ProcedureRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcedureService {

    private final ProcedureRepository repository;

    public ProcedureService(ProcedureRepository repository) {
        this.repository = repository;
    }

    public Procedure save(Procedure procedure) {
        return repository.save(procedure);
    }

    public List<Procedure> getByPatientId(Long patientId) {
        return repository.findByPatientId(patientId);
    }
}
