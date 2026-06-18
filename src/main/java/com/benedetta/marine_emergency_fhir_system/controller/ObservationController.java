package com.benedetta.marine_emergency_fhir_system.controller;


import com.benedetta.marine_emergency_fhir_system.dto.ObservationCreateDTO;
import com.benedetta.marine_emergency_fhir_system.dto.ObservationDTO;
import com.benedetta.marine_emergency_fhir_system.exception.PatientNotFoundException;
import com.benedetta.marine_emergency_fhir_system.model.Observation;
import com.benedetta.marine_emergency_fhir_system.model.Patient;
import com.benedetta.marine_emergency_fhir_system.service.ObservationService;
import com.benedetta.marine_emergency_fhir_system.service.PatientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/observations")
public class ObservationController {

    private final ObservationService service;
    private final PatientService patientService;

    public ObservationController(ObservationService service, PatientService patientService) {
        this.service = service;
        this.patientService = patientService;
    }

    @PostMapping
    public ObservationDTO create(@RequestBody ObservationCreateDTO dto) {

        Observation obs = new Observation();
        obs.setCode(dto.getCode());
        obs.setDisplay(dto.getDisplay());
        obs.setTimestamp(dto.getTimestamp());

        Patient patient = patientService.getById(dto.getPatientId())
                .orElseThrow(() -> new PatientNotFoundException(dto.getPatientId()));

        obs.setPatient(patient);

        Observation saved = service.save(obs);

        return new ObservationDTO(
                saved.getPatient().getId(),
                saved.getCode(),
                saved.getDisplay(),
                saved.getTimestamp()
        );
    }

    @GetMapping("/patient/{patientId}")
    public List<ObservationDTO> getByPatient(@PathVariable Long patientId) {
        return service.getByPatientId(patientId)
                .stream()
                .map(obs -> new ObservationDTO(
                        obs.getPatient().getId(),
                        obs.getCode(),
                        obs.getDisplay(),
                        obs.getTimestamp()
                ))
                .toList();
    }
}