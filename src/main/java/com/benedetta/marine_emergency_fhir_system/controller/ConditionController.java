package com.benedetta.marine_emergency_fhir_system.controller;

import com.benedetta.marine_emergency_fhir_system.dto.ConditionCreateDTO;
import com.benedetta.marine_emergency_fhir_system.dto.ConditionDTO;
import com.benedetta.marine_emergency_fhir_system.exception.PatientNotFoundException;
import com.benedetta.marine_emergency_fhir_system.model.Condition;
import com.benedetta.marine_emergency_fhir_system.model.Patient;
import com.benedetta.marine_emergency_fhir_system.service.ConditionService;
import com.benedetta.marine_emergency_fhir_system.service.PatientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conditions")
public class ConditionController {

    private final ConditionService service;
    private final PatientService patientService;

    public ConditionController(
            ConditionService service,
            PatientService patientService
    ) {
        this.service = service;
        this.patientService = patientService;
    }

    @PostMapping
    public ConditionDTO create(
            @RequestBody ConditionCreateDTO dto
    ) {

        Patient patient = patientService.getById(dto.getPatientId())
                .orElseThrow(() ->
                        new PatientNotFoundException(dto.getPatientId()));

        Condition condition = new Condition();

        condition.setCode(dto.getCode());
        condition.setDisplay(dto.getDisplay());
        condition.setClinicalStatus(dto.getClinicalStatus());
        condition.setVerificationStatus(dto.getVerificationStatus());
        condition.setRecordedDate(dto.getRecordedDate());
        condition.setPatient(patient);

        Condition saved = service.save(condition);

        return new ConditionDTO(
                saved.getPatient().getId(),
                saved.getCode(),
                saved.getDisplay(),
                saved.getClinicalStatus(),
                saved.getVerificationStatus(),
                saved.getRecordedDate()
        );
    }

    @GetMapping("/patient/{patientId}")
    public List<ConditionDTO> getByPatient(
            @PathVariable Long patientId
    ) {

        return service.getByPatientId(patientId)
                .stream()
                .map(c -> new ConditionDTO(
                        c.getPatient().getId(),
                        c.getCode(),
                        c.getDisplay(),
                        c.getClinicalStatus(),
                        c.getVerificationStatus(),
                        c.getRecordedDate()
                ))
                .toList();
    }
}
