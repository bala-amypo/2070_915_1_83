package com.example.demo.service.impl;

import com.example.demo.model.SLARequirement;
import com.example.demo.repository.SLARequirementRepository;
import com.example.demo.service.SLARequirementService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SLARequirementServiceImpl implements SLARequirementService {
    private final SLARequirementRepository slaRequirementRepository;

    public SLARequirementServiceImpl(SLARequirementRepository slaRequirementRepository) {
        this.slaRequirementRepository = slaRequirementRepository; [cite_start]// [cite: 256]
    }

    @Override
    public SLARequirement createRequirement(SLARequirement req) {
        if (req.getMaxDeliveryDays() != null && req.getMaxDeliveryDays() <= 0) {
            throw new IllegalArgumentException("Max delivery days must be > 0"); [cite_start]// [cite: 262]
        }
        if (req.getMinQualityScore() != null && (req.getMinQualityScore() < 0 || req.getMinQualityScore() > 100)) {
            throw new IllegalArgumentException("Quality score must be between 0 and 100"); [cite_start]// [cite: 263]
        }
        if (slaRequirementRepository.existsByRequirementName(req.getRequirementName())) {
            throw new IllegalArgumentException("unique requirement name"); [cite_start]// [cite: 181]
        }
        return slaRequirementRepository.save(req);
    }

    @Override
    public SLARequirement updateRequirement(Long id, SLARequirement req) {
        SLARequirement existing = getRequirementById(id);
        if (!existing.getRequirementName().equals(req.getRequirementName())) {
            if (slaRequirementRepository.existsByRequirementName(req.getRequirementName())) {
                throw new IllegalArgumentException("unique"); [cite_start]// [cite: 265]
            }
        }
        existing.setRequirementName(req.getRequirementName());
        existing.setMaxDeliveryDays(req.getMaxDeliveryDays());
        existing.setMinQualityScore(req.getMinQualityScore());
        return slaRequirementRepository.save(existing);
    }

    @Override
    public SLARequirement getRequirementById(Long id) {
        return slaRequirementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("SLA requirement not found")); [cite_start]// [cite: 266]
    }

    @Override
    public List<SLARequirement> getAllRequirements() {
        return slaRequirementRepository.findAll();
    }

    @Override
    public void deactivateRequirement(Long id) {
        SLARequirement req = getRequirementById(id);
        req.setActive(false);
        slaRequirementRepository.save(req);
    }
}