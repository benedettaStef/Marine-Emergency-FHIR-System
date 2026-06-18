package com.benedetta.marine_emergency_fhir_system.dto;

import java.time.LocalDateTime;

public class CommunicationDTO {

    private String resourceType;

    private String code;

    private String message;

    private String sender;

    private Subject subject;

    private LocalDateTime timestamp;

    public CommunicationDTO(
            Long patientId,
            String code,
            String message,
            String sender,
            LocalDateTime timestamp
    ) {
        this.resourceType = "Communication";
        this.code = code;
        this.message = message;
        this.sender = sender;
        this.subject = new Subject(patientId);
        this.timestamp = timestamp;
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

    public String getMessage() {
        return message;
    }

    public String getSender() {
        return sender;
    }

    public Subject getSubject() {
        return subject;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}