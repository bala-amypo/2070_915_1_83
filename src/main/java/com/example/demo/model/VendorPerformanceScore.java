package com.example.demo.model;

import jakarta.persistence.*;
import java.sql.Timestamp;
import org.hibernate.annotations.CreationTimestamp;

@Entity
public class VendorPerformanceScore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; [cite_start]// [cite: 89]

    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private Vendor vendor; [cite_start]// [cite: 92]

    private Double onTimePercentage; [cite_start]// [cite: 93, 98]
    private Double qualityCompliancePercentage; [cite_start]// [cite: 94, 98]
    private Double overallScore; [cite_start]// [cite: 95, 99]

    @CreationTimestamp
    private Timestamp calculatedAt; [cite_start]// [cite: 96]

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Vendor getVendor() { return vendor; }
    public void setVendor(Vendor vendor) { this.vendor = vendor; }
    public Double getOnTimePercentage() { return onTimePercentage; }
    public void setOnTimePercentage(Double onTimePercentage) { this.onTimePercentage = onTimePercentage; }
    public Double getQualityCompliancePercentage() { return qualityCompliancePercentage; }
    public void setQualityCompliancePercentage(Double qualityCompliancePercentage) { this.qualityCompliancePercentage = qualityCompliancePercentage; }
    public Double getOverallScore() { return overallScore; }
    public void setOverallScore(Double overallScore) { this.overallScore = overallScore; }
    public Timestamp getCalculatedAt() { return calculatedAt; }
    public void setCalculatedAt(Timestamp calculatedAt) { this.calculatedAt = calculatedAt; }
}