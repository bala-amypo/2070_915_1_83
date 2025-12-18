package com.example.demo.controller;

import com.example.demo.model.DeliveryEvaluation;
import com.example.demo.service.DeliveryEvaluationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/evaluations")
@Tag(name = "Delivery Evaluations")
public class DeliveryEvaluationController {

    private final DeliveryEvaluationService evaluationService;

    public DeliveryEvaluationController(DeliveryEvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    @PostMapping
    public DeliveryEvaluation create(@RequestBody DeliveryEvaluation evaluation) {
        return evaluationService.createEvaluation(evaluation);
    }

    @GetMapping("/{id}")
    public DeliveryEvaluation getById(@PathVariable Long id) {
        return evaluationService.getEvaluationById(id);
    }

    @GetMapping("/vendor/{vendorId}")
    public List<DeliveryEvaluation> getByVendor(@PathVariable Long vendorId) {
        return evaluationService.getEvaluationsForVendor(vendorId);
    }

    @GetMapping("/requirement/{reqId}")
    public List<DeliveryEvaluation> getByRequirement(@PathVariable Long reqId) {
        return evaluationService.getEvaluationsForRequirement(reqId);
    }
}
