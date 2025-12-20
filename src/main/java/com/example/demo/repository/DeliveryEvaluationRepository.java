package com.example.demo.repository;

import com.example.demo.model.DeliveryEvaluation;
import com.example.demo.model.Vendor;
import com.example.demo.model.SLARequirement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface DeliveryEvaluationRepository extends JpaRepository<DeliveryEvaluation, Long> {
    List<DeliveryEvaluation> findByVendorId(Long vendorId); [cite_start]// [cite: 185]
    List<DeliveryEvaluation> findBySlaRequirementId(Long slaId); [cite_start]// [cite: 186]

    @Query("SELECT d FROM DeliveryEvaluation d WHERE d.vendor = ?1 AND d.qualityScore >= ?2")
    List<DeliveryEvaluation> findHighQualityDeliveries(Vendor vendor, Double minScore); [cite_start]// [cite: 189]

    @Query("SELECT d FROM DeliveryEvaluation d WHERE d.slaRequirement = ?1 AND d.actualDeliveryDays <= d.slaRequirement.maxDeliveryDays")
    List<DeliveryEvaluation> findOnTimeDeliveries(SLARequirement sla); [cite_start]// [cite: 190]
}