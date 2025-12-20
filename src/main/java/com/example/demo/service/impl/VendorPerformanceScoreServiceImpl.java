package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.VendorPerformanceScoreService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VendorPerformanceScoreServiceImpl implements VendorPerformanceScoreService {
    private final VendorPerformanceScoreRepository scoreRepo;
    private final DeliveryEvaluationRepository evaluationRepo;
    private final VendorRepository vendorRepo;
    private final VendorTierRepository tierRepo;

    public VendorPerformanceScoreServiceImpl(
            VendorPerformanceScoreRepository scoreRepo,
            DeliveryEvaluationRepository evaluationRepo,
            VendorRepository vendorRepo,
            VendorTierRepository tierRepo) {
        this.scoreRepo = scoreRepo;
        this.evaluationRepo = evaluationRepo;
        this.vendorRepo = vendorRepo;
        this.tierRepo = tierRepo;
    }

    @Override
    public VendorPerformanceScore calculateScore(Long vendorId) {
        Vendor vendor = vendorRepo.findById(vendorId)
                .orElseThrow(() -> new RuntimeException("not found"));

        List<DeliveryEvaluation> evals = evaluationRepo.findByVendorId(vendorId);
        if (evals.isEmpty()) {
            throw new IllegalArgumentException("No evaluations found");
        }

        // Corrected filtering: use boolean targets instead of integer days
        long onTime = evals.stream().filter(DeliveryEvaluation::getMeetsDeliveryTarget).count();
        long quality = evals.stream().filter(DeliveryEvaluation::getMeetsQualityTarget).count();

        double onTimePerc = (double) onTime / evals.size() * 100;
        double qualityPerc = (double) quality / evals.size() * 100;
        double overall = (onTimePerc + qualityPerc) / 2;

        VendorPerformanceScore score = new VendorPerformanceScore();
        score.setVendor(vendor);
        score.setOnTimePercentage(onTimePerc);
        score.setQualityCompliancePercentage(qualityPerc);
        score.setOverallScore(overall);

        return scoreRepo.save(score);
    }

    @Override
    public VendorPerformanceScore getLatestScore(Long vendorId) {
        List<VendorPerformanceScore> scores = scoreRepo.findByVendorOrderByCalculatedAtDesc(vendorId);
        return scores.isEmpty() ? null : scores.get(0);
    }

    @Override
    public List<VendorPerformanceScore> getScoresForVendor(Long vendorId) {
        return scoreRepo.findByVendorOrderByCalculatedAtDesc(vendorId);
    }
}