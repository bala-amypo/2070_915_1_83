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
        this.tierRepo = tierRepo;
    }

    @Override
    public VendorTier createTier(VendorTier tier) {
        if (tier.getMinScoreThreshold() == null || tier.getMinScoreThreshold() < 0 || tier.getMinScoreThreshold() > 100) {
            throw new IllegalArgumentException("0-100");
        }
        if (tierRepo.existsByTierName(tier.getTierName())) {
            throw new IllegalArgumentException("Tier name must be unique");
        }
        tier.setActive(true);
        return tierRepo.save(tier);
    }

    @Override
    public VendorTier updateTier(Long id, VendorTier tier) {
        VendorTier existing = getTierById(id);
        existing.setTierName(tier.getTierName());
        existing.setMinScoreThreshold(tier.getMinScoreThreshold());
        existing.setDescription(tier.getDescription());
        return tierRepo.save(existing);
    }

    @Override
    public VendorTier getTierById(Long id) {
        return tierRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("tier not found"));
    }

    @Override
    public List<VendorTier> getAllTiers() {
        return tierRepo.findAll();
    }

    @Override
    public void deactivateTier(Long id) {
        VendorTier tier = getTierById(id);
        tier.setActive(false);
        tierRepo.save(tier);
    }
}