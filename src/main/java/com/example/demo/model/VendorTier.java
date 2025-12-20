package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class VendorTier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; [cite_start]// [cite: 104]

    @Column(unique = true, nullable = false)
    private String tierName; [cite_start]// [cite: 105, 116]

    private Double minScoreThreshold; [cite_start]// [cite: 106, 115]
    private String description; [cite_start]// [cite: 108]
    private Boolean active = true; [cite_start]// [cite: 110]

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTierName() { return tierName; }
    public void setTierName(String tierName) { this.tierName = tierName; }
    public Double getMinScoreThreshold() { return minScoreThreshold; }
    public void setMinScoreThreshold(Double minScoreThreshold) { this.minScoreThreshold = minScoreThreshold; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}