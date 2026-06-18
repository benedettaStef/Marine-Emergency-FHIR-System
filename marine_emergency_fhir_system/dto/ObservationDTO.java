package com.benedetta.marine_emergency_fhir_system.dto;

import java.time.LocalDateTime;

public class ObservationDTO {

    private String resourceType;
    private String status;
    private Code code;
    private Subject subject;
    private LocalDateTime effectiveDateTime;

    public ObservationDTO(
            Long patientId,
            String codeValue,
            String display,
            LocalDateTime timestamp
    ) {
        this.resourceType = "Observation";
        this.status = "final";
        this.code = new Code(codeValue, display);
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

    public static class Code {

        private String code;
        private String display;

        public Code(String code, String display) {
            this.code = code;
            this.display = display;
        }

        public String getCode() {
            return code;
        }

        public String getDisplay() {
            return display;
        }
    }

    public String getResourceType() {
        return resourceType;
    }

    public String getStatus() {
        return status;
    }

    public Code getCode() {
        return code;
    }

    public Subject getSubject() {
        return subject;
    }

    public LocalDateTime getEffectiveDateTime() {
        return effectiveDateTime;
    }
}