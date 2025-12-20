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

    public VendorPerformanceScoreServiceImpl(VendorPerformanceScoreRepository scoreRepo, 
                                            DeliveryEvaluationRepository evaluationRepo, 
                                            VendorRepository vendorRepo) {
        this.scoreRepo = scoreRepo;
        this.evaluationRepo = evaluationRepo;
        this.vendorRepo = vendorRepo;
    }

    @Override
    public VendorPerformanceScore calculateScore(Long vendorId) {
        Vendor vendor = vendorRepo.findById(vendorId)
                .orElseThrow(() -> new RuntimeException("vendor not found"));

        List<DeliveryEvaluation> evaluations = evaluationRepo.findByVendorId(vendorId);
        if (evaluations.isEmpty()) {
            throw new IllegalArgumentException("No evaluations found");
        }

        long onTimeCount = evaluations.stream().filter(DeliveryEvaluation::getMeetsDeliveryTarget).count();
        long qualityCount = evaluations.stream().filter(DeliveryEvaluation::getMeetsQualityTarget).count();

        double onTimePercentage = (double) onTimeCount / evaluations.size() * 100;
        double qualityPercentage = (double) qualityCount / evaluations.size() * 100;
        double overallScore = (onTimePercentage + qualityPercentage) / 2;

        VendorPerformanceScore score = new VendorPerformanceScore();
        score.setVendor(vendor);
        score.setOnTimePercentage(onTimePercentage);
        score.setQualityCompliancePercentage(qualityPercentage);
        score.setOverallScore(overallScore);

        return scoreRepo.save(score);
    }

    @Override
    public VendorPerformanceScore getLatestScore(Long vendorId) {
        List<VendorPerformanceScore> scores = scoreRepo.findByVendorIdOrderByCalculatedAtDesc(vendorId);
        return scores.isEmpty() ? null : scores.get(0);
    }

    @Override
    public List<VendorPerformanceScore> getScoresForVendor(Long vendorId) {
        return scoreRepo.findByVendorIdOrderByCalculatedAtDesc(vendorId);
    }
}