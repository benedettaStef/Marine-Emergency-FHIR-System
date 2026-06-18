package com.benedetta.marine_emergency_fhir_system.service;

import com.benedetta.marine_emergency_fhir_system.dto.PatientDTO;
import com.benedetta.marine_emergency_fhir_system.model.Patient;
import com.benedetta.marine_emergency_fhir_system.repository.PatientRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    private final PatientRepository repository;

    public PatientService(PatientRepository repository) {
        this.repository = repository;
    }

    public Patient createFromDto(PatientDTO dto) {

        String name = dto.getName().get(0).getText();

        Patient patient = new Patient(
                name,
                dto.getBirthDate()
        );

        return repository.save(patient);
    }
    public Patient save(Patient patient) {
        return repository.save(patient);
    }

    public List<Patient> getAll() {
        return repository.findAll();
    }

    public Optional<Patient> getById(Long id) {
        return repository.findById(id);
    }
}