package com.benedetta.marine_emergency_fhir_system.repository;

import com.benedetta.marine_emergency_fhir_system.model.Communication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommunicationRepository
        extends JpaRepository<Communication, Long> {

    List<Communication> findByPatientId(Long patientId);
}
