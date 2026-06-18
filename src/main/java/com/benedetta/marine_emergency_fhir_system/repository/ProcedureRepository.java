package com.benedetta.marine_emergency_fhir_system.repository;

import com.benedetta.marine_emergency_fhir_system.model.Procedure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProcedureRepository
        extends JpaRepository<Procedure, Long> {

    List<Procedure> findByPatientId(Long patientId);
}