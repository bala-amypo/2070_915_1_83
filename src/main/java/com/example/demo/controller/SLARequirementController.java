package com.example.demo.controller;

import com.example.demo.model.SLARequirement;
import com.example.demo.service.SLARequirementService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sla-requirements")
@Tag(name = "SLA Requirements")
public class SLARequirementController {

    private final SLARequirementService slaService;

    public SLARequirementController(SLARequirementService slaService) {
        this.slaService = slaService;
    }

    @PostMapping
    public SLARequirement create(@RequestBody SLARequirement req) {
        return slaService.createRequirement(req);
    }

    @PutMapping("/{id}")
    public SLARequirement update(@PathVariable Long id,
                                 @RequestBody SLARequirement req) {
        return slaService.updateRequirement(id, req);
    }

    @GetMapping("/{id}")
    public SLARequirement getById(@PathVariable Long id) {
        return slaService.getRequirementById(id);
    }

    @GetMapping
    public List<SLARequirement> getAll() {
        return slaService.getAllRequirements();
    }

    @PutMapping("/{id}/deactivate")
    public void deactivate(@PathVariable Long id) {
        slaService.deactivateRequirement(id);
    }
}
