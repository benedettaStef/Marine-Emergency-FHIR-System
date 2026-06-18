package com.benedetta.marine_emergency_fhir_system.repository;

import com.benedetta.marine_emergency_fhir_system.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}