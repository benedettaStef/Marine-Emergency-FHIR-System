package com.benedetta.marine_emergency_fhir_system.controller;

import com.benedetta.marine_emergency_fhir_system.dto.CarePlanCreateDTO;
import com.benedetta.marine_emergency_fhir_system.dto.CarePlanDTO;
import com.benedetta.marine_emergency_fhir_system.exception.PatientNotFoundException;
import com.benedetta.marine_emergency_fhir_system.model.CarePlan;
import com.benedetta.marine_emergency_fhir_system.model.Patient;
import com.benedetta.marine_emergency_fhir_system.service.CarePlanService;
import com.benedetta.marine_emergency_fhir_system.service.PatientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/careplans")
public class CarePlanController {

    private final CarePlanService service;
    private final PatientService patientService;

    public CarePlanController(
            CarePlanService service,
            PatientService patientService
    ) {
        this.service = service;
        this.patientService = patientService;
    }

    @PostMapping
    public CarePlanDTO create(
            @RequestBody CarePlanCreateDTO dto
    ) {

        Patient patient = patientService.getById(dto.getPatientId())
                .orElseThrow(() ->
                        new PatientNotFoundException(dto.getPatientId()));

        CarePlan carePlan = new CarePlan();

        carePlan.setCode(dto.getCode());
        carePlan.setActivity(dto.getActivity());
        carePlan.setStatus(dto.getStatus());
        carePlan.setCreatedDate(dto.getCreatedDate());
        carePlan.setPatient(patient);

        CarePlan saved = service.save(carePlan);

        return new CarePlanDTO(
                saved.getPatient().getId(),
                saved.getCode(),
                saved.getActivity(),
                saved.getStatus(),
                saved.getCreatedDate()
        );
    }

    @GetMapping("/patient/{patientId}")
    public List<CarePlanDTO> getByPatient(
            @PathVariable Long patientId
    ) {

        return service.getByPatientId(patientId)
                .stream()
                .map(cp -> new CarePlanDTO(
                        cp.getPatient().getId(),
                        cp.getCode(),
                        cp.getActivity(),
                        cp.getStatus(),
                        cp.getCreatedDate()
                ))
                .toList();
    }
}