package com.benedetta.marine_emergency_fhir_system.controller;

import com.benedetta.marine_emergency_fhir_system.dto.PatientDTO;
import com.benedetta.marine_emergency_fhir_system.exception.PatientNotFoundException;
import com.benedetta.marine_emergency_fhir_system.model.Patient;
import com.benedetta.marine_emergency_fhir_system.service.PatientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final PatientService service;

    public PatientController(PatientService service) {
        this.service = service;
    }

    @PostMapping
    public PatientDTO create(@RequestBody PatientDTO dto) {

        Patient saved = service.createFromDto(dto);

        return new PatientDTO(
                saved.getId(),
                saved.getName(),
                saved.getBirthDate()
        );
    }

    @GetMapping("/{id}")
    public PatientDTO getById(@PathVariable Long id) {

        Patient patient = service.getById(id)
                .orElseThrow(() -> new PatientNotFoundException(id));

        return new PatientDTO(
                patient.getId(),
                patient.getName(),
                patient.getBirthDate()
        );
    }

    @GetMapping
    public List<PatientDTO> getAll() {
        return service.getAll()
                .stream()
                .map(p -> new PatientDTO(
                        p.getId(),
                        p.getName(),
                        p.getBirthDate()
                ))
                .toList();
    }
}