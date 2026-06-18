package com.benedetta.marine_emergency_fhir_system.service;

import com.benedetta.marine_emergency_fhir_system.model.CarePlan;
import com.benedetta.marine_emergency_fhir_system.repository.CarePlanRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarePlanService {

    private final CarePlanRepository repository;

    public CarePlanService(CarePlanRepository repository) {
        this.repository = repository;
    }

    public CarePlan save(CarePlan carePlan) {
        return repository.save(carePlan);
    }

    public List<CarePlan> getByPatientId(Long patientId) {
        return repository.findByPatientId(patientId);
    }
}