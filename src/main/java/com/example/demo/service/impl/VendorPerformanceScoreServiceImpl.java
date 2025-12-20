package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.VendorPerformanceScoreService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VendorPerformanceScoreServiceImpl implements VendorPerformanceScoreService {
    private final VendorPerformanceScoreRepository vendorPerformanceScoreRepository;
    private final DeliveryEvaluationRepository deliveryEvaluationRepository;
    private final VendorRepository vendorRepository;

    public VendorPerformanceScoreServiceImpl(VendorPerformanceScoreRepository vendorPerformanceScoreRepository,
                                             DeliveryEvaluationRepository deliveryEvaluationRepository,
                                             VendorRepository vendorRepository) {
        this.vendorPerformanceScoreRepository = vendorPerformanceScoreRepository; [cite_start]// [cite: 297]
        this.deliveryEvaluationRepository = deliveryEvaluationRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public VendorPerformanceScore calculateScore(Long vendorId) {
        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new RuntimeException("vendor not found")); [cite_start]// [cite: 300]

        List<DeliveryEvaluation> evaluations = deliveryEvaluationRepository.findByVendorId(vendorId); [cite_start]// [cite: 302]
        if (evaluations.isEmpty()) return null;

        long onTimeCount = evaluations.stream().filter(DeliveryEvaluation::getMeetsDeliveryTarget).count();
        long qualityCount = evaluations.stream().filter(DeliveryEvaluation::getMeetsQualityTarget).count();

        double onTimePercentage = (double) onTimeCount / evaluations.size() * 100; [cite_start]// [cite: 304]
        double qualityPercentage = (double) qualityCount / evaluations.size() * 100; [cite_start]// [cite: 305]
        double overallScore = (onTimePercentage * 0.4) + (qualityPercentage * 0.6); [cite_start]// [cite: 306]

        VendorPerformanceScore score = new VendorPerformanceScore();
        score.setVendor(vendor);
        score.setOnTimePercentage(onTimePercentage);
        score.setQualityCompliancePercentage(qualityPercentage);
        score.setOverallScore(overallScore);

        return vendorPerformanceScoreRepository.save(score); [cite_start]// [cite: 307]
    }

    @Override
    public VendorPerformanceScore getLatestScore(Long vendorId) {
        List<VendorPerformanceScore> scores = vendorPerformanceScoreRepository.findByVendorIdOrderByCalculatedAtDesc(vendorId);
        return scores.isEmpty() ? null : scores.get(0); [cite_start]// [cite: 309]
    }

    @Override
    public List<VendorPerformanceScore> getScoresForVendor(Long vendorId) {
        return vendorPerformanceScoreRepository.findByVendorIdOrderByCalculatedAtDesc(vendorId); [cite_start]// [cite: 311]
    }
}