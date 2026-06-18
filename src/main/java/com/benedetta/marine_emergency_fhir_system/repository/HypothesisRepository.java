package com.benedetta.marine_emergency_fhir_system.repository;

import com.benedetta.marine_emergency_fhir_system.model.Hypothesis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HypothesisRepository extends JpaRepository<Hypothesis, Long> {

    List<Hypothesis> findByPatientId(Long patientId);

}
