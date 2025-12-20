package com.example.demo.controller;

import com.example.demo.model.Vendor;
import com.example.demo.service.VendorService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
[cite_start]@RequestMapping("/api/vendors") // [cite: 362]
public class VendorController {
    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    [cite_start]@PostMapping // [cite: 365]
    public Vendor create(@RequestBody Vendor vendor) {
        return vendorService.createVendor(vendor);
    }

    [cite_start]@PutMapping("/{id}") // [cite: 366]
    public Vendor update(@PathVariable Long id, @RequestBody Vendor vendor) {
        return vendorService.updateVendor(id, vendor);
    }

    [cite_start]@GetMapping("/{id}") // [cite: 367]
    public Vendor get(@PathVariable Long id) {
        return vendorService.getVendorById(id);
    }

    [cite_start]@GetMapping // [cite: 368]
    public List<Vendor> list() {
        return vendorService.getAllVendors();
    }

    [cite_start]@PutMapping("/{id}/deactivate") // [cite: 369]
    public void deactivate(@PathVariable Long id) {
        vendorService.deactivateVendor(id);
    }
}