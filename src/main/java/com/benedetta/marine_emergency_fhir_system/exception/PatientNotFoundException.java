package com.benedetta.marine_emergency_fhir_system.exception;


public class PatientNotFoundException extends RuntimeException {

    public PatientNotFoundException(Long id) {
        super("Patient with id " + id + " not found");
    }
}
