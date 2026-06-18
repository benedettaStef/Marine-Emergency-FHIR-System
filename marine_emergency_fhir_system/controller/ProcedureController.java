package com.benedetta.marine_emergency_fhir_system.controller;

import com.benedetta.marine_emergency_fhir_system.dto.ProcedureCreateDTO;
import com.benedetta.marine_emergency_fhir_system.dto.ProcedureDTO;
import com.benedetta.marine_emergency_fhir_system.exception.PatientNotFoundException;
import com.benedetta.marine_emergency_fhir_system.model.Patient;
import com.benedetta.marine_emergency_fhir_system.model.Procedure;
import com.benedetta.marine_emergency_fhir_system.service.PatientService;
import com.benedetta.marine_emergency_fhir_system.service.ProcedureService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/procedures")
public class ProcedureController {

    private final ProcedureService service;
    private final PatientService patientService;

    public ProcedureController(
            ProcedureService service,
            PatientService patientService
    ) {
        this.service = service;
        this.patientService = patientService;
    }

    @PostMapping
    public ProcedureDTO create(@RequestBody ProcedureCreateDTO dto) {

        Patient patient = patientService.getById(dto.getPatientId())
                .orElseThrow(() ->
                        new PatientNotFoundException(dto.getPatientId()));

        Procedure procedure = new Procedure();

        procedure.setCode(dto.getCode());
        procedure.setDisplay(dto.getDisplay());
        procedure.setOutcome(dto.getOutcome());
        procedure.setPerformedDate(dto.getPerformedDate());
        procedure.setPatient(patient);

        Procedure saved = service.save(procedure);

        return new ProcedureDTO(
                saved.getPatient().getId(),
                saved.getCode(),
                saved.getDisplay(),
                saved.getOutcome(),
                saved.getPerformedDate()
        );
    }

    @GetMapping("/patient/{patientId}")
    public List<ProcedureDTO> getByPatient(
            @PathVariable Long patientId
    ) {
        return service.getByPatientId(patientId)
                .stream()
                .map(proc -> new ProcedureDTO(
                        proc.getPatient().getId(),
                        proc.getCode(),
                        proc.getDisplay(),
                        proc.getOutcome(),
                        proc.getPerformedDate()
                ))
                .toList();
    }
}