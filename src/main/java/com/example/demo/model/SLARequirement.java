@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "requirementName"))
public class SLARequirement {
    @Id @GeneratedValue
    private Long id;
    private String requirementName;
    private String description;
    private Integer maxDeliveryDays;
    private Double minQualityScore;
    private Boolean active = true;
}
