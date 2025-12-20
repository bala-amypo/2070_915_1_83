package com.example.demo.service;

import com.example.demo.model.SLARequirement;
import java.util.List;

public interface SLARequirementService {
    SLARequirement createRequirement(SLARequirement req); // [cite: 248]
    SLARequirement getRequirementById(Long id); // [cite: 251]
    List<SLARequirement> getAllRequirements(); // [cite: 253]
    void deactivateRequirement(Long id); // [cite: 254]
}