package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class SLARequirement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // [cite: 58]

    @Column(unique = true, nullable = false)
    private String requirementName; // [cite: 59, 69]

    private String description; // [cite: 60]
    private Integer maxDeliveryDays; // [cite: 61]
    private Double minQualityScore; // [cite: 62]
    private Boolean active = true; // [cite: 64]

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getRequirementName() { return requirementName; }
    public void setRequirementName(String requirementName) { this.requirementName = requirementName; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Integer getMaxDeliveryDays() { return maxDeliveryDays; }
    public void setMaxDeliveryDays(Integer maxDeliveryDays) { this.maxDeliveryDays = maxDeliveryDays; }
    public Double getMinQualityScore() { return minQualityScore; }
    public void setMinQualityScore(Double minQualityScore) { this.minQualityScore = minQualityScore; }
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}