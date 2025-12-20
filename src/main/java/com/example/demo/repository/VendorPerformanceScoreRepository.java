package com.example.demo.repository;

import com.example.demo.model.VendorPerformanceScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VendorPerformanceScoreRepository extends JpaRepository<VendorPerformanceScore, Long> {
    // Returns scores ordered newest first [cite: 194]
    List<VendorPerformanceScore> findByVendorIdOrderByCalculatedAtDesc(Long vendorId); 
}