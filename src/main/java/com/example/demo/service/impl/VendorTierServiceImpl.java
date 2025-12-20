package com.example.demo.service.impl;

import com.example.demo.model.VendorTier;
import com.example.demo.repository.VendorTierRepository;
import com.example.demo.service.VendorTierService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VendorTierServiceImpl implements VendorTierService {
    private final VendorTierRepository vendorTierRepository;

    public VendorTierServiceImpl(VendorTierRepository vendorTierRepository) {
        this.vendorTierRepository = vendorTierRepository; [cite_start]// [cite: 320]
    }

    @Override
    public VendorTier createTier(VendorTier tier) {
        if (tier.getMinScoreThreshold() < 0 || tier.getMinScoreThreshold() > 100) {
            throw new IllegalArgumentException("0-100"); [cite_start]// [cite: 322]
        }
        if (vendorTierRepository.existsByTierName(tier.getTierName())) {
            throw new IllegalArgumentException("unique tier name"); [cite_start]// [cite: 323]
        }
        return vendorTierRepository.save(tier);
    }

    @Override
    public VendorTier updateTier(Long id, VendorTier tier) {
        VendorTier existing = getTierById(id);
        existing.setTierName(tier.getTierName());
        existing.setMinScoreThreshold(tier.getMinScoreThreshold());
        existing.setDescription(tier.getDescription());
        return vendorTierRepository.save(existing);
    }

    @Override
    public VendorTier getTierById(Long id) {
        return vendorTierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("tier not found")); [cite_start]// [cite: 326]
    }

    @Override
    public List<VendorTier> getAllTiers() {
        return vendorTierRepository.findAll();
    }

    @Override
    public void deactivateTier(Long id) {
        VendorTier tier = getTierById(id);
        tier.setActive(false); [cite_start]// [cite: 325]
        vendorTierRepository.save(tier);
    }
}