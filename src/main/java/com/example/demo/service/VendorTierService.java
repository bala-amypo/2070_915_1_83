package com.example.demo.service;

import com.example.demo.model.VendorTier;
import java.util.List;

public interface VendorTierService {
    VendorTier createTier(VendorTier tier); [cite_start]// [cite: 314]
    VendorTier updateTier(Long id, VendorTier tier); [cite_start]// [cite: 315]
    VendorTier getTierById(Long id); [cite_start]// [cite: 316]
    List<VendorTier> getAllTiers(); [cite_start]// [cite: 317]
    void deactivateTier(Long id); [cite_start]// [cite: 318]
}