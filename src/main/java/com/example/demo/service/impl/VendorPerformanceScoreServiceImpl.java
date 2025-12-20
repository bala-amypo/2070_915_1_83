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
            .orElseThrow(() -> new RuntimeException("Vendor not found")); // [cite: 300]

        List<DeliveryEvaluation> evaluations = evaluationRepo.findByVendorId(vendorId); // [cite: 302]
        if (evaluations.isEmpty()) {
            throw new IllegalArgumentException("No evaluations found to calculate score");
        }

        long onTimeCount = evaluations.stream().filter(DeliveryEvaluation::getMeetsDeliveryTarget).count();
        long qualityCount = evaluations.stream().filter(DeliveryEvaluation::getMeetsQualityTarget).count();

        double onTimePercent = (double) onTimeCount / evaluations.size() * 100; // [cite: 304]
        double qualityPercent = (double) qualityCount / evaluations.size() * 100; // [cite: 305]
        double overall = (onTimePercent + qualityPercent) / 2; // [cite: 306, 99]

        VendorPerformanceScore score = new VendorPerformanceScore();
        score.setVendor(vendor);
        score.setOnTimePercentage(onTimePercent);
        score.setQualityCompliancePercentage(qualityPercent);
        score.setOverallScore(overall);
        
        return scoreRepo.save(score); // [cite: 307]
    }

    @Override
    public List<VendorPerformanceScore> getScoresForVendor(Long vendorId) {
        return scoreRepo.findByVendorIdOrderByCalculatedAtDesc(vendorId); // [cite: 311]
    }
}