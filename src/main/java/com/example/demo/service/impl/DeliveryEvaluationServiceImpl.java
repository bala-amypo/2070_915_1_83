package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.DeliveryEvaluationService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DeliveryEvaluationServiceImpl implements DeliveryEvaluationService {
    private final DeliveryEvaluationRepository deliveryEvaluationRepository;
    private final VendorRepository vendorRepository;
    private final SLARequirementRepository slaRequirementRepository;

    public DeliveryEvaluationServiceImpl(DeliveryEvaluationRepository deliveryEvaluationRepository,
                                         VendorRepository vendorRepository,
                                         SLARequirementRepository slaRequirementRepository) {
        this.deliveryEvaluationRepository = deliveryEvaluationRepository; [cite_start]// [cite: 275]
        this.vendorRepository = vendorRepository;
        this.slaRequirementRepository = slaRequirementRepository;
    }

    @Override
    public DeliveryEvaluation createEvaluation(DeliveryEvaluation evaluation) {
        Vendor vendor = vendorRepository.findById(evaluation.getVendor().getId())
                .orElseThrow(() -> new RuntimeException("vendor not found")); [cite_start]// [cite: 279]
        SLARequirement sla = slaRequirementRepository.findById(evaluation.getSlaRequirement().getId())
                .orElseThrow(() -> new RuntimeException("SLA not found")); [cite_start]// [cite: 279]

        if (!vendor.getActive()) {
            throw new IllegalStateException("Only active vendors allowed"); [cite_start]// [cite: 280]
        }
        if (evaluation.getActualDeliveryDays() < 0) {
            throw new IllegalArgumentException("actualDeliveryDays >=0"); [cite_start]// [cite: 281]
        }
        if (evaluation.getQualityScore() < 0 || evaluation.getQualityScore() > 100) {
            throw new IllegalArgumentException("Quality score between 0 and 100"); [cite_start]// [cite: 282]
        }

        evaluation.setMeetsDeliveryTarget(evaluation.getActualDeliveryDays() <= sla.getMaxDeliveryDays()); [cite_start]// [cite: 284]
        evaluation.setMeetsQualityTarget(evaluation.getQualityScore() >= sla.getMinQualityScore()); [cite_start]// [cite: 286]

        return deliveryEvaluationRepository.save(evaluation); [cite_start]// [cite: 287]
    }

    @Override
    public DeliveryEvaluation getEvaluationById(Long id) {
        return deliveryEvaluationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("evaluation not found"));
    }

    @Override
    public List<DeliveryEvaluation> getEvaluationsForVendor(Long vendorId) {
        return deliveryEvaluationRepository.findByVendorId(vendorId); [cite_start]// [cite: 288]
    }

    @Override
    public List<DeliveryEvaluation> getEvaluationsForRequirement(Long requirementId) {
        return deliveryEvaluationRepository.findBySlaRequirementId(requirementId); [cite_start]// [cite: 289]
    }
}