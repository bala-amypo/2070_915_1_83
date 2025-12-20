package com.example.demo.service.impl;

import com.example.demo.model.DeliveryEvaluation;
import com.example.demo.model.SLARequirement;
import com.example.demo.model.Vendor;
import com.example.demo.repository.DeliveryEvaluationRepository;
import com.example.demo.repository.SLARequirementRepository;
import com.example.demo.repository.VendorRepository;
import com.example.demo.service.DeliveryEvaluationService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DeliveryEvaluationServiceImpl implements DeliveryEvaluationService {
    private final DeliveryEvaluationRepository evaluationRepo;
    private final VendorRepository vendorRepo;
    private final SLARequirementRepository slaRepo;

    public DeliveryEvaluationServiceImpl(DeliveryEvaluationRepository evaluationRepo, VendorRepository vendorRepo, SLARequirementRepository slaRepo) {
        this.evaluationRepo = evaluationRepo;
        this.vendorRepo = vendorRepo;
        this.slaRepo = slaRepo;
    }

    @Override
    public DeliveryEvaluation createEvaluation(DeliveryEvaluation evaluation) {
        Vendor vendor = vendorRepo.findById(evaluation.getVendor().getId())
                .orElseThrow(() -> new RuntimeException("vendor not found"));
        SLARequirement sla = slaRepo.findById(evaluation.getSlaRequirement().getId())
                .orElseThrow(() -> new RuntimeException("SLA not found"));

        if (vendor.getActive() == null || !vendor.getActive()) {
            throw new IllegalStateException("active vendors");
        }
        if (evaluation.getActualDeliveryDays() < 0) {
            throw new IllegalArgumentException(">=0");
        }
        if (evaluation.getQualityScore() < 0 || evaluation.getQualityScore() > 100) {
            throw new IllegalArgumentException("Quality score between 0 and 100");
        }

        evaluation.setMeetsDeliveryTarget(evaluation.getActualDeliveryDays() <= sla.getMaxDeliveryDays());
        evaluation.setMeetsQualityTarget(evaluation.getQualityScore() >= sla.getMinQualityScore());
        
        return evaluationRepo.save(evaluation);
    }

    @Override
    public DeliveryEvaluation getEvaluationById(Long id) {
        return evaluationRepo.findById(id).orElseThrow(() -> new RuntimeException("not found"));
    }

    @Override
    public List<DeliveryEvaluation> getEvaluationsForVendor(Long vendorId) {
        return evaluationRepo.findByVendorId(vendorId);
    }

    @Override
    public List<DeliveryEvaluation> getEvaluationsForRequirement(Long requirementId) {
        return evaluationRepo.findBySlaRequirementId(requirementId);
    }
}   