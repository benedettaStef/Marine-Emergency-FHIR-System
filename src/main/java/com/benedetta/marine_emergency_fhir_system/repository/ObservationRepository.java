package com.benedetta.marine_emergency_fhir_system.repository;


import com.benedetta.marine_emergency_fhir_system.model.Observation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ObservationRepository extends JpaRepository<Observation, Long> {

    List<Observation> findByPatientId(Long patientId);
}