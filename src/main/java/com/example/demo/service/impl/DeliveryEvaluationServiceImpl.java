package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.DeliveryEvaluationService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DeliveryEvaluationServiceImpl implements DeliveryEvaluationService {
    private final DeliveryEvaluationRepository evaluationRepo;
    private final VendorRepository vendorRepo;
    private final SLARequirementRepository slaRepo;

    public DeliveryEvaluationServiceImpl(DeliveryEvaluationRepository evaluationRepo, 
                                        VendorRepository vendorRepo, 
                                        SLARequirementRepository slaRepo) {
        this.evaluationRepo = evaluationRepo;
        this.vendorRepo = vendorRepo;
        this.slaRepo = slaRepo;
    }

    @Override
    public DeliveryEvaluation createEvaluation(DeliveryEvaluation evaluation) {
        Vendor vendor = vendorRepo.findById(evaluation.getVendor().getId())
            .orElseThrow(() -> new RuntimeException("Vendor not found"));
        SLARequirement sla = slaRepo.findById(evaluation.getSlaRequirement().getId())
            .orElseThrow(() -> new RuntimeException("SLA not found"));

        if (!vendor.getActive()) {
            throw new IllegalStateException("Only active vendors allowed"); // [cite: 280]
        }
        if (evaluation.getActualDeliveryDays() < 0) {
            throw new IllegalArgumentException("Actual delivery days must be >=0"); // [cite: 281]
        }
        if (evaluation.getQualityScore() < 0 || evaluation.getQualityScore() > 100) {
            throw new IllegalArgumentException("Quality score must be between 0 and 100"); // [cite: 282]
        }

        evaluation.setMeetsDeliveryTarget(evaluation.getActualDeliveryDays() <= sla.getMaxDeliveryDays());
        evaluation.setMeetsQualityTarget(evaluation.getQualityScore() >= sla.getMinQualityScore());
        
        return evaluationRepo.save(evaluation);
    }

    @Override
    public List<DeliveryEvaluation> getEvaluationsForVendor(Long vendorId) {
        return evaluationRepo.findByVendorId(vendorId);
    }
    
    // Additional methods implemented here...
}