package com.example.demo.repository;

import com.example.demo.model.DeliveryEvaluation;
import com.example.demo.model.SLARequirement;
import com.example.demo.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DeliveryEvaluationRepository
        extends JpaRepository<DeliveryEvaluation, Long> {

    // REQUIRED by test
    List<DeliveryEvaluation> findByVendorId(Long vendorId);

    // REQUIRED by test
    List<DeliveryEvaluation> findBySlaRequirementId(Long slaId);

    // REQUIRED by test (MUST use @Query)
    @Query("""
           SELECT d FROM DeliveryEvaluation d
           WHERE d.vendor = :vendor
           AND d.qualityScore >= :minScore
           """)
    List<DeliveryEvaluation> findHighQualityDeliveries(
            Vendor vendor,
            Double minScore
    );

    // REQUIRED by test (MUST use @Query)
    @Query("""
           SELECT d FROM DeliveryEvaluation d
           WHERE d.slaRequirement = :sla
           AND d.meetsDeliveryTarget = true
           """)
    List<DeliveryEvaluation> findOnTimeDeliveries(
            SLARequirement sla
    );
}
