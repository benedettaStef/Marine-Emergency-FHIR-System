package com.benedetta.marine_emergency_fhir_system.controller;

import com.benedetta.marine_emergency_fhir_system.dto.HypothesisCreateDTO;
import com.benedetta.marine_emergency_fhir_system.dto.HypothesisDTO;
import com.benedetta.marine_emergency_fhir_system.exception.PatientNotFoundException;
import com.benedetta.marine_emergency_fhir_system.model.Hypothesis;
import com.benedetta.marine_emergency_fhir_system.model.Patient;
import com.benedetta.marine_emergency_fhir_system.service.HypothesisService;
import com.benedetta.marine_emergency_fhir_system.service.PatientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hypotheses")
public class HypothesisController {

    private final HypothesisService service;
    private final PatientService patientService;

    public HypothesisController(
            HypothesisService service,
            PatientService patientService
    ) {
        this.service = service;
        this.patientService = patientService;
    }

    @PostMapping
    public HypothesisDTO create(
            @RequestBody HypothesisCreateDTO dto
    ) {

        Patient patient = patientService.getById(dto.getPatientId())
                .orElseThrow(() ->
                        new PatientNotFoundException(dto.getPatientId()));

        Hypothesis hypothesis = new Hypothesis();

        hypothesis.setCode(dto.getCode());
        hypothesis.setDisplay(dto.getDisplay());
        hypothesis.setOperatorName(dto.getOperatorName());
        hypothesis.setTimestamp(dto.getTimestamp());
        hypothesis.setPatient(patient);

        Hypothesis saved = service.save(hypothesis);

        return new HypothesisDTO(
                saved.getPatient().getId(),
                saved.getCode(),
                saved.getDisplay(),
                saved.getOperatorName(),
                saved.getTimestamp()
        );
    }

    @GetMapping("/patient/{patientId}")
    public List<HypothesisDTO> getByPatient(
            @PathVariable Long patientId
    ) {

        return service.getByPatientId(patientId)
                .stream()
                .map(h -> new HypothesisDTO(
                        h.getPatient().getId(),
                        h.getCode(),
                        h.getDisplay(),
                        h.getOperatorName(),
                        h.getTimestamp()
                ))
                .toList();
    }
}