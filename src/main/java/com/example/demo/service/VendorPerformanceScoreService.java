package com.example.demo.service;

import com.example.demo.model.VendorPerformanceScore;
import java.util.List;

public interface VendorPerformanceScoreService {
    VendorPerformanceScore calculateScore(Long vendorId); [cite_start]// [cite: 292]
    VendorPerformanceScore getLatestScore(Long vendorId); [cite_start]// [cite: 293]
    List<VendorPerformanceScore> getScoresForVendor(Long vendorId); [cite_start]// [cite: 294]
}