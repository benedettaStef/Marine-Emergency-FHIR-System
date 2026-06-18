package com.benedetta.marine_emergency_fhir_system.dto;

import java.time.LocalDateTime;

public class CarePlanDTO {

    private String resourceType;

    private String status;

    private Activity activity;

    private Subject subject;

    private LocalDateTime createdDate;

    public CarePlanDTO(
            Long patientId,
            String code,
            String activityText,
            String status,
            LocalDateTime createdDate
    ) {
        this.resourceType = "CarePlan";
        this.status = status;
        this.activity = new Activity(code, activityText);
        this.subject = new Subject(patientId);
        this.createdDate = createdDate;
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

    public static class Activity {

        private String code;

        private String display;

        public Activity(String code, String display) {
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

    public Activity getActivity() {
        return activity;
    }

    public Subject getSubject() {
        return subject;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }
}