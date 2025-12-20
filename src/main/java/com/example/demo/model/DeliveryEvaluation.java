package com.example.demo.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class DeliveryEvaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; [cite_start]// [cite: 73]

    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private Vendor vendor; [cite_start]// [cite: 74]

    @ManyToOne
    @JoinColumn(name = "sla_requirement_id")
    private SLARequirement slaRequirement; [cite_start]// [cite: 75]

    private Integer actualDeliveryDays; [cite_start]// [cite: 76, 82]
    private Double qualityScore; [cite_start]// [cite: 77, 83]
    private Date evaluationDate; [cite_start]// [cite: 79]
    private Boolean meetsDeliveryTarget; [cite_start]// [cite: 80, 84]
    private Boolean meetsQualityTarget; [cite_start]// [cite: 81, 85]

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Vendor getVendor() { return vendor; }
    public void setVendor(Vendor vendor) { this.vendor = vendor; }
    public SLARequirement getSlaRequirement() { return slaRequirement; }
    public void setSlaRequirement(SLARequirement slaRequirement) { this.slaRequirement = slaRequirement; }
    public Integer getActualDeliveryDays() { return actualDeliveryDays; }
    public void setActualDeliveryDays(Integer actualDeliveryDays) { this.actualDeliveryDays = actualDeliveryDays; }
    public Double getQualityScore() { return qualityScore; }
    public void setQualityScore(Double qualityScore) { this.qualityScore = qualityScore; }
    public Date getEvaluationDate() { return evaluationDate; }
    public void setEvaluationDate(Date evaluationDate) { this.evaluationDate = evaluationDate; }
    public Boolean getMeetsDeliveryTarget() { return meetsDeliveryTarget; }
    public void setMeetsDeliveryTarget(Boolean meetsDeliveryTarget) { this.meetsDeliveryTarget = meetsDeliveryTarget; }
    public Boolean getMeetsQualityTarget() { return meetsQualityTarget; }
    public void setMeetsQualityTarget(Boolean meetsQualityTarget) { this.meetsQualityTarget = meetsQualityTarget; }
}