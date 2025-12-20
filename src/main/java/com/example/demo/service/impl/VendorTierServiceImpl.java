package com.example.demo.service.impl;

import com.example.demo.model.VendorTier;
import com.example.demo.repository.VendorTierRepository;
import com.example.demo.service.VendorTierService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VendorTierServiceImpl implements VendorTierService {
    private final VendorTierRepository tierRepo;

    public VendorTierServiceImpl(VendorTierRepository tierRepo) {
        this.tierRepo = tierRepo; // [cite: 320]
    }

    @Override
    public VendorTier createTier(VendorTier tier) {
        if (tier.getMinScoreThreshold() < 0 || tier.getMinScoreThreshold() > 100) {
            throw new IllegalArgumentException("Threshold must be 0-100"); // [cite: 322, 166]
        }
        if (tierRepo.existsByTierName(tier.getTierName())) {
            throw new IllegalArgumentException("Tier name must be unique"); // [cite: 323, 116]
        }
        return tierRepo.save(tier);
    }

    @Override
    public VendorTier getTierById(Long id) {
        return tierRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Tier not found")); // [cite: 326]
    }

    @Override
    public void deactivateTier(Long id) {
        VendorTier tier = getTierById(id);
        tier.setActive(false);
        tierRepo.save(tier); // [cite: 325]
    }
}