package com.benedetta.marine_emergency_fhir_system.dto;

import java.time.LocalDateTime;

public class HypothesisDTO {

    private String resourceType;

    private String code;

    private String display;

    private String operatorName;

    private Subject subject;

    private LocalDateTime effectiveDateTime;

    public HypothesisDTO(
            Long patientId,
            String code,
            String display,
            String operatorName,
            LocalDateTime timestamp
    ) {
        this.resourceType = "Observation";
        this.code = code;
        this.display = display;
        this.operatorName = operatorName;
        this.subject = new Subject(patientId);
        this.effectiveDateTime = timestamp;
    }

    public static class Subject {

        private String reference;

        public Subject(Long patientId) {
            this.reference = "Patient/" + patientId;
        }

        public String getReference() {
            return reference;
        }
    }

    public String getResourceType() {
        return resourceType;
    }

    public String getCode() {
        return code;
    }

    public String getDisplay() {
        return display;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public Subject getSubject() {
        return subject;
    }

    public LocalDateTime getEffectiveDateTime() {
        return effectiveDateTime;
    }
}
