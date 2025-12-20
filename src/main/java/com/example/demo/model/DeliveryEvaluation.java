package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.sql.Date;

@Entity
public class DeliveryEvaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Vendor vendor;
    @ManyToOne
    private SLARequirement slaRequirement;
    private Integer actualDeliveryDays;
    private Double qualityScore;
    private LocalDate evaluationDate;
    private Boolean meetsDeliveryTarget;
    private Boolean meetsQualityTarget;

    public DeliveryEvaluation() {}

    public DeliveryEvaluation(Vendor vendor, SLARequirement slaRequirement, Integer actualDeliveryDays, Double qualityScore, LocalDate evaluationDate) {
        this.vendor = vendor;
        this.slaRequirement = slaRequirement;
        this.actualDeliveryDays = actualDeliveryDays;
        this.qualityScore = qualityScore;
        this.evaluationDate = evaluationDate;
    }

    // Getters and Setters (Use LocalDate for evaluationDate to match tests)...
    public Vendor getVendor() { return vendor; }
    public SLARequirement getSlaRequirement() { return slaRequirement; }
    public Integer getActualDeliveryDays() { return actualDeliveryDays; }
    public Double getQualityScore() { return qualityScore; }
    public void setMeetsDeliveryTarget(Boolean meets) { this.meetsDeliveryTarget = meets; }
    public void setMeetsQualityTarget(Boolean meets) { this.meetsQualityTarget = meets; }
}