package com.benedetta.marine_emergency_fhir_system.dto;

import java.time.LocalDateTime;

public class ConditionDTO {

    private String resourceType;

    private Code code;

    private String clinicalStatus;

    private String verificationStatus;

    private Subject subject;

    private LocalDateTime recordedDate;

    public ConditionDTO(
            Long patientId,
            String codeValue,
            String display,
            String clinicalStatus,
            String verificationStatus,
            LocalDateTime recordedDate
    ) {
        this.resourceType = "Condition";
        this.code = new Code(codeValue, display);
        this.clinicalStatus = clinicalStatus;
        this.verificationStatus = verificationStatus;
        this.subject = new Subject(patientId);
        this.recordedDate = recordedDate;
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

    public String getClinicalStatus() {
        return clinicalStatus;
    }

    public String getVerificationStatus() {
        return verificationStatus;
    }

    public Subject getSubject() {
        return subject;
    }

    public LocalDateTime getRecordedDate() {
        return recordedDate;
    }
}