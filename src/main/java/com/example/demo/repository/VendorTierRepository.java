package com.example.demo.repository;

import com.example.demo.model.VendorTier;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VendorTierRepository extends JpaRepository<VendorTier, Long> {
    List<VendorTier> findByActiveTrueOrderByMinScoreThresholdDesc(); [cite_start]// [cite: 198]
    boolean existsByTierName(String name); [cite_start]// [cite: 201]
}