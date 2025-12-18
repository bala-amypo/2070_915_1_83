public interface DeliveryEvaluationRepository extends JpaRepository<DeliveryEvaluation, Long> {
    List<DeliveryEvaluation> findByVendorId(Long vendorId);
    List<DeliveryEvaluation> findBySlaRequirementId(Long slaId);

    @Query("select d from DeliveryEvaluation d where d.qualityScore >= :min")
    List<DeliveryEvaluation> findHighQualityDeliveries(Vendor vendor, Double min);

    @Query("select d from DeliveryEvaluation d where d.meetsDeliveryTarget = true")
    List<DeliveryEvaluation> findOnTimeDeliveries(SLARequirement sla);
}
