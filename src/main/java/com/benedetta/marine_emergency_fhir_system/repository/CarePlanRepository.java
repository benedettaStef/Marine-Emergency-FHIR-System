package com.benedetta.marine_emergency_fhir_system.repository;

import com.benedetta.marine_emergency_fhir_system.model.CarePlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarePlanRepository extends JpaRepository<CarePlan, Long> {

    List<CarePlan> findByPatientId(Long patientId);

}
