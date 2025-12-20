// SLARequirementRepository.java
package com.example.demo.repository;
import com.example.demo.model.SLARequirement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SLARequirementRepository extends JpaRepository<SLARequirement, Long> {
    boolean existsByRequirementName(String name); // [cite: 181]
}

// VendorPerformanceScoreRepository.java
package com.example.demo.repository;
import com.example.demo.model.VendorPerformanceScore;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VendorPerformanceScoreRepository extends JpaRepository<VendorPerformanceScore, Long> {
    List<VendorPerformanceScore> findByVendorIdOrderByCalculatedAtDesc(Long vendorId); // [cite: 194]
}

// VendorTierRepository.java
package com.example.demo.repository;
import com.example.demo.model.VendorTier;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VendorTierRepository extends JpaRepository<VendorTier, Long> {
    List<VendorTier> findByActiveTrueOrderByMinScoreThresholdDesc(); // [cite: 198]
    boolean existsByTierName(String name); // [cite: 201]
}

// UserRepository.java
package com.example.demo.repository;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email); // [cite: 205]
}