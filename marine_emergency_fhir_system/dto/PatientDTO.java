package com.benedetta.marine_emergency_fhir_system.dto;

import java.time.LocalDate;
import java.util.List;

public class PatientDTO {

    private String resourceType;
    private Long id;
    private List<HumanName> name;
    private LocalDate birthDate;

    public PatientDTO() {}

    public PatientDTO(Long id, String patientName, LocalDate birthDate) {
        this.resourceType = "Patient";
        this.id = id;
        this.name = List.of(new HumanName(patientName));
        this.birthDate = birthDate;
    }

    public static class HumanName {

        private String text;

        public HumanName(){}
        public HumanName(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }
        public void setText(String text) {
            this.text = text;
        }
    }

    public String getResourceType() {
        return resourceType;
    }
    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public List<HumanName> getName() {
        return name;
    }
    public void setName(List<HumanName> name) {
        this.name = name;
    }
    public LocalDate getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
