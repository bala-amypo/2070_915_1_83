package com.example.demo.controller;

import com.example.demo.model.Vendor;
import com.example.demo.service.VendorService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/vendors") // [cite: 362]
public class VendorController {
    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService; // [cite: 363]
    }

    @PostMapping // [cite: 365]
    public Vendor createVendor(@RequestBody Vendor vendor) {
        return vendorService.createVendor(vendor);
    }

    @GetMapping("/{id}") // [cite: 367]
    public Vendor getVendorById(@PathVariable Long id) {
        return vendorService.getVendorById(id);
    }

    @GetMapping // [cite: 368]
    public List<Vendor> getAllVendors() {
        return vendorService.getAllVendors();
    }

    @PutMapping("/{id}/deactivate") // [cite: 369]
    public void deactivateVendor(@PathVariable Long id) {
        vendorService.deactivateVendor(id);
    }
}