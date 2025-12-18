package com.example.demo.service.impl;

import com.example.demo.model.VendorTier;
import com.example.demo.repository.VendorTierRepository;
import com.example.demo.service.VendorTierService;

import java.util.List;

public class VendorTierServiceImpl implements VendorTierService {

    private final VendorTierRepository repo;

    public VendorTierServiceImpl(VendorTierRepository repo) {
        this.repo = repo;
    }

    @Override
    public VendorTier createTier(VendorTier tier) {
        if (tier.getMinScoreThreshold() < 0 ||
                tier.getMinScoreThreshold() > 100)
            throw new IllegalArgumentException("between 0 and 100");

        if (repo.existsByTierName(tier.getTierName()))
            throw new IllegalArgumentException("unique");

        tier.setActive(true);
        return repo.save(tier);
    }

    @Override
    public VendorTier updateTier(Long id, VendorTier tier) {
        VendorTier existing = getTierById(id);

        existing.setTierName(tier.getTierName());
        existing.setMinScoreThreshold(tier.getMinScoreThreshold());
        return repo.save(existing);
    }

    @Override
    public VendorTier getTierById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new IllegalStateException("not found"));
    }

    @Override
    public List<VendorTier> getAllTiers() {
        return repo.findAll();
    }

    @Override
    public void deactivateTier(Long id) {
        VendorTier tier = getTierById(id);
        tier.setActive(false);
        repo.save(tier);
    }
}
