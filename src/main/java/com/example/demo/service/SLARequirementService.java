package com.example.demo.service;

import com.example.demo.model.SLARequirement;
import java.util.List;

public interface SLARequirementService {
    SLARequirement createRequirement(SLARequirement req); [cite_start]// [cite: 248]
    SLARequirement updateRequirement(Long id, SLARequirement req); [cite_start]// [cite: 250]
    SLARequirement getRequirementById(Long id); [cite_start]// [cite: 251]
    List<SLARequirement> getAllRequirements(); [cite_start]// [cite: 253]
    void deactivateRequirement(Long id); [cite_start]// [cite: 254]
}