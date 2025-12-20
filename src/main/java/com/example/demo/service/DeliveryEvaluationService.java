package com.example.demo.service;

import com.example.demo.model.DeliveryEvaluation;
import java.util.List;

public interface DeliveryEvaluationService {
    DeliveryEvaluation createEvaluation(DeliveryEvaluation evaluation); [cite_start]// [cite: 269]
    DeliveryEvaluation getEvaluationById(Long id); [cite_start]// [cite: 270]
    List<DeliveryEvaluation> getEvaluationsForVendor(Long vendorId); [cite_start]// [cite: 271]
    List<DeliveryEvaluation> getEvaluationsForRequirement(Long requirementId); [cite_start]// [cite: 272]
}