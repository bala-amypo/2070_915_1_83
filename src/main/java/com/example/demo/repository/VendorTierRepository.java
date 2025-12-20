package com.example.demo.repository;

import com.example.demo.model.VendorTier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VendorTierRepository extends JpaRepository<VendorTier, Long> {
    // Returns active tiers ordered by threshold descending [cite: 198]
    List<VendorTier> findByActiveTrueOrderByMinScoreThresholdDesc(); 
    
    // Checks for unique tier names [cite: 201]
    boolean existsByTierName(String name); 
}