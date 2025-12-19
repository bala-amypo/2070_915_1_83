package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.DeliveryEvaluationService;

import java.util.List;

public class DeliveryEvaluationServiceImpl implements DeliveryEvaluationService {

    private final DeliveryEvaluationRepository evaluationRepo;
    private final VendorRepository vendorRepo;
    private final SLARequirementRepository slaRepo;

    public DeliveryEvaluationServiceImpl(
            DeliveryEvaluationRepository evaluationRepo,
            VendorRepository vendorRepo,
            SLARequirementRepository slaRepo) {

        this.evaluationRepo = evaluationRepo;
        this.vendorRepo = vendorRepo;
        this.slaRepo = slaRepo;
    }

    @Override
    public DeliveryEvaluation createEvaluation(DeliveryEvaluation eval) {

        Vendor vendor = vendorRepo.findById(eval.getVendor().getId())
                .orElseThrow(() -> new IllegalStateException("not found"));

        if (!vendor.getActive())
            throw new IllegalStateException("active vendors");

        if (eval.getActualDeliveryDays() < 0)
            throw new IllegalArgumentException(">= 0");

        if (eval.getQualityScore() < 0 || eval.getQualityScore() > 100)
            throw new IllegalArgumentException("Quality score");

        SLARequirement sla = slaRepo.findById(eval.getSlaRequirement().getId())
                .orElseThrow(() -> new IllegalStateException("not found"));

        eval.setMeetsDeliveryTarget(
                eval.getActualDeliveryDays() <= sla.getMaxDeliveryDays());

        eval.setMeetsQualityTarget(
                eval.getQualityScore() >= sla.getMinQualityScore());

        return evaluationRepo.save(eval);
    }

    @Override
    public DeliveryEvaluation getEvaluationById(Long id) {
        return evaluationRepo.findById(id)
                .orElseThrow(() -> new IllegalStateException("not found"));
    }

    @Override
    public List<DeliveryEvaluation> getEvaluationsForVendor(Long vendorId) {
        return evaluationRepo.findByVendorId(vendorId);
    }

    @Override
    public List<DeliveryEvaluation> getEvaluationsForRequirement(Long reqId) {
        return evaluationRepo.findBySlaRequirementId(reqId);
    }
}
