package com.example.demo.controller;

import com.example.demo.model.VendorPerformanceScore;
import com.example.demo.service.VendorPerformanceScoreService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/scores")
@Tag(name = "Vendor Performance Scores")
public class VendorPerformanceScoreController {

    private final VendorPerformanceScoreService scoreService;

    public VendorPerformanceScoreController(VendorPerformanceScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @PostMapping("/calculate/{vendorId}")
    public VendorPerformanceScore calculate(@PathVariable Long vendorId) {
        return scoreService.calculateScore(vendorId);
    }

    @GetMapping("/latest/{vendorId}")
    public VendorPerformanceScore latest(@PathVariable Long vendorId) {
        return scoreService.getLatestScore(vendorId);
    }

    @GetMapping("/vendor/{vendorId}")
    public List<VendorPerformanceScore> history(@PathVariable Long vendorId) {
        return scoreService.getScoresForVendor(vendorId);
    }
}
