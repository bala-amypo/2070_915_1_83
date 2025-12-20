package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class SLARequirement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String requirementName;
    private String description;
    private Integer maxDeliveryDays;
    private Double minQualityScore;
    private Boolean active = true;

    public SLARequirement() {}

    public SLARequirement(String requirementName, String description, Integer maxDeliveryDays, Double minQualityScore) {
        this.requirementName = requirementName;
        this.description = description;
        this.maxDeliveryDays = maxDeliveryDays;
        this.minQualityScore = minQualityScore;
        this.active = true;
    }

    // Getters and Setters...
    public String getRequirementName() { return requirementName; }
    public Integer getMaxDeliveryDays() { return maxDeliveryDays; }
    public Double getMinQualityScore() { return minQualityScore; }
    public void setActive(Boolean active) { this.active = active; }
    public Long getId() { return id; }
}