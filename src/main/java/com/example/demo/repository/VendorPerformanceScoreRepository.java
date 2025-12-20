package com.example.demo.repository;

import com.example.demo.model.VendorPerformanceScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VendorPerformanceScoreRepository extends JpaRepository<VendorPerformanceScore, Long> {

    // Custom query to fix the type mismatch while keeping the method name required by tests
    @Query("SELECT v FROM VendorPerformanceScore v WHERE v.vendor.id = :vendorId ORDER BY v.calculatedAt DESC")
    List<VendorPerformanceScore> findByVendorOrderByCalculatedAtDesc(Long vendorId); 
}