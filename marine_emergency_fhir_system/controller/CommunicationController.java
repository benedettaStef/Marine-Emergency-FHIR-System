package com.benedetta.marine_emergency_fhir_system.controller;

import com.benedetta.marine_emergency_fhir_system.dto.CommunicationCreateDTO;
import com.benedetta.marine_emergency_fhir_system.dto.CommunicationDTO;
import com.benedetta.marine_emergency_fhir_system.exception.PatientNotFoundException;
import com.benedetta.marine_emergency_fhir_system.model.Communication;
import com.benedetta.marine_emergency_fhir_system.model.Patient;
import com.benedetta.marine_emergency_fhir_system.service.CommunicationService;
import com.benedetta.marine_emergency_fhir_system.service.PatientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/communications")
public class CommunicationController {

    private final CommunicationService service;
    private final PatientService patientService;

    public CommunicationController(
            CommunicationService service,
            PatientService patientService
    ) {
        this.service = service;
        this.patientService = patientService;
    }

    @PostMapping
    public CommunicationDTO create(
            @RequestBody CommunicationCreateDTO dto
    ) {

        Patient patient = patientService.getById(dto.getPatientId())
                .orElseThrow(() ->
                        new PatientNotFoundException(dto.getPatientId()));

        Communication communication = new Communication();

        communication.setCode(dto.getCode());
        communication.setMessage(dto.getMessage());
        communication.setSender(dto.getSender());
        communication.setTimestamp(dto.getTimestamp());
        communication.setPatient(patient);

        Communication saved = service.save(communication);

        return new CommunicationDTO(
                saved.getPatient().getId(),
                saved.getCode(),
                saved.getMessage(),
                saved.getSender(),
                saved.getTimestamp()
        );
    }

    @GetMapping("/patient/{patientId}")
    public List<CommunicationDTO> getByPatient(
            @PathVariable Long patientId
    ) {

        return service.getByPatientId(patientId)
                .stream()
                .map(comm -> new CommunicationDTO(
                        comm.getPatient().getId(),
                        comm.getCode(),
                        comm.getMessage(),
                        comm.getSender(),
                        comm.getTimestamp()
                ))
                .toList();
    }
}
