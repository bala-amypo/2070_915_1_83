package com.example.demo.controller;

import com.example.demo.model.VendorTier;
import com.example.demo.service.VendorTierService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tiers")
@Tag(name = "Vendor Tiers")
public class VendorTierController {

    private final VendorTierService tierService;

    public VendorTierController(VendorTierService tierService) {
        this.tierService = tierService;
    }

    @PostMapping
    public VendorTier create(@RequestBody VendorTier tier) {
        return tierService.createTier(tier);
    }

    @PutMapping("/{id}")
    public VendorTier update(@PathVariable Long id,
                             @RequestBody VendorTier tier) {
        return tierService.updateTier(id, tier);
    }

    @GetMapping("/{id}")
    public VendorTier getById(@PathVariable Long id) {
        return tierService.getTierById(id);
    }

    @GetMapping
    public List<VendorTier> getAll() {
        return tierService.getAllTiers();
    }

    @PutMapping("/{id}/deactivate")
    public void deactivate(@PathVariable Long id) {
        tierService.deactivateTier(id);
    }
}
