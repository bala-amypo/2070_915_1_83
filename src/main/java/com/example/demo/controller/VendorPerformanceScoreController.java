package com.example.demo.controller;

import com.example.demo.model.VendorPerformanceScore;
import com.example.demo.service.VendorPerformanceScoreService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/scores") // [cite: 390]
public class VendorPerformanceScoreController {
    private final VendorPerformanceScoreService scoreService;

    public VendorPerformanceScoreController(VendorPerformanceScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @PostMapping("/calculate/{vendorId}") // [cite: 393]
    public VendorPerformanceScore calculate(@PathVariable Long vendorId) {
        return scoreService.calculateScore(vendorId);
    }

    @GetMapping("/vendor/{vendorId}") // [cite: 395]
    public List<VendorPerformanceScore> getHistory(@PathVariable Long vendorId) {
        return scoreService.getScoresForVendor(vendorId);
    }
}