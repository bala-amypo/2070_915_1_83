package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.VendorPerformanceScoreService;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.util.List;

@Service
public class VendorPerformanceScoreServiceImpl implements VendorPerformanceScoreService {
    private final VendorPerformanceScoreRepository vendorPerformanceScoreRepository;
    private final DeliveryEvaluationRepository deliveryEvaluationRepository;
    private final VendorRepository vendorRepository;
    private final VendorTierRepository vendorTierRepository;

    public VendorPerformanceScoreServiceImpl(
            VendorPerformanceScoreRepository vendorPerformanceScoreRepository,
            DeliveryEvaluationRepository deliveryEvaluationRepository,
            VendorRepository vendorRepository,
            VendorTierRepository vendorTierRepository) {
        this.vendorPerformanceScoreRepository = vendorPerformanceScoreRepository;
        this.deliveryEvaluationRepository = deliveryEvaluationRepository;
        this.vendorRepository = vendorRepository;
        this.vendorTierRepository = vendorTierRepository;
    }

    @Override
    public VendorPerformanceScore calculateScore(Long vendorId) {
        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new RuntimeException("vendor not found"));

        List<DeliveryEvaluation> evaluations = deliveryEvaluationRepository.findByVendorId(vendorId);
        if (evaluations.isEmpty()) {
            throw new IllegalArgumentException("No evaluations found");
        }

        // Percentage calculations using explicit method references
        long onTimeCount = evaluations.stream()
                .filter(e -> Boolean.TRUE.equals(e.getMeetsDeliveryTarget()))
                .count();
        long qualityCount = evaluations.stream()
                .filter(e -> Boolean.TRUE.equals(e.getMeetsQualityTarget()))
                .count();

        double onTimePercentage = (double) onTimeCount / evaluations.size() * 100;
        double qualityCompliancePercentage = (double) qualityCount / evaluations.size() * 100;
        double overallScore = (onTimePercentage + qualityCompliancePercentage) / 2;

        VendorPerformanceScore score = new VendorPerformanceScore();
        score.setVendor(vendor);
        score.setOnTimePercentage(onTimePercentage);
        score.setQualityCompliancePercentage(qualityCompliancePercentage);
        score.setOverallScore(overallScore);
        score.setCalculatedAt(new Timestamp(System.currentTimeMillis()));

        return vendorPerformanceScoreRepository.save(score);
    }

    @Override
    public VendorPerformanceScore getLatestScore(Long vendorId) {
        List<VendorPerformanceScore> scores = vendorPerformanceScoreRepository.findByVendorOrderByCalculatedAtDesc(vendorId);
        return scores.isEmpty() ? null : scores.get(0);
    }

    @Override
    public List<VendorPerformanceScore> getScoresForVendor(Long vendorId) {
        return vendorPerformanceScoreRepository.findByVendorOrderByCalculatedAtDesc(vendorId);
    }
}