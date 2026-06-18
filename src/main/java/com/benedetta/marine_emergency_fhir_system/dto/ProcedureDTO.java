package com.benedetta.marine_emergency_fhir_system.dto;

import java.time.LocalDateTime;

public class ProcedureDTO {

    private String resourceType;

    private Code code;

    private String outcome;

    private Subject subject;

    private LocalDateTime performedDate;

    public ProcedureDTO(
            Long patientId,
            String codeValue,
            String display,
            String outcome,
            LocalDateTime performedDate
    ) {
        this.resourceType = "Procedure";
        this.code = new Code(codeValue, display);
        this.outcome = outcome;
        this.subject = new Subject(patientId);
        this.performedDate = performedDate;
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

    public Code getCode() {
        return code;
    }

    public String getOutcome() {
        return outcome;
    }

    public Subject getSubject() {
        return subject;
    }

    public LocalDateTime getPerformedDate() {
        return performedDate;
    }
}
